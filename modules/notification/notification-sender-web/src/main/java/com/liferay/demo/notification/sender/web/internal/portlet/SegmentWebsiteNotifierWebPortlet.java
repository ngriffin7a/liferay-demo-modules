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

import com.liferay.demo.notification.sender.web.SegmentNotifierWebPortletKeys;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.model.Phone;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.vulcan.util.TransformUtil;
import com.liferay.segments.model.SegmentsEntry;
import com.liferay.segments.provider.SegmentsEntryProviderRegistry;
import com.liferay.segments.service.SegmentsEntryLocalService;

import java.io.IOException;

import java.util.List;
import java.util.Objects;

import javax.portlet.ActionParameters;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Neil Griffin
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=NotificationsAdminWeb",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/views/segmentSmsNotifier.jsp",
		"javax.portlet.name=" + SegmentNotifierWebPortletKeys.SEGMENT_SMS_NOTIFIER_WEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"javax.portlet.version=3.0"
	},
	service = Portlet.class
)
public class SegmentWebsiteNotifierWebPortlet extends MVCPortlet {

	public static final String ADMIN_EMAIL_FROM_ADDRESS = PropsUtil.get(
		PropsKeys.ADMIN_EMAIL_FROM_ADDRESS);

	public static final String ADMIN_EMAIL_FROM_NAME = PropsUtil.get(
		PropsKeys.ADMIN_EMAIL_FROM_NAME);

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		List<SegmentsEntry> segmentsEntries =
			_segmentsEntryLocalService.getSegmentsEntries(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		renderRequest.setAttribute("segmentsEntries", segmentsEntries);

		super.render(renderRequest, renderResponse);
	}

	public void sendWebsite(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ActionParameters actionParameters = actionRequest.getActionParameters();

		String message = actionParameters.getValue("message");

		long segmentsEntryId = GetterUtil.getLong(
			actionParameters.getValue("segmentsEntryId"));

		List<User> users = _getSegmentsEntryUsers(
			segmentsEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (User user : users) {
			List<Phone> phones = user.getPhones();

			for (Phone phone : phones) {
				ListType listType = phone.getType();

				if (Objects.equals("mobile-phone", listType.getName())) {
					String phoneNumber = phone.getNumber();

					if (Validator.isNotNull(phoneNumber)) {
						String smsEmail = phoneNumber + "@vtext.com";

						_emailUtil.sendNotificationEmail(
							ADMIN_EMAIL_FROM_ADDRESS, ADMIN_EMAIL_FROM_NAME,
							smsEmail, user, "", message);
					}
				}
			}
		}
	}

	private List<User> _getSegmentsEntryUsers(
			long segmentsEntryId, int start, int end)
		throws Exception {

		return TransformUtil.transformToList(
			ArrayUtil.toLongArray(
				_segmentsEntryProviderRegistry.getSegmentsEntryClassPKs(
					segmentsEntryId, start, end)),
			_userLocalService::fetchUser);
	}

	@Reference
	private EmailUtil _emailUtil;

	@Reference
	private SegmentsEntryLocalService _segmentsEntryLocalService;

	@Reference
	private SegmentsEntryProviderRegistry _segmentsEntryProviderRegistry;

	@Reference
	private UserLocalService _userLocalService;

}