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

import com.liferay.document.library.constants.DLPortletKeys;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;

import java.util.Objects;

/**
 * @author Neil Griffin
 */
public class MyLiferayPortletRequest extends LiferayPortletRequestWrapper {

	public MyLiferayPortletRequest(
		LiferayPortletRequest liferayPortletRequest) {

		super(liferayPortletRequest);
	}

	@Override
	public String getPortletName() {
		Exception exception = new Exception("foo");

		StackTraceElement[] stackTrace = exception.getStackTrace();

		int depth = 0;

		for (StackTraceElement stackTraceElement : stackTrace) {
			String methodName = stackTraceElement.getMethodName();

			// The DLAdminManagementToolbarDisplayContext.getCreationMenu()
			// method will only return a creation menu (a list of menu items) if
			// the current portlet request is associated with the portlet named
			// DLPortletKeys.DOCUMENT_LIBRARY_ADMIN.

			if (Objects.equals(methodName, "getCreationMenu")) {
				return DLPortletKeys.DOCUMENT_LIBRARY_ADMIN;
			}

			depth++;

			// If the "getCreationMenu" method is not found in the first 20
			// frames of the call stack then for performance reasons, give up
			// and stop iterating.

			if (depth > 10) {
				break;
			}
		}

		return super.getPortletName();
	}

}