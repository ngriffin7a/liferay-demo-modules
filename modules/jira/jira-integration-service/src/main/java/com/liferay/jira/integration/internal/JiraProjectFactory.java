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

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.jira.integration.JiraProject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Neil Griffin
 */
public class JiraProjectFactory {

	public static JiraProject create(JSONObject jsonObject)
		throws JSONException {

		JSONObject avatarsJSONObject = jsonObject.getJSONObject("avatarUrls");
		Map<String, String> avatarURLs = new HashMap<>();
		avatarURLs.put("48x48", avatarsJSONObject.getString("48x48"));
		avatarURLs.put("32x32", avatarsJSONObject.getString("32x32"));
		avatarURLs.put("24x24", avatarsJSONObject.getString("24x24"));
		avatarURLs.put("16x16", avatarsJSONObject.getString("16x16"));
		return new JiraProjectImpl(
			jsonObject.getString("id"), jsonObject.getString("key"),
			jsonObject.getString("name"), avatarURLs);
	}

}