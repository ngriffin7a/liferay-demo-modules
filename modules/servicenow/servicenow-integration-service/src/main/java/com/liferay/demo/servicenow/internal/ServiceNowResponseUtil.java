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

/**
 * @author Neil Griffin
 */
public class ServiceNowResponseUtil {

	public static String trim(String response) {

		// Strip the leading "[" and trailing "]" characters which prevent the
		// response from being parsed as JSON.

		if (response.startsWith("[")) {
			response = response.substring(1);
		}

		if (response.endsWith("]")) {
			response = response.substring(0, response.length() - 1);
		}

		return response;
	}

}