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

package com.liferay.demo.jira.integration.internal.model;

import com.liferay.demo.jira.integration.internal.util.JiraDateUtil;
import com.liferay.demo.jira.integration.model.Issue;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author Neil Griffin
 */
public class IssueFactory {

	public static Issue create(String projectId, JSONObject jsonObject)
		throws JSONException {

		DateFormat dateFormat = new SimpleDateFormat(JiraDateUtil.DATE_PATTERN);

		try {
			String status = "";
			JSONObject fieldsJSONObject = jsonObject.getJSONObject("fields");

			if (fieldsJSONObject != null) {
				JSONObject statusJSONObject = fieldsJSONObject.getJSONObject(
					"status");

				if (statusJSONObject != null) {
					status = statusJSONObject.getString("name");
				}
			}

			String issueType = null;

			JSONObject issueTypeJSONObject = fieldsJSONObject.getJSONObject(
				"issuetype");

			if (issueTypeJSONObject != null) {
				issueType = issueTypeJSONObject.getString("name");
			}

			Issue issue = new IssueImpl();

			issue.setIssueId(jsonObject.getString("id"));
			issue.setProjectId(projectId);
			issue.setKey(jsonObject.getString("key"));
			issue.setCreatedDate(
				JiraDateUtil.getDate(
					dateFormat, fieldsJSONObject.getString("created")));
			issue.setDescription(fieldsJSONObject.getString("description"));
			issue.setSummary(fieldsJSONObject.getString("summary"));
			issue.setStatus(status);
			issue.setType(issueType);

			return issue;
		}
		catch (ParseException pe) {
			throw new JSONException(pe);
		}
	}

	public static Issue newIssue() {
		return new IssueImpl();
	}

}