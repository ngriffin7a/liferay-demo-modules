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
 * @author Neil Griffin
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=Jira Integration",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"javax.portlet.display-name=Issues",
		"javax.portlet.init-param.template-path=/views/",
		"javax.portlet.name=" + PortletKeys.ISSUES,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"javax.portlet.supported-public-render-parameter=projectKey",
		"javax.portlet.version=3.0"
	},
	service = Portlet.class
)
public class IssuesPortlet extends MVCPortlet {
}