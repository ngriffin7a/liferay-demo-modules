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

package com.liferay.demo.permission.service.internal;

import com.liferay.demo.permission.PermissionCheckerFinder;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactory;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Neil Griffin
 */
@Component(service = PermissionCheckerFinder.class)
public class PermissionCheckerFinderImpl implements PermissionCheckerFinder {

	@Override
	public PermissionChecker findAdministratorPermissionChecker(long companyId)
		throws PortalException {

		PermissionChecker administratorPermissionChecker;
		Role administratorRole = _roleLocalService.getRole(
			companyId, RoleConstants.ADMINISTRATOR);

		List<User> administratorUsers = _userLocalService.getRoleUsers(
			administratorRole.getRoleId());

		if ((administratorUsers != null) && !administratorUsers.isEmpty()) {
			User administratorUser = administratorUsers.get(0);

			try {
				administratorPermissionChecker =
					_permissionCheckerFactory.create(administratorUser);
			}
			catch (Exception e) {
				throw new SystemException(e.getMessage(), e);
			}
		}
		else {
			throw new SystemException(
				"Unable to find a user with the Administrator role");
		}

		return administratorPermissionChecker;
	}

	@Reference
	private PermissionCheckerFactory _permissionCheckerFactory;

	@Reference
	private RoleLocalService _roleLocalService;

	@Reference
	private UserLocalService _userLocalService;

}