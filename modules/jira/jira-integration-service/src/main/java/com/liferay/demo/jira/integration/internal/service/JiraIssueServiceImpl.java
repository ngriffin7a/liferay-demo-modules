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
import com.liferay.demo.jira.integration.internal.model.IssueFactory;
import com.liferay.demo.jira.integration.internal.model.IssueImpl;
import com.liferay.demo.jira.integration.internal.util.JiraResponseUtil;
import com.liferay.demo.jira.integration.model.Issue;
import com.liferay.demo.jira.integration.service.JiraIssueService;
import com.liferay.demo.jira.integration.service.JiraToken;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;

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
import java.util.Optional;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Neil Griffin
 */
@Component(
	configurationPid = "com.liferay.demo.jira.integration.config.JiraConfiguration",
	immediate = true, service = JiraIssueService.class
)
public class JiraIssueServiceImpl implements JiraIssueService {

	@Activate
	public void activate(Map<Object, Object> properties) {
		_jiraConfiguration = ConfigurableUtil.createConfigurable(
			JiraConfiguration.class, properties);
	}

	@Override
	public void createIssue(Issue issue) throws IOException {
		JSONObject jsonObject = _jsonFactory.createJSONObject();

		JSONObject fields = _jsonFactory.createJSONObject();

		JSONObject project = _jsonFactory.createJSONObject();

		project.put("key", issue.getProjectId());

		fields.put("project", project);

		fields.put("description", issue.getDescription());
		fields.put("summary", issue.getSummary());

		JSONObject issueType = _jsonFactory.createJSONObject();

		issueType.put("name", issue.getType());

		fields.put("issueType", issueType);

		jsonObject.put("fields", fields);

		String toJSONString = jsonObject.toJSONString();

		System.err.println("!@#$ create JSON=" + toJSONString);
	}

	@Override
	public Optional<Issue> getIssue(JiraToken jiraToken, String issueId)
		throws IOException {

		Issue issue = null;

		HttpRequest httpRequest;

		try {
			httpRequest = HttpRequest.newBuilder(
			).uri(
				new URI(
					"https", _jiraConfiguration.hostname(),
					StringBundler.concat(
						"/rest/api/3/issue/",
						URLEncoder.encode(issueId, "UTF-8")),
					null)
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

		String responseBody = JiraResponseUtil.trim(httpResponse.body());

		_log.trace("responseBody=" + responseBody);

		try {
			JSONObject jsonObject = _jsonFactory.createJSONObject(responseBody);

			issue = IssueFactory.create(issueId, jsonObject);
		}
		catch (JSONException jsone) {
			throw new IOException(jsone);
		}

		if (issue == null) {
			return Optional.empty();
		}

		return Optional.of(issue);
	}

	@Override
	public List<Issue> getIssues(JiraToken jiraToken, String projectId)
		throws IOException {

		List<Issue> issues = new ArrayList<>();

		HttpRequest httpRequest;

		try {
			httpRequest = HttpRequest.newBuilder(
			).uri(
				new URI(
					"https", _jiraConfiguration.hostname(),
					"/rest/api/2/search",
					StringBundler.concat(
						"jql=project=", URLEncoder.encode(projectId, "UTF-8")),
					null)
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
				issues.add(
					IssueFactory.create(projectId, records.getJSONObject(i)));
			}
		}
		catch (JSONException jsone) {
			throw new IOException(jsone);
		}

		return issues;
	}

	@Override
	public Issue newIssue() {
		return new IssueImpl();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		JiraIssueServiceImpl.class);

	private volatile JiraConfiguration _jiraConfiguration;

	@Reference
	private JSONFactory _jsonFactory;

}