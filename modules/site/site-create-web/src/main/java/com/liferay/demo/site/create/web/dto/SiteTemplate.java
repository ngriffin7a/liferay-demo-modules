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

package com.liferay.demo.site.create.web.dto;

/**
 * @author Neil Griffin
 */
public class SiteTemplate {

	public SiteTemplate(long id, String name) {
		_id = id;
		_name = name;
	}

	public long getId() {
		return _id;
	}

	public String getName() {
		return _name;
	}

	public void setId(long siteTemplateId) {
		_id = siteTemplateId;
	}

	public void setName(String siteName) {
		_name = siteName;
	}

	private long _id;
	private String _name;

}