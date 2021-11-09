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

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.jira.integration.JiraIssue;
import com.liferay.jira.integration.JiraIssueService;
import com.liferay.jira.integration.JiraConfiguration;
import com.liferay.jira.integration.JiraToken;

import java.io.IOException;

import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.net.http.HttpClient;

/**
 * @author Neil Griffin
 */
@Component(
	configurationPid = "com.liferay.jira.integration.JiraConfiguration",
	immediate = true, service = JiraIssueService.class
)
public class JiraIssueServiceImpl implements JiraIssueService {

	@Activate
	public void activate(Map<Object, Object> properties) {
		_jiraConfiguration = ConfigurableUtil.createConfigurable(
			JiraConfiguration.class, properties);
	}

	@Override
	public List<JiraIssue> getIssues(
		JiraToken jiraToken, String projectId)
		throws IOException {

		List<JiraIssue> jiraIssues = new ArrayList<>();

		HttpRequest httpRequest;

		try {
			httpRequest = HttpRequest.newBuilder(
			).uri(
				new URI(
					"https", _jiraConfiguration.restApiHostname(),
					"/rest/api/2/search",
					StringBundler.concat(
						"jql=project=" + URLEncoder.encode(projectId, "UTF-8")),
					null)
			// ).setHeader(
				// "Authorization", "Bearer " + jiraToken.getAccessToken()
			).setHeader(
				"Content-Type", "application/json"
			).GET(
			).build();

			_log.trace("headers=" + httpRequest.headers());
			_log.trace("httpRequest=" + httpRequest);
		}
		catch (URISyntaxException urise) {
			throw new IOException(urise);
		}

		HttpResponse<String> httpResponse;

		try {
			HttpClient httpClient = HttpClient.newBuilder(
			).proxy(
				ProxySelector.getDefault()
			).version(
				HttpClient.Version.HTTP_1_1
			).build();

			httpResponse = httpClient.send(
				httpRequest, HttpResponse.BodyHandlers.ofString());
		}
		catch (InterruptedException ie) {
			throw new IOException(ie);
		}

		String responseBody = JiraResponseUtil.trim(httpResponse.body());

		_log.trace("responseBody=" + responseBody);

		try {
			JSONObject jsonObject = _jsonFactory.createJSONObject(responseBody);

			JSONArray records = jsonObject.getJSONArray("issues");

			if (records == null) {
				throw new JSONException(
					"Array named 'issues' not found in response JSON");
			}

			int length = records.length();

			for (int i = 0; i < length; i++) {
				jiraIssues.add(
					JiraIssueFactory.create(projectId, records.getJSONObject(i)));
			}
		}
		catch (JSONException jsone) {
			throw new IOException(jsone);
		}

		return jiraIssues;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		JiraIssueServiceImpl.class);

	@Reference
	private JSONFactory _jsonFactory;

	private volatile JiraConfiguration _jiraConfiguration;

}