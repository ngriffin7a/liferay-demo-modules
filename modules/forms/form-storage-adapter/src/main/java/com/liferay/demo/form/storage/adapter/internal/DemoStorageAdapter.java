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

package com.liferay.demo.form.storage.adapter.internal;

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
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.util.GetterUtil;

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
public class DemoStorageAdapter extends BaseStorageAdapter<Demo> {

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

		Demo demo;

		try {
			demo = parse(ddmFormValues);
		}
		catch (JSONException jsone) {
			throw new StorageException(jsone);
		}

		validate(demo);

		try {
			// TOD: Custom storage (email, database, web service, etc.)
			_log.info("Saved Demo");
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
	protected Demo parse(DDMFormValues ddmFormValues) throws JSONException {
		JSONObject jsonObject = _ddmSerializer.getJSONObject(ddmFormValues);

		_log.trace("formValuesAsJSON={}", jsonObject.toJSONString());

		Demo demo = new Demo();

		demo.setFirstName(jsonObject.getString("FirstName"));
		demo.setFirstName(jsonObject.getString("LastName"));

		return demo;
	}

	@Override
	protected void validate(Demo demo) throws DDMFormValuesValidationException {
		/*
		if (!Validator.isUrl(demo.getWebsiteURL())) {
			_log.error("Website URL is invalid");

			throw new DDMFormValuesValidationException.MustSetValidValue(
				"Website URL");
		}
		 */
	}

	private static final Logger _log = LoggerFactory.getLogger(
		DemoStorageAdapter.class);

	@Reference
	private DDMFormValuesValidator _ddmFormValuesValidator;

	@Reference
	private DDMSerializer _ddmSerializer;

	@Reference
	private DDMStorageAdapterTracker _ddmStorageAdapterTracker;

}