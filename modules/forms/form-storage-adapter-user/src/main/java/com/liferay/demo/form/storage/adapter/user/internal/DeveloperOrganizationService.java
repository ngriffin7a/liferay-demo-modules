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
import com.liferay.demo.user.registration.BaseLiferayOrganizationService;
import com.liferay.demo.user.registration.LiferayOrganizationService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.AddressLocalService;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.ServiceContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Neil Griffin
 */
@Component(service = DeveloperOrganizationService.class)
public class DeveloperOrganizationService
	extends BaseLiferayOrganizationService
	implements LiferayOrganizationService<Developer> {

	@Override
	public Organization addOrganization(
			Developer developer, ServiceContext serviceContext)
		throws PortalException {

		long creatorUserId = serviceContext.getUserId();

		Organization organization = addOrganization(
			developer.getAddressLine1(), developer.getAddressLine2(),
			developer.getCity(), developer.getCountryId(), creatorUserId,
			developer.getCompanyName(), developer.getPostalCode(),
			developer.getRegionId(), serviceContext);

		// Set the expando column (custom field) values. Note that since the
		// form is being used by a "Guest" user, we have to trick the Liferay
		// permissionChecker into thinking that the current user is the
		// Administrator user (someone who has the permission to set expando
		// column values).

		PermissionChecker permissionCheckerBackup =
			PermissionThreadLocal.getPermissionChecker();
		PermissionThreadLocal.setPermissionChecker(
			getAdministratorPermissionChecker(serviceContext.getCompanyId()));

		// Set Expandos
		// ExpandoBridge expandoBridge = organization.getExpandoBridge();

		PermissionThreadLocal.setPermissionChecker(permissionCheckerBackup);

		return organization;
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
	protected OrganizationLocalService getOrganizationLocalService() {
		return _organizationLocalService;
	}

	@Reference
	private AddressLocalService _addressLocalService;

	@Reference
	private OrganizationLocalService _organizationLocalService;

	@Reference
	private PermissionCheckerFinder _permissionCheckerFinder;

}