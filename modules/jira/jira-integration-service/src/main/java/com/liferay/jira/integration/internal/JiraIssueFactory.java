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

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.jira.integration.JiraIssue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author Neil Griffin
 */
public class JiraIssueFactory {

	public static JiraIssue create(String projectId, JSONObject jsonObject)
		throws JSONException {

		DateFormat dateFormat = new SimpleDateFormat(
			JiraDateUtil.DATE_PATTERN);

		try {
			String status = "";
			JSONObject fieldsJSONObject = jsonObject.getJSONObject("fields");
			if (fieldsJSONObject != null) {
				JSONObject statusJSONObject = fieldsJSONObject.getJSONObject("status");
				if (statusJSONObject != null) {
					status = statusJSONObject.getString("name");
				}
			}
			return new JiraIssueImpl(
				jsonObject.getString("id"), projectId,
				jsonObject.getString("key"),
				JiraDateUtil.getDate(
					dateFormat, fieldsJSONObject.getString("created")),
				status, fieldsJSONObject.getString("summary"));
		}
		catch (ParseException pe) {
			throw new JSONException(pe);
		}
	}

}