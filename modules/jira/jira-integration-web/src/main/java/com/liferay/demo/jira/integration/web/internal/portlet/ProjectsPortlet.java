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

import com.liferay.demo.jira.integration.model.Project;
import com.liferay.demo.jira.integration.service.JiraProjectService;
import com.liferay.demo.jira.integration.service.JiraToken;
import com.liferay.demo.jira.integration.service.JiraTokenService;
import com.liferay.demo.jira.integration.web.PortletKeys;
import com.liferay.demo.jira.integration.web.internal.el.CurrentUser;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import java.io.IOException;

import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Neil Griffin
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=Jira Integration",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Projects",
		"javax.portlet.init-param.template-path=/views/",
		"javax.portlet.init-param.view-template=/views/projects.jspx",
		"javax.portlet.name=" + PortletKeys.PROJECTS,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"javax.portlet.supported-public-render-parameter=projectKey",
		"javax.portlet.version=3.0"
	},
	service = Portlet.class
)
public class ProjectsPortlet extends MVCPortlet {

	@Override
	public void doView(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		PortletSession portletSession = renderRequest.getPortletSession();

		List<Project> projects = (List<Project>)portletSession.getAttribute(
			"projects");

		if (projects == null) {
			JiraToken jiraToken = JiraTokenUtil.getJiraTokenFromPortletSession(
				_jiraTokenService, portletSession);

			projects = _jiraProjectService.getProjects(jiraToken);

			portletSession.setAttribute("projects", projects);
		}

		renderRequest.setAttribute("currentUser", new CurrentUser());
		renderRequest.setAttribute("projects", projects);

		super.doView(renderRequest, renderResponse);
	}

	@Reference
	private JiraProjectService _jiraProjectService;

	@Reference
	private JiraTokenService _jiraTokenService;

}