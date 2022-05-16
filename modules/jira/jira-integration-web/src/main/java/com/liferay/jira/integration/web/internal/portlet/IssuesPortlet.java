package com.liferay.jira.integration.web.internal.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.jira.integration.JiraIssue;
import com.liferay.jira.integration.JiraIssueService;
import com.liferay.jira.integration.JiraToken;
import com.liferay.jira.integration.JiraTokenService;
import com.liferay.jira.integration.web.internal.constants.PortletKeys;
import com.liferay.jira.integration.web.internal.el.CurrentUser;

import java.io.IOException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletSession;
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
		"com.liferay.portlet.display-category=Jira Integration",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Issues",
		"javax.portlet.init-param.template-path=/views/",
		"javax.portlet.init-param.view-template=/views/issues.jsp",
		"javax.portlet.name=" + PortletKeys.ISSUES,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"javax.portlet.supported-public-render-parameter=jiraProjectId",
		"javax.portlet.version=3.0"
	},
	service = Portlet.class
)
public class IssuesPortlet extends MVCPortlet {

	@Override
	public void doView(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		RenderParameters renderParameters = renderRequest.getRenderParameters();

		String jiraProjectId = renderParameters.getValue(
			"jiraProjectId");

		if (Validator.isNotNull(jiraProjectId)) {
			PortletSession portletSession = renderRequest.getPortletSession();

			Map<String, List<JiraIssue>> jiraIssueMap =
				(Map<String, List<JiraIssue>>)portletSession.getAttribute(
					"jiraIssueMap");

			if (jiraIssueMap == null) {
				jiraIssueMap = new HashMap<>();
			}

			List<JiraIssue> issues = jiraIssueMap.get(
				jiraProjectId);

			if (issues == null) {
				JiraToken jiraToken =
					JiraTokenUtil.getJiraTokenFromPortletSession(_jiraTokenService, portletSession);

				issues = _jiraIssueService.getIssues(jiraToken, jiraProjectId);

				jiraIssueMap.put(jiraProjectId, issues);
			}

			portletSession.setAttribute("jiraIssueMap", jiraIssueMap);

			renderRequest.setAttribute("issues", issues);
			renderRequest.setAttribute("currentUser", new CurrentUser());
		}

		super.doView(renderRequest, renderResponse);
	}

	@Reference
	private JiraIssueService _jiraIssueService;

	@Reference
	private JiraTokenService _jiraTokenService;

}