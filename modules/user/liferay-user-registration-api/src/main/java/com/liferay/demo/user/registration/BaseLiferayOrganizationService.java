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

package com.liferay.demo.user.registration;

import com.liferay.portal.kernel.exception.NoSuchOrganizationException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.model.ListTypeConstants;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.OrganizationConstants;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.AddressLocalService;
import com.liferay.portal.kernel.service.ListTypeServiceUtil;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.List;

/**
 * @author Neil Griffin
 */
public abstract class BaseLiferayOrganizationService {

	protected Organization addOrganization(
			String addressLine1, String addressLine2, String city,
			long countryId, long creatorUserId, String name, String postalCode,
			long regionId, ServiceContext serviceContext)
		throws PortalException {

		OrganizationLocalService organizationLocalService =
			getOrganizationLocalService();

		Organization organization = null;

		try {
			organization = organizationLocalService.getOrganization(
				serviceContext.getCompanyId(), name);
		}
		catch (NoSuchOrganizationException noSuchOrganizationException) {
			String comments = null;
			boolean site = false;

			organization = organizationLocalService.addOrganization(
				creatorUserId,
				OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID, name,
				OrganizationConstants.TYPE_ORGANIZATION, regionId, countryId,
				ListTypeConstants.ORGANIZATION_STATUS_DEFAULT, comments, site,
				serviceContext);

			AddressLocalService addressLocalService = getAddressLocalService();

			String addressLine3 = null;
			boolean mailing = true;
			boolean primary = true;

			addressLocalService.addAddress(
				creatorUserId, Organization.class.getName(),
				organization.getOrganizationId(), addressLine1, addressLine2,
				addressLine3, city, postalCode, regionId, countryId,
				_getBillingTypeId(), mailing, primary, serviceContext);
		}

		return organization;
	}

	protected abstract AddressLocalService getAddressLocalService();

	protected abstract PermissionChecker getAdministratorPermissionChecker(
			long companyId)
		throws PortalException;

	protected abstract OrganizationLocalService getOrganizationLocalService();

	private long _getBillingTypeId() {
		long billingTypeId = 0;
		List<ListType> addressTypes = ListTypeServiceUtil.getListTypes(
			_ADDRESS_CLASS_NAME);

		for (ListType addressType : addressTypes) {
			String name = addressType.getName();

			if (name.equals("billing")) {
				billingTypeId = addressType.getListTypeId();

				break;
			}
		}

		return billingTypeId;
	}

	private static final String _ADDRESS_CLASS_NAME =
		"com.liferay.portal.kernel.model.Organization.address";

}