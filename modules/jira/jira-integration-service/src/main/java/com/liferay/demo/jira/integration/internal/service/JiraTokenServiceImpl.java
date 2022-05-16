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
import com.liferay.demo.jira.integration.service.JiraToken;
import com.liferay.demo.jira.integration.service.JiraTokenService;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.nio.charset.StandardCharsets;

import java.util.Base64;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * @author Neil Griffin
 */
@Component(
	configurationPid = "com.liferay.demo.jira.integration.config.JiraConfiguration",
	immediate = true, service = JiraTokenService.class
)
public class JiraTokenServiceImpl implements JiraTokenService {

	@Activate
	public void activate(Map<Object, Object> properties) {
		_jiraConfiguration = ConfigurableUtil.createConfigurable(
			JiraConfiguration.class, properties);
	}

	@Override
	public JiraToken getToken() {
		String apiToken = _jiraConfiguration.apiToken();
		String user = _jiraConfiguration.user();

		String basicToken = user + ":" + apiToken;

		Base64.Encoder encoder = Base64.getEncoder();

		String basicAuthorization = encoder.encodeToString(
			basicToken.getBytes(StandardCharsets.UTF_8));

		JiraToken jiraToken = new JiraTokenImpl(
			apiToken, basicAuthorization, user);

		_log.debug("jiraToken=[" + jiraToken + "]");

		return jiraToken;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		JiraTokenServiceImpl.class);

	private volatile JiraConfiguration _jiraConfiguration;

}