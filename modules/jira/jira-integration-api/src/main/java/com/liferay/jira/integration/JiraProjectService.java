/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 */

package com.liferay.jira.integration;

import java.io.IOException;

import java.util.List;
import java.util.Optional;

/**
 * @author Neil Griffin
 */
public interface JiraProjectService {

	public Optional<JiraProject> getProject(
		JiraToken jiraToken, String projectId)
		throws IOException;

	public List<JiraProject> getProjects(JiraToken jiraToken)
		throws IOException;

}