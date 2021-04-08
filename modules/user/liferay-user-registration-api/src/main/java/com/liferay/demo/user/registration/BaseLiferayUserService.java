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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Contact;
import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Phone;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserGroupRole;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.AddressLocalService;
import com.liferay.portal.kernel.service.ListTypeServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserGroupRoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.service.persistence.PhoneUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.users.admin.kernel.util.UsersAdminUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

/**
 * @author Neil Griffin
 */
public abstract class BaseLiferayUserService {

	public User addOrganizationRoles(
			User user, Organization organization,
			String[] organizationRoleNames, ServiceContext serviceContext)
		throws PortalException {

		Set<Long> organizationIdSet = SetUtil.fromArray(
			user.getOrganizationIds());

		long organizationId = organization.getOrganizationId();

		if (!organizationIdSet.contains(organizationId)) {
			organizationIdSet.add(organizationId);
		}

		long[] organizationIds = ArrayUtil.toArray(
			organizationIdSet.toArray(new Long[0]));

		Contact contact = user.getContact();

		UserGroupRoleLocalService userGroupRoleLocalService =
			getUserGroupRoleService();

		List<UserGroupRole> userGroupRoles = new ArrayList<>(
			userGroupRoleLocalService.getUserGroupRoles(user.getUserId()));

		RoleLocalService roleLocalService = getRoleLocalService();

		for (String organizationRoleName : organizationRoleNames) {
			logInfo("Adding organizational role: " + organizationRoleName);

			Role organizationRole = roleLocalService.getRole(
				serviceContext.getCompanyId(), organizationRoleName);

			UserGroupRole userGroupRole =
				userGroupRoleLocalService.createUserGroupRole(0);

			userGroupRole.setUserId(user.getUserId());
			userGroupRole.setGroupId(organization.getGroupId());
			userGroupRole.setRoleId(organizationRole.getRoleId());

			userGroupRoles.add(userGroupRole);
		}

		PermissionChecker permissionCheckerBackup =
			PermissionThreadLocal.getPermissionChecker();

		PermissionThreadLocal.setPermissionChecker(
			getAdministratorPermissionChecker(serviceContext.getCompanyId()));

		// Note: Exception will be thrown if we don't set the
		// PrinicpalThreadLocal name.

		String principalNameBackup = PrincipalThreadLocal.getName();
		PrincipalThreadLocal.setName(serviceContext.getUserId());

		UserLocalService userLocalService = getUserLocalService();

		boolean hasPortrait = true;

		byte[] portraitBytes = null;

		Calendar birthday = CalendarFactoryUtil.getCalendar();

		birthday.setTime(contact.getBirthday());

		userLocalService.updateUser(
			user.getUserId(), user.getPassword(), null, null,
			user.isPasswordReset(), null, null, user.getScreenName(),
			user.getEmailAddress(), user.getFacebookId(), user.getOpenId(),
			hasPortrait, portraitBytes, user.getLanguageId(),
			user.getTimeZoneId(), user.getGreeting(), user.getComments(),
			user.getFirstName(), user.getMiddleName(), user.getLastName(),
			contact.getPrefixId(), contact.getSuffixId(), user.isMale(),
			birthday.get(Calendar.MONTH), birthday.get(Calendar.DATE),
			birthday.get(Calendar.YEAR), contact.getSmsSn(),
			contact.getFacebookSn(), contact.getJabberSn(),
			contact.getSkypeSn(), contact.getTwitterSn(), user.getJobTitle(),
			user.getGroupIds(), organizationIds, user.getRoleIds(),
			userGroupRoles, user.getUserGroupIds(), serviceContext);

		PrincipalThreadLocal.setName(principalNameBackup);
		PermissionThreadLocal.setPermissionChecker(permissionCheckerBackup);

		return user;
	}

