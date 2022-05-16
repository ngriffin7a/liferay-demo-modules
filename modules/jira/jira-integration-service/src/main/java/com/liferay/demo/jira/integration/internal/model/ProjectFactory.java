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

import com.liferay.demo.jira.integration.internal.model.ProjectImpl;
import com.liferay.demo.jira.integration.model.Project;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Neil Griffin
 */
public class ProjectFactory {

	public static Project create(JSONObject jsonObject)
		throws JSONException {

		JSONObject avatarsJSONObject = jsonObject.getJSONObject("avatarUrls");
		Map<String, String> avatarURLs = new HashMap<>();

		avatarURLs.put("16x16", avatarsJSONObject.getString("16x16"));
		avatarURLs.put("24x24", avatarsJSONObject.getString("24x24"));
		avatarURLs.put("32x32", avatarsJSONObject.getString("32x32"));
		avatarURLs.put("48x48", avatarsJSONObject.getString("48x48"));

		return new ProjectImpl(
			jsonObject.getString("id"), jsonObject.getString("key"),
			jsonObject.getString("name"), avatarURLs);
	}

}