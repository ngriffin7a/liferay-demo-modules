package com.liferay.demo.notification.sender.web.internal.portlet;

import com.liferay.portal.kernel.model.User;

import java.util.ArrayList;
import java.util.List;

public class RecipientUtil {

	public static List<Recipient> toRecipients(List<User> users) {

		List<Recipient> recipients = new ArrayList<>();
		for (User user : users) {
			recipients.add(
				new Recipient(
					user.getUserId(), user.getFirstName(), user.getLastName(),
					user.getFullName(), user.getEmailAddress(),
					user.getScreenName(), user.getLocale()));
		}

		return recipients;
	}

}
