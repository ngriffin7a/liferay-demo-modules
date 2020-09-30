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

package com.liferay.asset.ext.web.internal.portlet;

import com.liferay.asset.ext.service.AssetEntryUserStatusLocalService;
import com.liferay.asset.ext.web.constants.AssetExtWebPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletContext;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.PortletResponse;
import javax.portlet.ProcessAction;

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
		"javax.portlet.display-name=AssetExtWeb",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jspx",
		"javax.portlet.name=" + AssetExtWebPortletKeys.ASSET_EXT_WEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"javax.portlet.version=3.0"
	},
	service = Portlet.class
)
public class AssetExtWebPortlet extends MVCPortlet {

	@ProcessAction(name = "markAllInSiteAsUnread")
	public void markAllInSiteAsUnread(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (_log.isDebugEnabled()) {
			try {
				_log.debug(
					"Marking all articles as read by all users for site " +
						"named \"" + themeDisplay.getScopeGroupName() + "\"");
			}
			catch (PortalException e) {
				throw new PortletException(e);
			}
		}

		_assetEntryUserStatusLocalService.deleteAllByGroupId(
			themeDisplay.getScopeGroupId());
	}

	protected void include(
			String path, PortletRequest portletRequest,
			PortletResponse portletResponse, String lifecycle)
		throws IOException, PortletException {

		if ((path != null) && path.endsWith(".jspx")) {
			PortletContext portletContext = getPortletContext();

			PortletRequestDispatcher portletRequestDispatcher =
				portletContext.getRequestDispatcher(path);

			portletRequestDispatcher.include(portletRequest, portletResponse);
		}
		else {
			super.include(path, portletRequest, portletResponse, lifecycle);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AssetExtWebPortlet.class);

	@Reference
	private AssetEntryUserStatusLocalService _assetEntryUserStatusLocalService;

}