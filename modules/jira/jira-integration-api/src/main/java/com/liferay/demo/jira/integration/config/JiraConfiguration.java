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

package com.liferay.demo.jira.integration.config;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Neil Griffin
 */
@ExtendedObjectClassDefinition(category = "jira-integration")
@Meta.OCD(
	id = "com.liferay.demo.jira.integration.config.JiraConfiguration",
	localization = "content/Language"
)
public interface JiraConfiguration {

	@Meta.AD(deflt = "", name = "api-token", required = false)
	public String apiToken();

	@Meta.AD(deflt = "", name = "hostname", required = false)
	public String hostname();

	@Meta.AD(deflt = "", name = "projectKey", required = false)
	public String projectKey();

	@Meta.AD(deflt = "", name = "user", required = false)
	public String user();

}