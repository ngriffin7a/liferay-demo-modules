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

import com.liferay.demo.form.storage.adapter.BaseStorageAdapter;
import com.liferay.demo.form.storage.adapter.DDMSerializer;
import com.liferay.dynamic.data.mapping.exception.StorageException;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.dynamic.data.mapping.storage.DDMStorageAdapter;
import com.liferay.dynamic.data.mapping.storage.DDMStorageAdapterSaveRequest;
import com.liferay.dynamic.data.mapping.storage.DDMStorageAdapterSaveResponse;
import com.liferay.dynamic.data.mapping.storage.DDMStorageAdapterTracker;
import com.liferay.dynamic.data.mapping.validator.DDMFormValuesValidationException;
import com.liferay.dynamic.data.mapping.validator.DDMFormValuesValidator;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Country;
import com.liferay.portal.kernel.model.CountryConstants;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Region;
import com.liferay.portal.kernel.model.RegionConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.CountryService;
import com.liferay.portal.kernel.service.RegionService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Neil Griffin
 */
@Component(
	immediate = true, property = "ddm.storage.adapter.type=Demo",
	service = DDMStorageAdapter.class
)
public class DeveloperStorageAdapter extends BaseStorageAdapter<Developer> {

	@Override
	public DDMStorageAdapterSaveResponse save(
			DDMStorageAdapterSaveRequest ddmStorageAdapterSaveRequest)
		throws StorageException {

		DDMStorageAdapter parentDDMStorageAdapter =
			getParentDDMStorageAdapter();

		// Save the form the normal way Liferay Forms does it.

		DDMStorageAdapterSaveResponse ddmStorageAdapterSaveResponse =
			parentDDMStorageAdapter.save(ddmStorageAdapterSaveRequest);

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		boolean validateDDMFormValues = GetterUtil.getBoolean(
			serviceContext.getAttribute("validateDDMFormValues"), true);

		if (!validateDDMFormValues) {
			return ddmStorageAdapterSaveResponse;
		}

		DDMFormValues ddmFormValues =
			ddmStorageAdapterSaveRequest.getDDMFormValues();

		_ddmFormValuesValidator.validate(ddmFormValues);

		Developer developer;

		try {
			developer = parse(ddmFormValues);
		}
		catch (JSONException jsone) {
			throw new StorageException(jsone);
		}

		validate(developer);

		try {
			Organization organization =
				_developerOrganizationService.addOrganization(
					developer, serviceContext);

			_log.info(
				"Added organizationId: " + organization.getOrganizationId());

			User user = _developerUserService.addUser(
				developer, organization,
				new String[] {"Organization Developer"}, serviceContext);

			_log.info("Added Liferay User");
			_log.info("  userId=" + user.getUserId());
			_log.info("  screenName=" + user.getScreenName());
			_log.info("  password=" + user.getPasswordUnencrypted());
		}
		catch (Exception e) {
			_log.error(e.getMessage(), e);

			throw new StorageException(e);
		}

		return ddmStorageAdapterSaveResponse;
	}

	@Override
	protected DDMStorageAdapterTracker getDDMStorageAdapterTracker() {
		return _ddmStorageAdapterTracker;
	}

	@Override
	protected Developer parse(DDMFormValues ddmFormValues)
		throws JSONException {

		JSONObject jsonObject = _ddmSerializer.getJSONObject(ddmFormValues);

		_log.trace("formValuesAsJSON={}", jsonObject.toJSONString());

		Developer developer = new Developer();

		developer.setAddressLine1(jsonObject.getString("AddressLine1"));
		developer.setAddressLine2(jsonObject.getString("AddressLine2"));
		developer.setCity(jsonObject.getString("City"));
		developer.setCountryId(
			_getCountryId(stripJSONSyntax(jsonObject.getString("Country"))));
		developer.setCompanyName(jsonObject.getString("CompanyName"));
		developer.setEmailAddress(jsonObject.getString("EmailAddress"));
		developer.setFirstName(jsonObject.getString("FirstName"));
		developer.setJobTitle(jsonObject.getString("JobTitle"));
		developer.setPostalCode(jsonObject.getString("PostalCode"));

		// Unable to detect region outside the US at this time

		developer.setRegionId(
			_getRegionId(stripJSONSyntax(jsonObject.getString("Region"))));
		developer.setLastName(jsonObject.getString("LastName"));
		developer.setMobilePhone(jsonObject.getString("MobilePhone"));
		developer.setWebsiteURL(jsonObject.getString("WebsiteURL"));

		return developer;
	}

	@Override
	protected void validate(Developer developer)
		throws DDMFormValuesValidationException {

		if (!Validator.isEmailAddress(developer.getEmailAddress())) {
			_log.error("Email address is invalid");

			throw new DDMFormValuesValidationException.MustSetValidValue(
				"Email Address");
		}
	}

	private long _getCountryId(String countryNameCurrentValue) {
		List<Country> countries = _countryService.getCountries();

		long countryId = CountryConstants.DEFAULT_COUNTRY_ID;

		for (Country country : countries) {
			String nameCurrentValue = country.getNameCurrentValue();

			if (nameCurrentValue.equals(countryNameCurrentValue)) {
				countryId = country.getCountryId();

				break;
			}
		}

		return countryId;
	}

	private long _getRegionId(String regionName) {
		List<Region> regions = _regionService.getRegions();

		long regionId = RegionConstants.DEFAULT_REGION_ID;

		for (Region region : regions) {
			String name = region.getName();

			if (name.equals(regionName)) {
				regionId = region.getRegionId();

				break;
			}
		}

		return regionId;
	}

	private static final Logger _log = LoggerFactory.getLogger(
		DeveloperStorageAdapter.class);

	@Reference
	private CountryService _countryService;

	@Reference
	private DDMFormValuesValidator _ddmFormValuesValidator;

	@Reference
	private DDMSerializer _ddmSerializer;

	@Reference
	private DDMStorageAdapterTracker _ddmStorageAdapterTracker;

	@Reference
	private DeveloperOrganizationService _developerOrganizationService;

	@Reference
	private DeveloperUserService _developerUserService;

	@Reference
	private RegionService _regionService;

}