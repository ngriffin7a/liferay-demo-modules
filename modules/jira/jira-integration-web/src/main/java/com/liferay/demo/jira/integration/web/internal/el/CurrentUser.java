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

package com.liferay.demo.jira.integration.web.internal.el;

/**
 * @author Neil Griffin
 */
public class CurrentUser {

	public boolean mayAddIssue() {

		// TODO: Permissions

		return true;
	}

	public boolean mayEditIssue(String key) {

		// TODO: Permissions

		return true;
	}

	public boolean mayViewIssue(String issueId) {

		// TODO: Permissions

		return true;
	}

	public boolean maySelectProject(String projectId) {
		return true;
	}

	public boolean mayViewProject(String projectId) {
		return true;
	}

}