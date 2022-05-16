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

import com.liferay.demo.jira.integration.model.Project;

import java.util.Map;

/**
 * @author Neil Griffin
 */
public class ProjectImpl implements Project {

	public ProjectImpl(
		String projectId, String key, String name,
		Map<String, String> avatarURLs) {

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
	public String getKey() {
		return _key;
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public String getProjectId() {
		return _projectId;
	}

	private Map<String, String> _avatarURLs;
	private String _key;
	private String _name;
	private String _projectId;

}