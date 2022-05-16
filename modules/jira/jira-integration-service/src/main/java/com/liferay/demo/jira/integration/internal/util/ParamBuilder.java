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

package com.liferay.demo.jira.integration.internal.util;

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