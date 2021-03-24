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

package com.liferay.demo.site.create.web.internal.portlet;

import com.liferay.demo.site.create.web.SiteCreateWebPortletKeys;
import com.liferay.demo.site.create.web.dto.SiteTemplate;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.LayoutSetPrototype;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.LayoutSetPrototypeServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.sites.kernel.util.SitesUtil;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionParameters;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.RenderURL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Neil Griffin
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=SiteCreateWeb",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/views/site-create.jsp",
		"javax.portlet.name=" + SiteCreateWebPortletKeys.SITECREATEWEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"javax.portlet.version=3.0"
	},
	service = Portlet.class
)
public class SiteCreateWebPortlet extends MVCPortlet {

	public void createSite(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ActionParameters actionParameters = actionRequest.getActionParameters();

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			actionRequest);

		Map<Locale, String> nameMap = LocalizationUtil.getLocalizationMap(
			actionRequest, "name");

		nameMap.put(
			actionRequest.getLocale(),
			GetterUtil.getString(actionParameters.getValue("name")));

		Map<Locale, String> descriptionMap =
			LocalizationUtil.getLocalizationMap(actionRequest, "description");
		int type = GetterUtil.getInteger(
			actionParameters.getValue("type"), GroupConstants.TYPE_SITE_OPEN);
		String friendlyURL = GetterUtil.getString(
			actionParameters.getValue("friendlyURL"));
		boolean manualMembership = GetterUtil.getBoolean(
			actionParameters.getValue("manualMembership"), true);
		boolean inheritContent = false;
		boolean active = GetterUtil.getBoolean(
			actionParameters.getValue("active"), true);

		Group liveGroup = null;

		try {
			liveGroup = _groupLocalService.addGroup(
				themeDisplay.getUserId(),
				GroupConstants.DEFAULT_PARENT_GROUP_ID, null, 0,
				GroupConstants.DEFAULT_LIVE_GROUP_ID, nameMap, descriptionMap,
				type, manualMembership,
				GroupConstants.DEFAULT_MEMBERSHIP_RESTRICTION, friendlyURL,
				true, inheritContent, active, serviceContext);
		}
		catch (PortalException portalException) {
			portalException.printStackTrace();

			throw portalException;
		}

		long layoutSetPrototypeId = GetterUtil.getLong(
			actionParameters.getValue("layoutSetPrototypeId"));

		long publicLayoutSetPrototypeId = layoutSetPrototypeId;
		long privateLayoutSetPrototypeId = layoutSetPrototypeId;

		boolean publicLayoutSetPrototypeLinkEnabled = false;
		boolean privateLayoutSetPrototypeLinkEnabled = false;

		SitesUtil.updateLayoutSetPrototypesLinks(
			liveGroup, publicLayoutSetPrototypeId, privateLayoutSetPrototypeId,
			publicLayoutSetPrototypeLinkEnabled,
			privateLayoutSetPrototypeLinkEnabled);
	}

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		RenderURL renderURL = renderResponse.createRenderURL();

		renderURL.setWindowState(LiferayWindowState.POP_UP);

		_log.debug("POP_UP renderURL=" + renderURL.toString());

		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		try {
			List<LayoutSetPrototype> layoutSetPrototypes =
				LayoutSetPrototypeServiceUtil.search(
					themeDisplay.getCompanyId(), Boolean.TRUE, null);

			List<SiteTemplate> siteTemplates = new ArrayList<>();

			for (LayoutSetPrototype layoutSetPrototype : layoutSetPrototypes) {
				siteTemplates.add(
					new SiteTemplate(
						layoutSetPrototype.getLayoutSetPrototypeId(),
						layoutSetPrototype.getName(themeDisplay.getLocale())));
			}

			renderRequest.setAttribute("siteTemplates", siteTemplates);
		}
		catch (PortalException portalException) {
			portalException.printStackTrace();
		}

		super.render(renderRequest, renderResponse);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SiteCreateWebPortlet.class);

	@Reference
	private GroupLocalService _groupLocalService;

}