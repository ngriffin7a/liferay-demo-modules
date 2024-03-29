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

package com.liferay.demo.jira.integration.web.internal.portlet;

import com.liferay.demo.jira.integration.service.JiraToken;
import com.liferay.demo.jira.integration.service.JiraTokenService;

import java.io.IOException;

import javax.portlet.PortletSession;

/**
 * @author Neil Griffin
 */
public class JiraTokenUtil {

	public static JiraToken getJiraTokenFromPortletSession(
			JiraTokenService jiraTokenService, PortletSession portletSession)
		throws IOException {

		JiraToken jiraToken = (JiraToken)portletSession.getAttribute(
			"jiraToken");

		if (jiraToken == null) {
			jiraToken = jiraTokenService.getToken();
		}

		portletSession.setAttribute("jiraToken", jiraToken);

		return jiraToken;
	}

}