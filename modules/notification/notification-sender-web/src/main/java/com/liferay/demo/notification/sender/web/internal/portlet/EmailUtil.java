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

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailServiceUtil;
import com.liferay.mail.kernel.template.MailTemplate;
import com.liferay.mail.kernel.template.MailTemplateContext;
import com.liferay.mail.kernel.template.MailTemplateFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.mail.internet.InternetAddress;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Neil Griffin
 */
@Component(service = EmailUtil.class)
public class EmailUtil {

	public void sendNotificationEmail(
			String fromAddress, String fromName, String toAddress, User toUser,
			String subject, String body)
		throws PortalException {

		MailMessage mailMessage = null;

		try {
			mailMessage = new MailMessage(
				new InternetAddress(fromAddress, fromName),
				new InternetAddress(toAddress, toUser.getFullName()), subject,
				body, true);
		}
		catch (UnsupportedEncodingException e) {
			throw new PortalException(e);
		}

		Company company = _companyLocalService.getCompany(
			toUser.getCompanyId());

		mailMessage.setMessageId(
			PortalUtil.getMailId(
				company.getMx(), "user", System.currentTimeMillis()));

		MailServiceUtil.sendEmail(mailMessage);
	}

	public void sendNotificationEmail(
			String fromAddress, String fromName, String toAddress, User toUser,
			String subject, String body,
			MailTemplateContext mailTemplateContext)
		throws PortalException {

		try {
			MailTemplate subjectTemplate =
				MailTemplateFactoryUtil.createMailTemplate(subject, false);

			MailTemplate bodyTemplate =
				MailTemplateFactoryUtil.createMailTemplate(body, true);

			sendNotificationEmail(
				fromAddress, fromName, toAddress, toUser,
				subjectTemplate.renderAsString(
					toUser.getLocale(), mailTemplateContext),
				bodyTemplate.renderAsString(
					toUser.getLocale(), mailTemplateContext));
		}
		catch (IOException ioException) {
			throw new SystemException(ioException);
		}
	}

	@Reference
	private CompanyLocalService _companyLocalService;

}