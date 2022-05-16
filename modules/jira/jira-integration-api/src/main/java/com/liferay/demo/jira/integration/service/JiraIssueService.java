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

package com.liferay.demo.jira.integration.service;

import com.liferay.demo.jira.integration.model.Issue;

import java.io.IOException;

import java.util.List;
import java.util.Optional;

/**
 * @author Neil Griffin
 */
public interface JiraIssueService {

	public Issue newIssue();

	public void createIssue(Issue issue) throws IOException;

	public List<Issue> getIssues(JiraToken jiraToken, String projectId)
		throws IOException;

	public Optional<Issue> getIssue(JiraToken jiraToken, String issueId)
		throws IOException;
}