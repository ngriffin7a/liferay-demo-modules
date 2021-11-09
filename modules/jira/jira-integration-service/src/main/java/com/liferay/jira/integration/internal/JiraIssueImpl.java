package com.liferay.jira.integration.internal;

import com.liferay.jira.integration.JiraIssue;

import java.util.Date;

public class JiraIssueImpl implements JiraIssue {

	public JiraIssueImpl(
		String issueId, String projectId, String issueNumber, Date createdDate,
		String status, String summary) {

		_issueId = issueId;
		_projectId = projectId;
		_issueNumber = issueNumber;
		_createdDate = createdDate;
		_status = status;
		_summary = summary;
	}

	@Override
	public String getProjectId() {
		return _projectId;
	}

	@Override
	public String getIssueId() {
		return _issueId;
	}

	@Override
	public String getKey() {
		return _issueNumber;
	}

	@Override
	public Date getCreatedDate() {
		return _createdDate;
	}

	@Override
	public String getSummary() {
		return _summary;
	}

	@Override
	public String getStatus() {
		return _status;
	}

	private String _projectId;
	private String _issueId;
	private String _issueNumber;
	private Date _createdDate;
	private String _summary;
	private String _status;

}