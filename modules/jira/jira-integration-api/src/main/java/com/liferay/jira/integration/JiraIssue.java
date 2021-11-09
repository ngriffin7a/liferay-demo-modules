package com.liferay.jira.integration;

import java.util.Date;

/**
 * @author Neil Griffin
 */
public interface JiraIssue {

	public String getProjectId();

	public String getIssueId();

	public String getKey();

	public Date getCreatedDate();

	public String getSummary();

	public String getStatus();

}