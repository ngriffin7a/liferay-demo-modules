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

package com.liferay.demo.jira.integration.internal.util;

import com.liferay.portal.kernel.util.Validator;

import java.text.DateFormat;
import java.text.ParseException;

import java.util.Date;

/**
 * @author Neil Griffin
 */
public class JiraDateUtil {

	public static final String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

	public static Date getDate(DateFormat dateFormat, String date)
		throws ParseException {

		if (Validator.isNull(date)) {
			return new Date();
		}

		return dateFormat.parse(date);
	}

}