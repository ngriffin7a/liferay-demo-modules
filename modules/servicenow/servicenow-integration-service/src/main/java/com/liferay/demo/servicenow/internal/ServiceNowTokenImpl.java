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

import com.liferay.demo.servicenow.ServiceNowToken;

import java.io.Serializable;

import java.text.MessageFormat;

/**
 * @author Neil Griffin
 */
public class ServiceNowTokenImpl implements Serializable, ServiceNowToken {

	public ServiceNowTokenImpl(
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
			"accessToken=[{0}] id=[{1}] instanceURL=[{2}] IssuedAt=[{3}] " +
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