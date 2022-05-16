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

package com.liferay.demo.jira.integration.model;

import java.util.Date;

/**
 * @author Neil Griffin
 */
public interface Issue {

	public Date getCreatedDate();

	public void setCreatedDate(Date createdDate);

	public String getDescription();

	public void setDescription(String description);

	public String getIssueId();

	public void setIssueId(String issueId);

	public String getKey();

	public void setKey(String key);

	public String getProjectId();

	public void setProjectId(String projectId);

	public String getStatus();

	public void setStatus(String status);

	public String getSummary();

	public void setSummary(String summary);

	public String getType();

	public void setType(String type);

	public Date getUpdatedDate();

	public void setUpdatedDate(Date updatedDate);
}