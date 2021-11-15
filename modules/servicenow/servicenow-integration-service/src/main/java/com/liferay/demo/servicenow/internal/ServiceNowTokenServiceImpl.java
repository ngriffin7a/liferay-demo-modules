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

import com.liferay.demo.servicenow.ServiceNowConfiguration;
import com.liferay.demo.servicenow.ServiceNowToken;
import com.liferay.demo.servicenow.ServiceNowTokenService;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;

import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.nio.charset.StandardCharsets;

import java.util.Base64;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Neil Griffin
 */
@Component(
	configurationPid = "com.liferay.demo.servicenow.ServiceNowConfiguration",
	immediate = true, service = ServiceNowTokenService.class
)
public class ServiceNowTokenServiceImpl implements ServiceNowTokenService {

	@Activate
	public void activate(Map<Object, Object> properties) {
		_serviceNowConfiguration = ConfigurableUtil.createConfigurable(
			ServiceNowConfiguration.class, properties);
	}

	@Override
	public ServiceNowToken getToken() throws IOException {
		ServiceNowToken serviceNowToken = null;

		String basicUsername = _serviceNowConfiguration.basicUsername();
		String basicPassword = _serviceNowConfiguration.basicPassword();

		if (Validator.isNotNull(basicUsername) &&
			Validator.isNotNull(basicPassword)) {

			String basicAuth = basicUsername + ":" + basicPassword;
			Base64.Encoder encoder = Base64.getEncoder();

			String encodedBasicAuth = encoder.encodeToString(
				basicAuth.getBytes(StandardCharsets.UTF_8));

			serviceNowToken = new ServiceNowTokenImpl(
				encodedBasicAuth, null, null, 0L, null);
		}

		// TODO: The OAuth code below was basically copied from the JIRA
		// integration modules and hasn't been tested with ServiceNow.

		else {
			String requestBody = ParamBuilder.newBuilder(
			).add(
				"grant_type", "password"
			).add(
				"client_id",
				URLEncoder.encode(
					_serviceNowConfiguration.oauthClientId(), "UTF-8")
			).add(
				"client_secret",
				URLEncoder.encode(
					_serviceNowConfiguration.oauthClientSecret(), "UTF-8")
			).add(
				"username",
				URLEncoder.encode(
					_serviceNowConfiguration.oauthUsername(), "UTF-8")
			).add(
				"password",
				URLEncoder.encode(
					_serviceNowConfiguration.oauthPassword(), "UTF-8")
			).build(
				ParamBuilder.Type.FORM_URLENCODED
			);

			_log.trace("requestBody=" + requestBody);

			HttpRequest.BodyPublisher bodyPublisher =
				HttpRequest.BodyPublishers.ofString(requestBody);

			HttpRequest httpRequest;

			try {
				httpRequest = HttpRequest.newBuilder(
				).uri(
					new URI(
						"https", _serviceNowConfiguration.loginApiHostname(),
						"/services/oauth2/token", null)
				).setHeader(
					"Content-Type", "application/x-www-form-urlencoded"
				).POST(
					bodyPublisher
				).build();

				_log.trace("httpRequest headers=" + httpRequest.headers());

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

			String responseBody = httpResponse.body();

			_log.trace("responseBody=" + responseBody);

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

			serviceNowToken = new ServiceNowTokenImpl(
				accessToken, jsonObject.getString("id"),
				jsonObject.getString("instance_url"),
				jsonObject.getLong("Incidentd_at"),
				jsonObject.getString("signature"));
		}

		_log.debug("serviceNowToken=" + serviceNowToken);

		return serviceNowToken;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ServiceNowTokenServiceImpl.class);

	@Reference
	private JSONFactory _jsonFactory;

	private volatile ServiceNowConfiguration _serviceNowConfiguration;

}