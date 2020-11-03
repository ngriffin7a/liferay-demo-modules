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

import com.liferay.dynamic.data.mapping.io.DDMFormValuesSerializer;
import com.liferay.dynamic.data.mapping.io.DDMFormValuesSerializerSerializeRequest;
import com.liferay.dynamic.data.mapping.io.DDMFormValuesSerializerSerializeResponse;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Neil Griffin
 */
@Component(service = DDMSerializer.class)
public class DDMSerializer {

	public JSONObject getJSONObject(DDMFormValues ddmFormValues)
		throws JSONException {

		DDMFormValuesSerializerSerializeRequest.Builder builder =
			DDMFormValuesSerializerSerializeRequest.Builder.newBuilder(
				ddmFormValues);

		DDMFormValuesSerializerSerializeResponse
			ddmFormValuesSerializerSerializeResponse =
				_jsonDDMFormValuesSerializer.serialize(builder.build());

		return _ddm2JSONObject(
			ddmFormValuesSerializerSerializeResponse.getContent());
	}

	private JSONObject _ddm2JSONObject(String sourceJSON) throws JSONException {
		JSONObject sourceJSONObject = _jsonFactory.createJSONObject(sourceJSON);

		JSONObject transformedJSONObject = _jsonFactory.createJSONObject();

		String languageId = sourceJSONObject.getString("defaultLanguageId");

		JSONArray fieldsJSONArray = sourceJSONObject.getJSONArray(
			"fieldValues");

		for (int i = 0; i < fieldsJSONArray.length(); i++) {
			JSONObject jsonObject = fieldsJSONArray.getJSONObject(i);

			JSONObject fieldValues = jsonObject.getJSONObject("value");

			transformedJSONObject.put(
				jsonObject.getString("name"),
				fieldValues.getString(languageId));
		}

		return transformedJSONObject;
	}

	@Reference(target = "(ddm.form.values.serializer.type=json)")
	private DDMFormValuesSerializer _jsonDDMFormValuesSerializer;

	@Reference
	private JSONFactory _jsonFactory;

}