package com.liferay.jira.integration.web.internal.portlet;

import com.liferay.jira.integration.JiraToken;
import com.liferay.jira.integration.JiraTokenService;

import java.io.IOException;

import javax.portlet.PortletSession;

public class JiraTokenUtil {

	public static JiraToken getJiraTokenFromPortletSession(
			JiraTokenService jiraTokenService,
			PortletSession portletSession)
		throws IOException {

		JiraToken jiraToken =
			(JiraToken)portletSession.getAttribute("jiraToken");

		if (jiraToken == null) {
			jiraToken = jiraTokenService.getToken();
		}

		portletSession.setAttribute("jiraToken", jiraToken);

		return jiraToken;
	}

}