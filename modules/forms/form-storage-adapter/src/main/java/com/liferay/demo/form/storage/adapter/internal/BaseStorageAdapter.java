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
import com.liferay.dynamic.data.mapping.storage.DDMStorageAdapterDeleteRequest;
import com.liferay.dynamic.data.mapping.storage.DDMStorageAdapterDeleteResponse;
import com.liferay.dynamic.data.mapping.storage.DDMStorageAdapterGetRequest;
import com.liferay.dynamic.data.mapping.storage.DDMStorageAdapterGetResponse;
import com.liferay.dynamic.data.mapping.storage.DDMStorageAdapterSaveRequest;
import com.liferay.dynamic.data.mapping.storage.DDMStorageAdapterSaveResponse;
import com.liferay.dynamic.data.mapping.storage.DDMStorageAdapterTracker;
import com.liferay.dynamic.data.mapping.validator.DDMFormValuesValidationException;
import com.liferay.portal.kernel.json.JSONException;

/**
 * @author Neil Griffin
 */
public abstract class BaseStorageAdapter<T> implements DDMStorageAdapter {

	@Override
	public DDMStorageAdapterDeleteResponse delete(
			DDMStorageAdapterDeleteRequest ddmStorageAdapterDeleteRequest)
		throws StorageException {

		DDMStorageAdapter parentDDMStorageAdapter =
			getParentDDMStorageAdapter();

		return parentDDMStorageAdapter.delete(ddmStorageAdapterDeleteRequest);
	}

	@Override
	public DDMStorageAdapterGetResponse get(
			DDMStorageAdapterGetRequest ddmStorageAdapterGetRequest)
		throws StorageException {

		DDMStorageAdapter parentDDMStorageAdapter =
			getParentDDMStorageAdapter();

		return parentDDMStorageAdapter.get(ddmStorageAdapterGetRequest);
	}

	@Override
	public DDMStorageAdapterSaveResponse save(
			DDMStorageAdapterSaveRequest ddmStorageAdapterSaveRequest)
		throws StorageException {

		return null;
	}

	protected abstract DDMStorageAdapterTracker getDDMStorageAdapterTracker();

	protected DDMStorageAdapter getParentDDMStorageAdapter() {
		DDMStorageAdapterTracker ddmStorageAdapterTracker =
			getDDMStorageAdapterTracker();

		return ddmStorageAdapterTracker.getDDMStorageAdapter("json");
	}

	protected abstract T parse(DDMFormValues ddmFormValues)
		throws JSONException;

	protected abstract void validate(T t)
		throws DDMFormValuesValidationException;

}