	protected User add(
			boolean active, boolean autoPassword, boolean autoScreenName,
			String emailAddress, String firstName, String jobTitle,
			String middleName, String mobilePhone, String lastName,
			long organizationId, String password1, String password2,
			String screenName, boolean sendEmail, ServiceContext serviceContext)
		throws PortalException {

		String userPassword1;
		String userPassword2;

		if (autoPassword) {
			userPassword1 = "";
			userPassword2 = "";
		}
		else {
			userPassword1 = password1;
			userPassword2 = password2;
		}

		String userScreenName;

		if (autoScreenName) {
			userScreenName = "";
		}
		else {
			userScreenName = screenName;
		}

		long facebookId = 0;
		String openId = "";
		int prefixId = 0;
		int suffixId = 0;
		boolean male = true;
		int birthdayMonth = 1;
		int birthdayDay = 1;
		int birthdayYear = 1970;
		long[] groupIds = {};

		long[] organizationIds = new long[0];

		if (organizationId > 0L) {
			organizationIds = new long[] {organizationId};
		}

		long[] roleIds = {};

		long[] userGroupIds = new long[0];

		// Add the user to the Liferay database (create an account).

		UserLocalService userLocalService = getUserLocalService();

		long companyId = serviceContext.getCompanyId();
		long creatorUserId = serviceContext.getUserId();

		User user = userLocalService.addUser(
			creatorUserId, companyId, autoPassword, userPassword1,
			userPassword2, autoScreenName, userScreenName, emailAddress,
			facebookId, openId, serviceContext.getLocale(), firstName,
			middleName, lastName, prefixId, suffixId, male, birthdayMonth,
			birthdayDay, birthdayYear, jobTitle, groupIds, organizationIds,
			roleIds, userGroupIds, sendEmail, serviceContext);

		// Disable the ability to login until someone approves the account.

		if (!active) {
			userLocalService.updateStatus(
				user.getUserId(), user.getStatus(), serviceContext);
		}

		// Add mobile phone.

		_updateMobilePhone(
			companyId, user.getContactId(), creatorUserId, user.getFullName(),
			mobilePhone, user.getUserId());

		return user;
	}

	protected abstract AddressLocalService getAddressLocalService();

	protected abstract PermissionChecker getAdministratorPermissionChecker(
			long companyId)
		throws PortalException;

	protected abstract RoleLocalService getRoleLocalService();

	protected abstract UserGroupRoleLocalService getUserGroupRoleService();

	protected abstract UserLocalService getUserLocalService();

	protected abstract void logError(String message);

	protected abstract void logInfo(String message);

	private long _getMobilePhoneTypeId() {
		long phoneTypeId = 0;
		List<ListType> phoneTypes = ListTypeServiceUtil.getListTypes(
			_PHONE_CLASS_NAME);

		for (ListType phoneType : phoneTypes) {
			String name = phoneType.getName();

			if (name.equals("mobile-phone")) {
				phoneTypeId = phoneType.getListTypeId();

				break;
			}
		}

		return phoneTypeId;
	}

	private void _updateMobilePhone(
			long companyId, long contactId, long creatorUserId, String fullName,
			String mobilePhone, long userId)
		throws PortalException {

		List<Phone> phones = new ArrayList<>();

		if (Validator.isNotNull(mobilePhone)) {
			if (Validator.isPhoneNumber(mobilePhone)) {
				Phone phone = PhoneUtil.create(0L);

				phone.setUserId(userId);
				phone.setCompanyId(companyId);
				phone.setNumber(mobilePhone);
				phone.setPrimary(true);
				phone.setTypeId(_getMobilePhoneTypeId());

				phones.add(phone);

				PermissionChecker permissionCheckerBackup =
					PermissionThreadLocal.getPermissionChecker();

				PermissionThreadLocal.setPermissionChecker(
					getAdministratorPermissionChecker(companyId));

				// Note: Exception will be thrown if we don't set the
				// PrinicpalThreadLocal name.

				String principalNameBackup = PrincipalThreadLocal.getName();
				PrincipalThreadLocal.setName(creatorUserId);
				UsersAdminUtil.updatePhones(
					Contact.class.getName(), contactId, phones);
				PrincipalThreadLocal.setName(principalNameBackup);
				PermissionThreadLocal.setPermissionChecker(
					permissionCheckerBackup);
			}
			else {
				if (!"N/A".equalsIgnoreCase(mobilePhone)) {
					logError(
						"Invalid mobilePhone=[" + mobilePhone +
							"] for registrant=[" + fullName + "]");
				}
			}
		}
	}

	private static final String _PHONE_CLASS_NAME =
		"com.liferay.portal.kernel.model.Contact.phone";

}