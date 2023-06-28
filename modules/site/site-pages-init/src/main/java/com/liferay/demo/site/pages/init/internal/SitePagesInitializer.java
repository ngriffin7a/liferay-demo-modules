/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.demo.site.pages.init.internal;

import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.layout.util.LayoutCopyHelper;
import com.liferay.portal.kernel.exception.LayoutFriendlyURLException;
import com.liferay.portal.kernel.exception.LayoutFriendlyURLsException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.InputStream;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Neil Griffin
 */
@Component(immediate = true, service = SitePagesInitializer.class)
public class SitePagesInitializer {

	@Activate
	public void activate() {
		List<Company> companies = _companyLocalService.getCompanies();

		for (Company company : companies) {
			try {
				_addSitePages(
					company.getCompanyId(),
					company.getDefaultUser(
					).getUserId(),
					company.getDefaultWebId());
			}
			catch (Exception exception) {
				_log.error(exception.getMessage(), exception);
			}
		}
	}

	private Layout _addPublicLayout(
			long userId, long groupId, long parentLayoutId,
			String parentLayoutName, String layoutName, String layoutType,
			ServiceContext serviceContext)
		throws PortalException {

		boolean privateLayout = false;
		String title = null;
		String description = null;
		boolean hidden = false;
		String friendlyURL = "/";

		if (parentLayoutName == null) {
			friendlyURL += layoutName;
		}
		else {
			friendlyURL += parentLayoutName + "/" + layoutName;

			// TODO: parentLayoutId == ???;

		}

		friendlyURL = friendlyURL.replaceAll("[^a-zA-Z0-9/]", "-");

		friendlyURL = friendlyURL.replace("--", "-");
		friendlyURL = friendlyURL.replace("--", "-");

		friendlyURL = friendlyURL.toLowerCase();

		_log.info(
			"Adding layout type=" + layoutType + " friendlyURL=" + friendlyURL +
				" name=" + layoutName);

		return _layoutLocalService.addLayout(
			userId, groupId, privateLayout, parentLayoutId, layoutName, title,
			description, layoutType, hidden, friendlyURL, serviceContext);
	}

	private void _addSitePages(long companyId, long userId, String defaultWebId)
		throws Exception {

		ClassLoader classLoader = SitePagesInitializer.class.getClassLoader();

		InputStream resourceAsStream = classLoader.getResourceAsStream(
			"sites.json");

		JSONObject jsonObject = _jsonFactory.createJSONObject(
			new String(FileUtil.getBytes(resourceAsStream)));

		JSONArray sites = jsonObject.getJSONArray("sites");

		for (Object siteObject : sites) {
			JSONObject site = (JSONObject)siteObject;

			String name = site.getString("name");

			Group group = _groupLocalService.getGroup(companyId, name);

			long groupId = group.getGroupId();

			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setCompanyId(companyId);
			serviceContext.setUserId(userId);
			serviceContext.setScopeGroupId(groupId);

			JSONObject pages = site.getJSONObject("pages");

			JSONArray publicPages = pages.getJSONArray("public");

			if (publicPages != null) {
				for (Object publicPageObject : publicPages) {
					JSONObject publicPage = (JSONObject)publicPageObject;

					String publicPageName = publicPage.getString("name");

					Layout layout = _getPublicLayout(
						userId, groupId, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID,
						publicPageName, serviceContext);

					JSONArray contentPages = publicPage.getJSONArray(
						"content-pages");

					if (contentPages != null) {
						for (Object contentPageObject : contentPages) {
							String contentPageName = (String)contentPageObject;

							long parentLayoutId = layout.getLayoutId();
							String parentLayoutName = layout.getName(
								Locale.getDefault());
							Layout publicLayout = _getPublicLayout(
								userId, groupId, parentLayoutId, contentPageName, serviceContext);

							// TODO: You tried and tried to get a page of type
							// LayoutConstants.TYPE_CONTENT to get published so
							// that it would show up in the nav, but couldn't
							// get it working. You even copied code from
							// PublishLayoutMVCActionCommand.java but couldn't
							// get that working either. Simply setting the
							// workflow status to STATUS_APPROVED isn't good
							// enough I guess. So need to just create portlet
							// pages for now, and manually create content pages
							// where necessary in the demo.

							if (publicLayout == null) {
								publicLayout = _addPublicLayout(
									userId, groupId, parentLayoutId,
									parentLayoutName, contentPageName,
									LayoutConstants.TYPE_PORTLET,

									// LayoutConstants.TYPE_CONTENT,

									serviceContext);

								/* 7.2 */
								Date statusDate = Calendar.getInstance().getTime();
								_assetEntryLocalService.updateEntry(
									Layout.class.getName(), publicLayout.getPlid(),
									statusDate, null, true, false);

									/* 7.3
								_layoutLocalService.updateStatus(
									userId, publicLayout.getPlid(),
									WorkflowConstants.STATUS_APPROVED,
									serviceContext);
									 */
							}
						}
					}
				}
			}
		}
	}

	private Layout _getPublicLayout(
		long userId, long groupId, long parentLayoutId, String layoutName, ServiceContext serviceContext)
		throws PortalException {

		List<Layout> publicLayouts = _layoutLocalService.getLayouts(
			groupId, false);

		for (Layout publicLayout : publicLayouts) {
			if (Objects.equals(
					publicLayout.getName(Locale.getDefault()), layoutName) &&
				(parentLayoutId == publicLayout.getParentLayoutId())) {

				return publicLayout;
			}
		}

		boolean privateLayout = false;
		String title = layoutName;
		String description = layoutName;
		String type = LayoutConstants.TYPE_PORTLET;
		boolean hidden = false;
		boolean system = false;
		String friendlyURL = "/" + layoutName.toLowerCase().replaceAll("\\s", "-").replaceAll("--", "-");

		Layout layout = null;

		for (int i = 0; i < 5; i++) {
			// In case of duplicate page names, try 5 times to create the page, changing the friendlyURL a little each time
			try {
				_log.debug("Creating page layoutName=\"" + layoutName +
						   "\" friendlyURL=\"" + friendlyURL + "\"");
				layout = _layoutLocalService.addLayout(userId, groupId,
					privateLayout,
					parentLayoutId, layoutName, title, description, type,
					hidden, system, friendlyURL, serviceContext);
				
				break;
			}
			catch (LayoutFriendlyURLsException layoutFriendlyURLsException) {
				friendlyURL += "1";
				_log.warn("Duplicate friendlyURL, trying again");
			}
		}

		if (layout == null) {
			throw new LayoutFriendlyURLException(LayoutFriendlyURLException.POSSIBLE_DUPLICATE);
		}

		return layout;
	}

	private void _publishLayout(
			Layout draftLayout, Layout layout, ServiceContext serviceContext,
			long userId)
		throws Exception {
	}

	private static final Logger _log = LoggerFactory.getLogger(
		SitePagesInitializer.class);

	@Reference
	private AssetEntryLocalService _assetEntryLocalService;

	@Reference
	private CompanyLocalService _companyLocalService;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private LayoutCopyHelper _layoutCopyHelper;

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
	private ModuleServiceLifecycle _portalInitialized;

}