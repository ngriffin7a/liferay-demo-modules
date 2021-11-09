package com.liferay.jira.integration.web.internal.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.jira.integration.JiraProject;
import com.liferay.jira.integration.JiraProjectService;
import com.liferay.jira.integration.JiraToken;
import com.liferay.jira.integration.JiraTokenService;
import com.liferay.jira.integration.web.internal.constants.PortletKeys;
import com.liferay.jira.integration.web.internal.el.CurrentUser;

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
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Projects",
		"javax.portlet.init-param.template-path=/views/",
		"javax.portlet.init-param.view-template=/views/projects.jsp",
		"javax.portlet.name=" + PortletKeys.PROJECTS,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"javax.portlet.supported-public-render-parameter=jiraProjectId",
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

		List<JiraProject> projects =
			(List<JiraProject>)portletSession.getAttribute("projects");

		if (projects == null) {
			JiraToken jiraToken =
				JiraTokenUtil.getJiraTokenFromPortletSession(_jiraTokenService, portletSession);

			projects = _jiraProjectService.getProjects(jiraToken);

			portletSession.setAttribute("projects", projects);
		}

		renderRequest.setAttribute("projects", projects);
		renderRequest.setAttribute("currentUser", new CurrentUser());

		super.doView(renderRequest, renderResponse);
	}

	@Reference
	private JiraProjectService _jiraProjectService;

	@Reference
	private JiraTokenService _jiraTokenService;

}