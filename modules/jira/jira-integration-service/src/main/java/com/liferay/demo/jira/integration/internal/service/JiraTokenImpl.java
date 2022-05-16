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

import com.liferay.demo.jira.integration.service.JiraToken;

import java.io.Serializable;

import java.text.MessageFormat;

/**
 * @author Neil Griffin
 */
public class JiraTokenImpl implements JiraToken, Serializable {

	public JiraTokenImpl(
		String apiToken, String basicAuthorization, String user) {

		_apiToken = apiToken;
		_basicAuthorization = basicAuthorization;
		_user = user;
	}

	@Override
	public String getApiToken() {
		return _apiToken;
	}

	@Override
	public String getBasicAuthorization() {
		return _basicAuthorization;
	}

	@Override
	public String getUser() {
		return _user;
	}

	@Override
	public String toString() {
		return MessageFormat.format(
			"apiToken=[{0}] basicAuthorization=[{1}] user=[{2}]", _apiToken,
			_basicAuthorization, _user);
	}

	private static final long serialVersionUID = 3651573117837686858L;

	private String _apiToken;
	private String _basicAuthorization;
	private String _user;

}