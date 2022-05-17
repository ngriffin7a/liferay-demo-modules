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

package com.liferay.demo.jira.integration.web.internal.controller;

import com.liferay.demo.jira.integration.model.Issue;
import com.liferay.demo.jira.integration.service.JiraIssueService;
import com.liferay.demo.jira.integration.service.JiraStatusService;
import com.liferay.demo.jira.integration.service.JiraToken;
import com.liferay.demo.jira.integration.service.JiraTokenService;
import com.liferay.demo.jira.integration.web.PortletKeys;
import com.liferay.demo.jira.integration.web.internal.el.CurrentUser;
import com.liferay.demo.jira.integration.web.internal.portlet.JiraTokenUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.view.state.SearchContainerViewState;
import com.liferay.view.state.SearchContainerViewStateFactory;

import java.io.IOException;

import java.util.Optional;

import javax.portlet.PortletException;
import javax.portlet.RenderParameters;
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
		"javax.portlet.name=" + PortletKeys.ISSUES,
		"mvc.command.name=issue-add", "mvc.command.name=issue-edit",
		"mvc.command.name=issue-view"
	},
	service = MVCRenderCommand.class
)
public class IssueDetailRenderController implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		renderRequest.setAttribute("currentUser", new CurrentUser());

		String navigation = "all";

		SearchContainerViewState issueSearchContainerViewState =
			_searchContainerViewStateFactory.create(
				"list", navigation, "key", "asc", renderRequest,
				new String[] {"key", "summary"});

		renderRequest.setAttribute(
			"issueSearchContainerViewState", issueSearchContainerViewState);

		Issue issue = _jiraIssueService.newIssue();

		RenderParameters renderParameters = renderRequest.getRenderParameters();

		String issueId = renderParameters.getValue("issueId");

		if (Validator.isNotNull(issueId)) {
			try {
				JiraToken jiraToken =
					JiraTokenUtil.getJiraTokenFromPortletSession(
						_jiraTokenService, renderRequest.getPortletSession());

				renderRequest.setAttribute(
					"statuses", _jiraStatusService.getStatuses(jiraToken));

				Optional<Issue> optionalIssue = _jiraIssueService.getIssue(
					jiraToken, issueId);

				if (optionalIssue.isPresent()) {
					issue = optionalIssue.get();
				}
				else {
					throw new PortletException(
						"No issue found with id=" + issueId);
				}
			}
			catch (IOException ioException) {
				throw new PortletException(ioException);
			}
		}

		renderRequest.setAttribute("issue", issue);

		return "/views/issue.jspx";
	}

	@Reference
	private JiraIssueService _jiraIssueService;

	@Reference
	private JiraStatusService _jiraStatusService;

	@Reference
	private JiraTokenService _jiraTokenService;

	@Reference
	private SearchContainerViewStateFactory _searchContainerViewStateFactory;

}