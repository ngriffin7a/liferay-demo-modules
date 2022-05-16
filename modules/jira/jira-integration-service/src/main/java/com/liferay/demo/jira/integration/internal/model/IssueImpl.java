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

package com.liferay.demo.jira.integration.internal.model;

import com.liferay.demo.jira.integration.model.Issue;

import java.util.Date;

/**
 * @author Neil Griffin
 */
public class IssueImpl implements Issue {

	@Override
	public Date getCreatedDate() {
		return _createdDate;
	}

	@Override
	public String getDescription() {
		return _description;
	}

	@Override
	public String getIssueId() {
		return _issueId;
	}

	@Override
	public String getKey() {
		return _key;
	}

	@Override
	public String getProjectId() {
		return _projectId;
	}

	@Override
	public String getStatus() {
		return _status;
	}

	@Override
	public String getSummary() {
		return _summary;
	}

	@Override
	public String getType() {
		return _type;
	}

	@Override
	public Date getUpdatedDate() {
		return _updatedDate;
	}

	@Override
	public void setCreatedDate(Date createdDate) {
		_createdDate = createdDate;
	}

	@Override
	public void setDescription(String description) {
		_description = description;
	}

	@Override
	public void setIssueId(String issueId) {
		_issueId = issueId;
	}

	@Override
	public void setKey(String key) {
		_key = key;
	}

	@Override
	public void setProjectId(String projectId) {
		_projectId = projectId;
	}

	@Override
	public void setStatus(String status) {
		_status = status;
	}

	@Override
	public void setSummary(String summary) {
		_summary = summary;
	}

	@Override
	public void setType(String type) {
		_type = type;
	}

	@Override
	public void setUpdatedDate(Date updatedDate) {
		_updatedDate = updatedDate;
	}

	private Date _createdDate;
	private String _description;
	private String _issueId;
	private String _key;
	private String _projectId;
	private String _status;
	private String _summary;
	private String _type;
	private Date _updatedDate;

}