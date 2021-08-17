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

package com.liferay.demo.notification.sender.web.internal.portlet;

import com.liferay.demo.notification.sender.web.NotificationSenderWebPortletKeys;
import com.liferay.portal.kernel.model.UserNotificationEvent;
import com.liferay.portal.kernel.notifications.BaseUserNotificationHandler;
import com.liferay.portal.kernel.service.ServiceContext;

import org.osgi.service.component.annotations.Component;

/**
 * @author Neil Griffin
 */
@Component(
	immediate = true,
	property = "javax.portlet.name=" + NotificationSenderWebPortletKeys.NOTIFICATION_SENDER_WEB,
	service = SegmentNotificationHandler.class
)
public class SegmentNotificationHandler extends BaseUserNotificationHandler {

	public SegmentNotificationHandler() {
		setPortletId(
			NotificationSenderWebPortletKeys.NOTIFICATION_SENDER_WEB);
	}

	@Override
	protected String getBody(
			UserNotificationEvent userNotificationEvent,
			ServiceContext serviceContext)
		throws Exception {

		return userNotificationEvent.getPayload();
	}

}