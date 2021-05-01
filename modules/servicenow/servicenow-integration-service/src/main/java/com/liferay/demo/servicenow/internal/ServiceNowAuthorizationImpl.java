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

package com.liferay.demo.servicenow.internal;

import com.liferay.demo.servicenow.ServiceNowAuthorization;

import java.util.List;

/**
 * @author Neil Griffin
 */
public class ServiceNowAuthorizationImpl implements ServiceNowAuthorization {

	public ServiceNowAuthorizationImpl(
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