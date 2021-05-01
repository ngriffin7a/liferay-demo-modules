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

package com.liferay.demo.servicenow.internal;

import com.liferay.demo.servicenow.Incident;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

/**
 * @author Neil Griffin
 */
public class ServiceNowIncidentFactory {

	public static Incident create(
			String category, JSONObject jsonObject, String hostname)
		throws JSONException {

		Date dueDate = null;
		String dueDateValue = jsonObject.getString("due_date");

		if (Validator.isNotNull(dueDateValue)) {
			DateFormat dateFormat = new SimpleDateFormat(
				ServiceNowDateUtil.DATE_PATTERN);

			try {
				dueDate = ServiceNowDateUtil.getDate(dateFormat, dueDateValue);
			}
			catch (ParseException parseException) {
				throw new JSONException(parseException);
			}
		}

		String number = jsonObject.getString("number");
		String shortDescription = jsonObject.getString("short_description");
		String subcategory = jsonObject.getString("subcategory");

		String sysId = jsonObject.getString("sys_id");

		String href =
			"https://" + hostname +
				"/nav_to.do?uri=%2Fincident.do%3Fsys_id%3D" + sysId;

		return new IncidentImpl(
			sysId, category, dueDate, href, number, shortDescription,
			subcategory);
	}

}