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
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.jira.integration.JiraConfiguration;
import com.liferay.jira.integration.JiraToken;
import com.liferay.jira.integration.JiraTokenService;

import java.io.IOException;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Neil Griffin
 */
@Component(
	configurationPid = "com.liferay.jira.integration.JiraConfiguration",
	immediate = true, service = JiraTokenService.class
)
public class JiraTokenServiceImpl implements JiraTokenService {

	@Activate
	public void activate(Map<Object, Object> properties) {
		_jiraConfiguration = ConfigurableUtil.createConfigurable(
			JiraConfiguration.class, properties);
	}

	@Override
	public JiraToken getToken() throws IOException {
		/*
		String requestBody = ParamBuilder.newBuilder(
		).add(
			"grant_type", "password"
		).add(
			"client_id",
			URLEncoder.encode(_jiraConfiguration.authClientId(), "UTF-8")
		).add(
			"client_secret",
			URLEncoder.encode(
				_jiraConfiguration.authClientSecret(), "UTF-8")
		).add(
			"username",
			URLEncoder.encode(_jiraConfiguration.authUsername(), "UTF-8")
		).add(
			"password",
			URLEncoder.encode(_jiraConfiguration.authPassword(), "UTF-8")
		).build(
			ParamBuilder.Type.FORM_URLENCODED
		);

		_log.trace("requestBody={}", requestBody);

		HttpRequest.BodyPublisher bodyPublisher =
			HttpRequest.BodyPublishers.ofString(requestBody);

		HttpRequest httpRequest;

		try {
			httpRequest = HttpRequest.newBuilder(
			).uri(
				new URI(
					"https", _jiraConfiguration.restApiHostname(),
					"/services/oauth2/token", null)
			).setHeader(
				"Content-Type", "application/x-www-form-urlencoded"
			).POST(
				bodyPublisher
			).build();

			_log.trace("httpRequest headers={}", httpRequest.headers());

			_log.trace("httpRequest={}", httpRequest);
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

		String responseBody = httpResponse.body();

		_log.trace("responseBody={}", responseBody);

		String accessToken = null;

		JSONObject jsonObject = null;

		try {
			jsonObject = _jsonFactory.createJSONObject(responseBody);

			if (jsonObject.has("error")) {
				throw new IOException(jsonObject.toJSONString());
			}

			if (jsonObject.has("access_token")) {
				accessToken = jsonObject.getString("access_token");

				if (Validator.isNull(accessToken)) {
					throw new IOException("Empty access_token");
				}
			}
		}
		catch (JSONException jsone) {
			jsone.printStackTrace();
		}

		JiraToken jiraToken = new JiraTokenImpl(
			accessToken, jsonObject.getString("id"),
			jsonObject.getString("instance_url"),
			jsonObject.getLong("issued_at"), jsonObject.getString("signature"));
		 */

		// TODO
		JiraToken jiraToken = new JiraTokenImpl(
			null, null, null, 0L, null);

		_log.debug("jiraToken=[" + jiraToken + "]");

		return jiraToken;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		JiraTokenServiceImpl.class);

	@Reference
	private JSONFactory _jsonFactory;

	private volatile JiraConfiguration _jiraConfiguration;

}