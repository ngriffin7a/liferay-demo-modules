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

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.list.asset.entry.provider.AssetListAssetEntryProvider;
import com.liferay.asset.list.model.AssetListEntry;
import com.liferay.asset.list.service.AssetListEntryLocalService;
import com.liferay.demo.notification.sender.web.NotificationSenderWebPortletKeys;
import com.liferay.mail.kernel.template.MailTemplateContextBuilder;
import com.liferay.mail.kernel.template.MailTemplateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.UserGroupLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.vulcan.util.TransformUtil;
import com.liferay.segments.provider.SegmentsEntryProviderRegistry;
import com.liferay.segments.service.SegmentsEntryLocalService;

import java.io.IOException;

import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
		"com.liferay.portlet.add-default-resource=true",
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=NotificationsAdminWeb",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/views/notificationSender.jsp",
		"javax.portlet.name=" + NotificationSenderWebPortletKeys.NOTIFICATION_SENDER_WEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"javax.portlet.version=3.0"
	},
	service = Portlet.class
)
public class NotificationSenderPortlet extends MVCPortlet {

	public static final String ADMIN_EMAIL_FROM_ADDRESS = PropsUtil.get(
		PropsKeys.ADMIN_EMAIL_FROM_ADDRESS);

	public static final String ADMIN_EMAIL_FROM_NAME = PropsUtil.get(
		PropsKeys.ADMIN_EMAIL_FROM_NAME);

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		renderRequest.setAttribute("emailSubject", "This is the email subject");

		renderRequest.setAttribute("fromAddress", ADMIN_EMAIL_FROM_ADDRESS);

		renderRequest.setAttribute("fromName", ADMIN_EMAIL_FROM_NAME);

		renderRequest.setAttribute("sendCalendar", Calendar.getInstance());

		// renderRequest.setAttribute("CALENDAR_AM_PM", java.util.Calendar.AM_PM);
		// renderRequest.setAttribute("CALENDAR_DATE", java.util.Calendar.DATE);
		// renderRequest.setAttribute("CALENDAR_HOUR", java.util.Calendar.HOUR);
		// renderRequest.setAttribute("CALENDAR_MINUTE", java.util.Calendar.MINUTE);
		// renderRequest.setAttribute("CALENDAR_MONTH", java.util.Calendar.MONTH);
		// renderRequest.setAttribute("CALENDAR_YEAR", java.util.Calendar.YEAR);

		// Sites
		Map<Long, String> groups = new HashMap<>();

		List<Group> groupList =
			_groupLocalService.getGroups(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (Group group : groupList) {
			groups.put(group.getGroupId(), group.getName());
		}

		renderRequest.setAttribute("groups", groups);

		// Collections
		renderRequest.setAttribute("assetListEntries",
			_assetListEntryLocalService.getAssetListEntries(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS));

		// Segments
		renderRequest.setAttribute("segmentsEntries",
			_segmentsEntryLocalService.getSegmentsEntries(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS));

		// User Groups
		renderRequest.setAttribute("userGroups",
			_userGroupLocalService.getUserGroups(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS));

		super.render(renderRequest, renderResponse);
	}

	public void sendEmails(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ActionParameters actionParameters = actionRequest.getActionParameters();

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		StringBuilder body = new StringBuilder(actionParameters.getValue("emailBody"));
		String subject = actionParameters.getValue(
			"preferences--emailBodySubject--");
		String fromAddress = actionParameters.getValue("fromAddress");
		String fromName = actionParameters.getValue("fromName");

		Set<Recipient> recipients = new HashSet<>();

		// Segment Recipients
		long segmentsEntryId = GetterUtil.getLong(
			actionParameters.getValue("segmentsEntryId"));

		if (segmentsEntryId > 0) {
			recipients.addAll(RecipientUtil.toRecipients(_getSegmentsEntryUsers(
				segmentsEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS)));
		}

		// User Group Recipients
		long userGroupId = GetterUtil.getLong(
			actionParameters.getValue("userGroupId"));

		if (userGroupId > 0) {
			recipients.addAll(RecipientUtil.toRecipients(_userLocalService.getUserGroupUsers(userGroupId)));
		}

		// Email Addresses (comma delimited)
		String emailAddresses = GetterUtil.getString(actionParameters.getValue("emailAddresses"));
		for (String emailAddress : emailAddresses.split(",")) {
			recipients.add(new Recipient(emailAddress));
		}

		long assetListEntryId = GetterUtil.getLong(
			actionParameters.getValue("assetListEntryId"));

		if (assetListEntryId > 0) {
			AssetListEntry assetListEntry =
				_assetListEntryLocalService.getAssetListEntry(assetListEntryId);

			List<AssetEntry> assetEntries =
				_assetListAssetEntryProvider.getAssetEntries(assetListEntry,
					segmentsEntryId);

			if (!assetEntries.isEmpty()) {

				body.append("<hr/>");
				body.append("<ul>");
				for (AssetEntry assetEntry : assetEntries) {
					AssetRenderer<?> assetRenderer =
						assetEntry.getAssetRenderer();
					body.append("<li>");
					body.append("<a href=\"");
					body.append(assetRenderer.getURLDownload(themeDisplay));
					body.append("\">");
					body.append(assetEntry.getTitle());
					body.append("</a>");
					body.append("</li>");
				}
				body.append("</li>");
			}
		}

		for (Recipient recipient: recipients) {

			if (recipient.getEmailAddress().isEmpty()) {
				System.err.println("Skipping recipient because the email address is empty: " + recipient);
				Thread.sleep(2000);
				continue;
			}

			MailTemplateContextBuilder mailTemplateContextBuilder =
				MailTemplateFactoryUtil.createMailTemplateContextBuilder();

			mailTemplateContextBuilder.put("[$FROM_ADDRESS$]", fromAddress);
			mailTemplateContextBuilder.put(
				"[$FROM_NAME$]", HtmlUtil.escape(fromName));

			String portalURL = _portalUtil.getPortalURL(actionRequest);

			mailTemplateContextBuilder.put("[$PORTAL_URL$]", portalURL);

			mailTemplateContextBuilder.put(
				"[$TO_ADDRESS$]", recipient.getEmailAddress());
			mailTemplateContextBuilder.put(
				"[$TO_NAME$]", HtmlUtil.escape(recipient.getFullName()));
			mailTemplateContextBuilder.put(
				"[$USER_ID$]", String.valueOf(recipient.getUserId()));
			mailTemplateContextBuilder.put(
				"[$USER_SCREENNAME$]", HtmlUtil.escape(recipient.getScreenName()));

			mailTemplateContextBuilder.put(
				"<img src=\"/documents",
				"<img src=\"" + portalURL + "/documents");

			System.err.println("!@#$ Sending email to: " + recipient.getEmailAddress());
			_emailUtil.sendNotificationEmail(
				themeDisplay.getCompanyId(), fromAddress, fromName, recipient.getEmailAddress(), recipient, subject,
				body.toString(), mailTemplateContextBuilder.build());
			Thread.sleep(2000);
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
	private AssetListEntryLocalService _assetListEntryLocalService;

	@Reference
	private AssetListAssetEntryProvider _assetListAssetEntryProvider;

	@Reference
	private EmailUtil _emailUtil;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private PortalUtil _portalUtil;

	@Reference
	private SegmentsEntryLocalService _segmentsEntryLocalService;

	@Reference
	private SegmentsEntryProviderRegistry _segmentsEntryProviderRegistry;

	@Reference
	private UserGroupLocalService _userGroupLocalService;

	@Reference
	private UserLocalService _userLocalService;

}