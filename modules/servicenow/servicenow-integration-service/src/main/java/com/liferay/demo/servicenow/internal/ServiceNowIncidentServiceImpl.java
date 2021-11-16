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

import com.liferay.demo.servicenow.Incident;
import com.liferay.demo.servicenow.ServiceNowConfiguration;
import com.liferay.demo.servicenow.ServiceNowIncidentService;
import com.liferay.demo.servicenow.ServiceNowToken;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.IOException;

import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Neil Griffin
 */
@Component(
	configurationPid = "com.liferay.demo.servicenow.ServiceNowConfiguration",
	immediate = true, service = ServiceNowIncidentService.class
)
public class ServiceNowIncidentServiceImpl
	implements ServiceNowIncidentService {

	@Activate
	public void activate(Map<Object, Object> properties) {
		_serviceNowConfiguration = ConfigurableUtil.createConfigurable(
			ServiceNowConfiguration.class, properties);
	}

	@Override
	public Optional<Incident> getIncident(
			ServiceNowToken serviceNowToken, String incidentId)
		throws IOException {

		// TODO: Should be done with a REST API call, asking for a single record
		// based on the incidentId.

		List<Incident> incidents = getIncidents(
			serviceNowToken, StringPool.BLANK, true, "number");

		for (Incident incident : incidents) {
			if (Objects.equals(incident.getIncidentId(), incidentId)) {
				return Optional.of(incident);
			}
		}

		return Optional.empty();
	}

	@Override
	public List<Incident> getIncidents(
			ServiceNowToken serviceNowToken, String category,
			boolean orderByAscending, String orderByCol)
		throws IOException {

		List<Incident> incidents = new ArrayList<>();

		String orderBy = "ORDERBY";

		if (!orderByAscending) {
			orderBy = "ORDERBYDESC";
		}

		HttpRequest httpRequest;

		String query = ParamBuilder.newBuilder(
		).add(
			"sysparm_limit", URLEncoder.encode("20", "UTF-8")
		).add(
			"sysparm_query",
			"active=true^" + orderBy + URLEncoder.encode(orderByCol, "UTF-8")
		).addIfNotEmpty(
			"category", URLEncoder.encode(category, "UTF-8")
		).build(
			ParamBuilder.Type.FORM_URLENCODED
		);

		System.err.println("!@#$ query=" + query);

		try {
			httpRequest = HttpRequest.newBuilder(
			).uri(
				new URI(
					"https", _serviceNowConfiguration.restApiHostname(),
					"/api/now/table/incident", query, null)
			).setHeader(
				"Authorization", "Basic " + serviceNowToken.getAccessToken()
			).setHeader(
				"Accept", "application/json"
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

		String responseBody = ServiceNowResponseUtil.trim(httpResponse.body());

		_log.trace("responseBody=" + responseBody);

		try {
			JSONObject jsonObject = _jsonFactory.createJSONObject(responseBody);

			JSONArray records = jsonObject.getJSONArray("result");

			if (records == null) {
				throw new JSONException(
					"Array named 'Incidents' not found in response JSON");
			}

			int length = records.length();

			for (int i = 0; i < length; i++) {
				incidents.add(
					ServiceNowIncidentFactory.create(
						category, records.getJSONObject(i),
						_serviceNowConfiguration.restApiHostname()));
			}
		}
		catch (JSONException jsone) {
			throw new IOException(jsone);
		}

		return incidents;
	}

	@Override
	public Incident newIncident() {
		return new IncidentImpl();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ServiceNowIncidentServiceImpl.class);

	@Reference
	private JSONFactory _jsonFactory;

	private volatile ServiceNowConfiguration _serviceNowConfiguration;

}