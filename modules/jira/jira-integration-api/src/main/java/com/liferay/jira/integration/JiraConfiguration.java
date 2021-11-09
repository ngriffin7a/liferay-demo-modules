package com.liferay.jira.integration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Neil Griffin
 */
@ExtendedObjectClassDefinition(category = "jira-integration")
@Meta.OCD(
	id = "com.liferay.jira.integration.JiraConfiguration",
	localization = "content/Language"
)
public interface JiraConfiguration {

	@Meta.AD(deflt = "", name = "auth-client-id", required = false)
	public String authClientId();

	@Meta.AD(deflt = "", name = "auth-client-secret", required = false)
	public String authClientSecret();

	@Meta.AD(deflt = "", name = "auth-password", required = false)
	public String authPassword();

	@Meta.AD(deflt = "auth-username", required = false)
	public String authUsername();

	@Meta.AD(
		deflt = "login.jira.com", name = "login-api-hostname",
		required = false
	)
	public String loginApiHostname();

	@Meta.AD(deflt = "", name = "rest-api-hostname", required = false)
	public String restApiHostname();

}