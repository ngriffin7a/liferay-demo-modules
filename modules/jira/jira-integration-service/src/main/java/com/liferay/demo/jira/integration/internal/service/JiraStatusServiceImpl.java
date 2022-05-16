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

package com.liferay.demo.jira.integration.internal.service;

import com.liferay.demo.jira.integration.config.JiraConfiguration;
import com.liferay.demo.jira.integration.internal.model.StatusFactory;
import com.liferay.demo.jira.integration.model.Status;
import com.liferay.demo.jira.integration.service.JiraStatusService;
import com.liferay.demo.jira.integration.service.JiraToken;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.IOException;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Neil Griffin
 */
@Component(
	configurationPid = "com.liferay.demo.jira.integration.config.JiraConfiguration",
	immediate = true, service = JiraStatusService.class
)
public class JiraStatusServiceImpl implements JiraStatusService {

	@Activate
	public void activate(Map<Object, Object> properties) {
		_jiraConfiguration = ConfigurableUtil.createConfigurable(
			JiraConfiguration.class, properties);
	}

	@Override
	public List<Status> getStatuses(JiraToken jiraToken)
		throws IOException {

		List<Status> statuses = new ArrayList<>();

		HttpRequest httpRequest;

		try {
			httpRequest = HttpRequest.newBuilder(
			).uri(
				new URI(
					"https", _jiraConfiguration.hostname(),
					"/rest/api/3/status", null)
			).setHeader(
				"Authorization", "Basic " + jiraToken.getBasicAuthorization()
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

		String responseBody = httpResponse.body();

		_log.trace("responseBody=" + responseBody);

		try {
			JSONArray jsonArray = _jsonFactory.createJSONArray(responseBody);

			int length = jsonArray.length();

			for (int i = 0; i < length; i++) {
				statuses.add(
					StatusFactory.create(jsonArray.getJSONObject(i)));
			}
		}
		catch (JSONException jsone) {
			throw new IOException(jsone);
		}

		return statuses;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		JiraStatusServiceImpl.class);

	private volatile JiraConfiguration _jiraConfiguration;

	@Reference
	private JSONFactory _jsonFactory;

}