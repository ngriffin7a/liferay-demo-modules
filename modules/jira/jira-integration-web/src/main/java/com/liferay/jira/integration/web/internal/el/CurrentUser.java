package com.liferay.jira.integration.web.internal.el;

/**
 * @author Neil Griffin
 */
public class CurrentUser {

	public boolean maySelectProject(String projectId) {
		return true;
	}

	public boolean mayViewProject(String projectId) {
		return true;
	}

	public boolean mayViewIssue(String issueId) {
		return true;
	}

}