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

import com.liferay.jira.integration.JiraAuthorization;

import java.util.List;

/**
 * @author Neil Griffin
 */
public class JiraAuthorizationImpl implements JiraAuthorization {

	public JiraAuthorizationImpl(
		List<String> permissionNames, List<String> roleNames) {

		_permissionNames = permissionNames;
		_roleNames = roleNames;
	}

	@Override
	public List<String> getPermissionNames() {
		return _permissionNames;
	}

	@Override
	public List<String> getRoleNames() {
		return _roleNames;
	}

	private List<String> _permissionNames;
	private List<String> _roleNames;

}