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

package com.liferay.demo.servicenow.web.internal.portlet;

import com.liferay.demo.servicenow.ServiceNowToken;
import com.liferay.demo.servicenow.ServiceNowTokenService;

import java.io.IOException;

import javax.portlet.PortletSession;

/**
 * @author Neil Griffin
 */
public class ServiceNowTokenUtil {

	public static ServiceNowToken getServiceNowTokenFromPortletSession(
			ServiceNowTokenService serviceNowTokenService,
			PortletSession portletSession)
		throws IOException {

		ServiceNowToken serviceNowToken =
			(ServiceNowToken)portletSession.getAttribute("serviceNowToken");

		if (serviceNowToken == null) {
			serviceNowToken = serviceNowTokenService.getToken();
		}

		portletSession.setAttribute("serviceNowToken", serviceNowToken);

		return serviceNowToken;
	}

}