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

package com.liferay.demo.form.storage.adapter.user.internal;

import com.liferay.demo.permission.PermissionCheckerFinder;
import com.liferay.demo.user.registration.BaseLiferayUserService;
import com.liferay.demo.user.registration.LiferayUserService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.AddressLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserGroupRoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Neil Griffin
 */
@Component(service = DeveloperUserService.class)
public class DeveloperUserService
	extends BaseLiferayUserService implements LiferayUserService<Developer> {

	@Override
	public User addUser(
			Developer developer, Organization organization,
			String[] organizationRoleNames, ServiceContext serviceContext)
		throws PortalException {

		boolean active = true;
		boolean autoPassword = true;
		boolean autoScreenName = true;
		String middleName = null;
		String password1 = null;
		String password2 = null;
		String screenName = null;
		boolean sendEmail = true;

		User user = add(
			active, autoPassword, autoScreenName, developer.getEmailAddress(),
			developer.getFirstName(), developer.getJobTitle(), middleName,
			developer.getMobilePhone(), developer.getLastName(),
			organization.getOrganizationId(), password1, password2, screenName,
			sendEmail, serviceContext);

		return addOrganizationRoles(
			user, organization, organizationRoleNames, serviceContext);
	}

	@Override
	protected AddressLocalService getAddressLocalService() {
		return _addressLocalService;
	}

	@Override
	protected PermissionChecker getAdministratorPermissionChecker(
			long companyId)
		throws PortalException {

		return _permissionCheckerFinder.findAdministratorPermissionChecker(
			companyId);
	}

	@Override
	protected RoleLocalService getRoleLocalService() {
		return _roleLocalService;
	}

	@Override
	protected UserGroupRoleLocalService getUserGroupRoleService() {
		return _userGroupRoleLocalService;
	}

	@Override
	protected UserLocalService getUserLocalService() {
		return _userLocalService;
	}

	@Override
	protected void logError(String message) {
		_log.error(message);
	}

	@Override
	protected void logInfo(String message) {
		_log.info(message);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DeveloperUserService.class);

	@Reference
	private AddressLocalService _addressLocalService;

	@Reference
	private PermissionCheckerFinder _permissionCheckerFinder;

	@Reference
	private RoleLocalService _roleLocalService;

	@Reference
	private UserGroupRoleLocalService _userGroupRoleLocalService;

	@Reference
	private UserLocalService _userLocalService;

}