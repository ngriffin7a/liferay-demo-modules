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

package com.liferay.demo.fragment.fix.internal;

import com.liferay.demo.permission.PermissionCheckerFinder;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.CompanyLocalService;

import java.util.Arrays;
import java.util.List;

import com.liferay.portal.kernel.service.ResourceActionLocalService;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;


/**
 * @author Neil Griffin
 */
@Component(immediate = true, service = FragmentFix.class)
public class FragmentFix {

	@Activate
	public void activate() {
		List<Company> companies = _companyLocalService.getCompanies();

		for (Company company : companies) {
			try {
				_fixPermissions(company.getCompanyId());
			}
			catch (PortalException e) {
				_log.error(e.getMessage(), e);
			}
		}
	}

	private void _fixPermissions(long companyId)
		throws PortalException {

		Role guestRole = _roleLocalService.getRole(
			companyId, RoleConstants.GUEST);

		PermissionChecker permissionCheckerBackup =
			PermissionThreadLocal.getPermissionChecker();

		PermissionThreadLocal.setPermissionChecker(
			_permissionCheckerFinder.findAdministratorPermissionChecker(companyId));

		try {

			List<DDMTemplate> ddmTemplates = _ddmTemplateLocalService.getDDMTemplates(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (DDMTemplate ddmTemplate : ddmTemplates) {
				long ddmTemplateId = ddmTemplate.getTemplateId();

				String[] actionKeys = {ActionKeys.VIEW};

				if (ddmTemplateId == 41379) {

					_log.info("Adding Guest VIEW permission for ddmTemplateId=" + ddmTemplateId);

					_grantPermissions(companyId, guestRole.getRoleId(), DDMTemplate.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL, String.valueOf(ddmTemplateId), actionKeys);
				}
				else {
					// _log.info("Disregard ddmTemplateId=" + ddmTemplateId);
				}
			}

		} catch (Throwable t) {
			_log.error(t, t);
		}

		PermissionThreadLocal.setPermissionChecker(permissionCheckerBackup);
	}


	private void _grantPermissions(
		long companyId, long roleId, String resourceId, int scope,
		String primKey, String[] actionKeys)
		throws PortalException {

		for (int i = 0; i < actionKeys.length; i++) {
			_resourceActionLocalService.checkResourceActions(
				resourceId, Arrays.asList(actionKeys));
			_resourcePermissionLocalService.setResourcePermissions(
				companyId, resourceId, scope, primKey, roleId, actionKeys);
		}
	}

	@Reference
	private ResourceActionLocalService _resourceActionLocalService;

	@Reference
	private ResourcePermissionLocalService _resourcePermissionLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		FragmentFix.class);

	@Reference
	private CompanyLocalService _companyLocalService;

	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
	private ModuleServiceLifecycle _portalInitialized;

	@Reference
	private DDMTemplateLocalService _ddmTemplateLocalService;

	@Reference
	private PermissionCheckerFinder _permissionCheckerFinder;

	@Reference
	private RoleLocalService _roleLocalService;
}