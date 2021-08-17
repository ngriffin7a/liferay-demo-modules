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

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.model.Phone;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.vulcan.util.TransformUtil;
import com.liferay.segments.provider.SegmentsEntryProviderRegistry;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;

import java.io.IOException;

import java.util.List;
import java.util.Objects;

import javax.portlet.ActionParameters;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Neil Griffin
 */
@Component(service = SMSNotifier.class)
public class SMSNotifier {

	public void sendSms(long segmentsEntryId, String messageText)
		throws Exception {

		List<User> users = _getSegmentsEntryUsers(
			segmentsEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (User user : users) {
			List<Phone> phones = user.getPhones();

			for (Phone phone : phones) {
				ListType listType = phone.getType();

				if (Objects.equals("mobile-phone", listType.getName())) {
					String phoneNumber = phone.getNumber();

					if (Validator.isNotNull(phoneNumber)) {
						if (!phoneNumber.startsWith("+") &&
							!phoneNumber.startsWith("1")) {

							phoneNumber = "+1" + phoneNumber;
						}

						String accountSid = "";
						String authToken = "";
						String fromPhoneNumber = "";

						Twilio.init(accountSid, authToken);

						MessageCreator creator = Message.creator(
							new PhoneNumber(phoneNumber),
							new PhoneNumber(fromPhoneNumber),
							messageText);

						Message message = creator.create();

						System.out.println(message.getSid());
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
	private SegmentsEntryProviderRegistry _segmentsEntryProviderRegistry;

	@Reference
	private UserLocalService _userLocalService;

}