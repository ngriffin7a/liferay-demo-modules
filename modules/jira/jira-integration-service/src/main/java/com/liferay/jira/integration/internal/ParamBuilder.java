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

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Neil Griffin
 */
public class ParamBuilder {

	public static ParamBuilder newBuilder() {
		return new ParamBuilder();
	}

	public ParamBuilder add(String name, String value) {
		_params.put(name, new String[] {value});

		return this;
	}

	public ParamBuilder add(String name, String[] value) {
		_params.put(name, value);

		return this;
	}

	public String build(Type type) {
		if (type == Type.FORM_URLENCODED) {
			return _buildFormURLEncoded();
		}

		throw new UnsupportedOperationException();
	}

	public enum Type {

		FORM_URLENCODED, MULTIPART_FORM_DATA

	}

	private ParamBuilder() {
		_params = new LinkedHashMap<>();
	}

	private String _buildFormURLEncoded() {
		StringBuilder sb = new StringBuilder();

		boolean first = true;

		for (Map.Entry<String, String[]> entry : _params.entrySet()) {
			String[] values = entry.getValue();

			for (String value : values) {
				if (first) {
					first = false;
				}
				else {
					sb.append("&");
				}

				sb.append(entry.getKey());
				sb.append("=");

				if (value != null) {
					sb.append(value.trim());
				}
			}
		}

		return sb.toString();
	}

	private Map<String, String[]> _params;

}