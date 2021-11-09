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

package com.liferay.jira.integration;

import java.io.IOException;

/**
 * @author Neil Griffin
 */
public interface JiraTokenService {

	/**
	 * Retrieves an OAuth2 {@link JiraToken} by invoking the JIRA
	 * RESTful authentication API with the specified credentials.
	 *
	 * @return The {@link JiraToken} associated with a successful authentication.
	 * @throws IOException When there is a failure connecting to JIRA or
	 * when there is an authenticate failure with the specified credentials.
	 */
	public JiraToken getToken() throws IOException;

}