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

import com.liferay.demo.jira.integration.config.JiraConfiguration;
import com.liferay.demo.jira.integration.model.Issue;
import com.liferay.demo.jira.integration.service.JiraIssueService;
import com.liferay.demo.jira.integration.model.Status;
import com.liferay.demo.jira.integration.service.JiraStatusService;
import com.liferay.demo.jira.integration.service.JiraToken;
import com.liferay.demo.jira.integration.service.JiraTokenService;
import com.liferay.demo.jira.integration.web.PortletKeys;
import com.liferay.demo.jira.integration.web.internal.el.CurrentUser;
import com.liferay.demo.jira.integration.web.internal.portlet.JiraTokenUtil;
import com.liferay.demo.jira.integration.web.internal.view.IssueManagementToolbarViewState;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.view.state.ManagementToolbarViewState;
import com.liferay.view.state.ManagementToolbarViewStateDisplayContextAdapter;
import com.liferay.view.state.ManagementToolbarViewStateFactory;
import com.liferay.view.state.SearchContainerViewState;
import com.liferay.view.state.SearchContainerViewStateFactory;

import java.io.IOException;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.portlet.PortletConfig;
import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.RenderParameters;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Neil Griffin
 */
@Component(
	configurationPid = "com.liferay.demo.jira.integration.config.JiraConfiguration",
	immediate = true,
	property = {
		"javax.portlet.name=" + PortletKeys.ISSUES, "mvc.command.name=/",
		"mvc.command.name=issues"
	},
	service = MVCRenderCommand.class
)
public class IssueMasterRenderController implements MVCRenderCommand {

	@Activate
	public void activate(Map<Object, Object> properties) {
		_jiraConfiguration = ConfigurableUtil.createConfigurable(
			JiraConfiguration.class, properties);
	}
	
	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		RenderParameters renderParameters = renderRequest.getRenderParameters();

		String projectKey = renderParameters.getValue("projectKey");

		if (Validator.isNull(projectKey)) {
			projectKey = _jiraConfiguration.projectKey();
		}

		String status = GetterUtil.get(
			renderParameters.getValue("status"), "all");

		PortletSession portletSession = renderRequest.getPortletSession();

		Map<String, List<Issue>> jiraIssueMap =
			(Map<String, List<Issue>>)portletSession.getAttribute(
				"jiraIssueMap");

		if (jiraIssueMap == null) {
			jiraIssueMap = new HashMap<>();
		}

		List<Status> statuses =
			(List<Status>)portletSession.getAttribute(
				"statuses");

		if (statuses == null) {
			statuses = Collections.emptyList();
		}

		SearchContainerViewState issueSearchContainerViewState =
			_searchContainerViewStateFactory.create(
				"list", "all", "key", "asc", renderRequest,
				new String[] {"key", "summary"});

		renderRequest.setAttribute(
			"issueSearchContainerViewState",
			issueSearchContainerViewState);

		PortletConfig portletConfig = (PortletConfig)renderRequest.getAttribute(
			"javax.portlet.config");

		ResourceBundle resourceBundle = portletConfig.getResourceBundle(
			renderRequest.getLocale());

		// TODO: These are currently hard-coded but need to be addressed
		// as part of ManagementToolBarViewState.getViewTypeItems()

		String navigation = "all";
		boolean showAdvancedSearch = false;
		boolean showDisplayStyleCard = false;
		boolean showDisplayStyleList = true;
		boolean showDisplayStyleTable = true;
		boolean showInfoButton = false;
		boolean showSearch = false;
		boolean showSort = false;

		// TODO: Permissions?

		boolean currentUserMayAddIssueEntry = true;

		PortalPreferences portalPreferences =
			PortletPreferencesFactoryUtil.getPortalPreferences(renderRequest);

		ManagementToolbarViewState managementToolbarViewState =
			_managementToolbarViewStateFactory.create(
				LanguageUtil.get(resourceBundle, "action.ADD_ISSUE"),
				portalPreferences.getValue(
					PortletKeys.ISSUES, "display-style", "list"),
				navigation, "key", "asc", renderRequest, renderResponse,
				showAdvancedSearch, currentUserMayAddIssueEntry,
				showDisplayStyleCard, showDisplayStyleList,
				showDisplayStyleTable, showInfoButton, showSearch, showSort);

		String mapKey =
			StringBundler.concat(
				projectKey, status,
				issueSearchContainerViewState.getOrderByType(),
				issueSearchContainerViewState.getOrderByCol());

		List<Issue> issues = jiraIssueMap.get(mapKey);

		if (issues == null) {
			JiraToken jiraToken = null;

			try {
				jiraToken =
					JiraTokenUtil.getJiraTokenFromPortletSession(
						_jiraTokenService, portletSession);

				issues = _jiraIssueService.getIssues(jiraToken, projectKey);
				/*
				issues = _jiraIssueService.getIssues(
					jiraToken, status,
					Objects.equals(
						issueSearchContainerViewState.getOrderByType(),
						"asc"),
					issueSearchContainerViewState.getOrderByCol());
				 */

				jiraIssueMap.put(mapKey, issues);

				statuses =
					_jiraStatusService.getStatuses(jiraToken);
			}
			catch (IOException ioException) {
				throw new PortletException(ioException);
			}
		}

		portletSession.setAttribute(
			"jiraIssueMap", jiraIssueMap);

		portletSession.setAttribute(
			"statuses", statuses);

		renderRequest.setAttribute("currentUser", new CurrentUser());
		renderRequest.setAttribute("issueCount", issues.size());
		renderRequest.setAttribute("issues", issues);
		renderRequest.setAttribute("statuses", statuses);

		String filterNavigationMessage = LanguageUtil.get(
			resourceBundle, "filter-navigation");
		String orderByMessage = LanguageUtil.get(resourceBundle, "order-by");
		String keyMessage = LanguageUtil.get(resourceBundle, "key");
		String summaryMessage = LanguageUtil.get(
			resourceBundle, "summary");

		renderRequest.setAttribute(
			"issueManagementToolbarViewState",
			new ManagementToolbarViewStateDisplayContextAdapter(
				new IssueManagementToolbarViewState(
					managementToolbarViewState,
					_portal.getHttpServletRequest(renderRequest),
					_portal.getLiferayPortletResponse(renderResponse), status,
					filterNavigationMessage, orderByMessage, keyMessage,
					summaryMessage, statuses)));

		return "/views/issues.jsp";
	}

	private volatile JiraConfiguration _jiraConfiguration;

	@Reference
	private ManagementToolbarViewStateFactory
		_managementToolbarViewStateFactory;

	@Reference
	private Portal _portal;

	@Reference
	private SearchContainerViewStateFactory _searchContainerViewStateFactory;

	@Reference
	private JiraIssueService _jiraIssueService;

	@Reference
	private JiraStatusService _jiraStatusService;

	@Reference
	private JiraTokenService _jiraTokenService;

}