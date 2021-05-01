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

package com.liferay.demo.servicenow;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Neil Griffin
 */
@ExtendedObjectClassDefinition(category = "servicenow-integration")
@Meta.OCD(
	id = "com.liferay.demo.servicenow.ServiceNowConfiguration",
	localization = "content/Language"
)
public interface ServiceNowConfiguration {

	@Meta.AD(deflt = "", name = "basic-password", required = false)
	public String basicPassword();

	@Meta.AD(deflt = "admin", name = "basic-username", required = false)
	public String basicUsername();

	@Meta.AD(deflt = "", name = "oauth-client-id", required = false)
	public String oauthClientId();

	@Meta.AD(deflt = "", name = "oauth-client-secret", required = false)
	public String oauthClientSecret();

	@Meta.AD(deflt = "", name = "oauth-password", required = false)
	public String oauthPassword();

	@Meta.AD(deflt = "", name = "oauth-username", required = false)
	public String oauthUsername();

	@Meta.AD(
		deflt = "login.servicenow.com", name = "login-api-hostname",
		required = false
	)
	public String loginApiHostname();

	@Meta.AD(deflt = "", name = "rest-api-hostname", required = false)
	public String restApiHostname();

}