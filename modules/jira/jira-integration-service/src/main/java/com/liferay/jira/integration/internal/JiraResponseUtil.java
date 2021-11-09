/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 */

package com.liferay.jira.integration.internal;

/**
 * @author Neil Griffin
 */
public class JiraResponseUtil {

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