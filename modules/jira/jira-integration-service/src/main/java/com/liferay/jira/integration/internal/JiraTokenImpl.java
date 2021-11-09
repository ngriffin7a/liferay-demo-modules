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

import com.liferay.jira.integration.JiraToken;

import java.io.Serializable;

import java.text.MessageFormat;

/**
 * @author Neil Griffin
 */
public class JiraTokenImpl implements JiraToken, Serializable {

	public JiraTokenImpl(
		String accessToken, String id, String instanceURL, long issuedAt,
		String signature) {

		_accessToken = accessToken;
		_id = id;
		_instanceURL = instanceURL;
		_issuedAt = issuedAt;
		_signature = signature;
	}

	@Override
	public String getAccessToken() {
		return _accessToken;
	}

	@Override
	public String getId() {
		return _id;
	}

	@Override
	public String getInstanceURL() {
		return _instanceURL;
	}

	@Override
	public long getIssuedAt() {
		return _issuedAt;
	}

	@Override
	public String getSignature() {
		return _signature;
	}

	@Override
	public String toString() {
		return MessageFormat.format(
			"accessToken=[{0}] id=[{1}] instanceURL=[{2}] issuedAt=[{3}] " +
				"signature=[{4}]",
			_accessToken, _id, _instanceURL, _issuedAt, _signature);
	}

	private static final long serialVersionUID = 3651573117837686858L;

	private String _accessToken;
	private String _id;
	private String _instanceURL;
	private long _issuedAt;
	private String _signature;

}