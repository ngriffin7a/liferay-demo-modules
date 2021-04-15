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

package com.liferay.demo.document.library.web.override.internal;

import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.util.JavaConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @author Neil Griffin
 */
public class MyHttpServletRequest extends HttpServletRequestWrapper {

	public MyHttpServletRequest(HttpServletRequest request) {
		super(request);
	}

	@Override
	public Object getAttribute(String name) {
		Object attribute = super.getAttribute(name);

		if (JavaConstants.JAVAX_PORTLET_REQUEST.equals(name)) {
			attribute = new MyLiferayPortletRequest(
				(LiferayPortletRequest)attribute);
		}

		return attribute;
	}

}