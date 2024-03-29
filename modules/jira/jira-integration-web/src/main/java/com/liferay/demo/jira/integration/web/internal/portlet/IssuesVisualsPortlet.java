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

package com.liferay.demo.jira.integration.web.internal.portlet;

import com.liferay.demo.jira.integration.web.PortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author Wes Kempa
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=Jira Integration",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.footer-portlet-javascript=https://cdn.fusioncharts.com/fusioncharts/latest/fusioncharts.js",
		"com.liferay.portlet.footer-portlet-javascript=https://cdn.fusioncharts.com/fusioncharts/latest/themes/fusioncharts.theme.fusion.js",
		"com.liferay.portlet.footer-portlet-javascript=/javascript/dashboard.js",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Issues Visual",
		"javax.portlet.init-param.template-path=/views/",
		"javax.portlet.init-param.view-template=/views/dashboard.jspx",
		"javax.portlet.name=" + PortletKeys.ISSUES_VISUAL,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"javax.portlet.version=3.0"
	},
	service = Portlet.class
)
public class IssuesVisualsPortlet extends MVCPortlet {
}