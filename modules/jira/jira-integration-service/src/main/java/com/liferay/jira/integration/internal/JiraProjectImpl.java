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

package com.liferay.jira.integration.internal;

import com.liferay.jira.integration.JiraProject;

import java.util.Map;

/**
 * @author Neil Griffin
 */
public class JiraProjectImpl implements JiraProject {

	public JiraProjectImpl(
		String projectId, String key, String name, Map<String, String> avatarURLs) {

		_projectId = projectId;
		_key = key;
		_name = name;
		_avatarURLs = avatarURLs;
	}

	@Override
	public Map<String, String> getAvatarURLs() {
		return _avatarURLs;
	}

	@Override
	public String getProjectId() {
		return _projectId;
	}

	@Override
	public String getKey() {
		return _key;
	}

	@Override
	public String getName() {
		return _name;
	}

	private String _projectId;
	private String _key;
	private String _name;
	private Map<String, String> _avatarURLs;

}