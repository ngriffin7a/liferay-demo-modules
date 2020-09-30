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

package com.liferay.asset.viewed.listener.internal;

import com.liferay.asset.ext.model.AssetEntryUserStatus;
import com.liferay.asset.ext.service.AssetEntryUserStatusLocalService;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Calendar;
import java.util.Objects;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Neil Griffin
 */
public class JournalArticleAuditingAssetRenderer
	extends JournalArticleAssetRendererWrapper {

	public JournalArticleAuditingAssetRenderer(
		AssetRenderer<JournalArticle> assetRenderer,
		AssetEntryUserStatusLocalService assetEntryUserStatusLocalService) {

		super(assetRenderer);

		_assetEntryUserStatusLocalService = assetEntryUserStatusLocalService;
	}

	@Override
	public boolean include(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, String template)
		throws Exception {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		User user = themeDisplay.getUser();

		if (user.isDefaultUser()) {
			_log.debug(
				"Not marking the article as read since it is the " +
					"default/Guest user");
		}
		else {
			if (Objects.equals("full_content", template)) {
				AssetRendererFactory assetRendererFactory =
					getAssetRendererFactory();

				AssetEntry assetEntry = assetRendererFactory.getAssetEntry(
					JournalArticle.class.getName(), getClassPK());

				Optional<AssetEntryUserStatus> optionalAssetEntryUserStatus =
					_assetEntryUserStatusLocalService.
						getAssetEntryUserStatusByEntryIdUserId(
							assetEntry.getEntryId(), themeDisplay.getUserId());

				String assetEntryTitle = assetEntry.getTitle(
					themeDisplay.getLocale());

				if (optionalAssetEntryUserStatus.isPresent()) {
					AssetEntryUserStatus assetEntryUserStatus =
						optionalAssetEntryUserStatus.get();

					if (assetEntryUserStatus.isRead()) {
						if (_log.isDebugEnabled()) {
							_log.debug(
								"The article titled \"" + assetEntryTitle +
									"\" is marked as read by " +
										user.getFullName());
						}
					}
					else {
						assetEntryUserStatus.setRead(true);

						Calendar todayCalendar = Calendar.getInstance();

						assetEntryUserStatus.setReadDate(
							todayCalendar.getTime());

						if (_log.isDebugEnabled()) {
							_log.debug(
								"Marking the article titled \"" +
									assetEntryTitle +
										"\" as having been read by " +
											user.getFullName());
						}

						_assetEntryUserStatusLocalService.
							updateAssetEntryUserStatus(assetEntryUserStatus);
					}
				}
				else {
					boolean acknowledged = false;
					boolean downloaded = false;
					boolean read = true;

					if (_log.isDebugEnabled()) {
						_log.debug(
							"Marking the article titled \"" + assetEntryTitle +
								"\" as having been read by " +
									user.getFullName());
					}

					_assetEntryUserStatusLocalService.addAssetEntryUserStatus(
						assetEntry.getEntryId(), themeDisplay.getCompanyId(),
						themeDisplay.getScopeGroupId(),
						themeDisplay.getUserId(), acknowledged, downloaded,
						read);
				}
			}
		}

		AssetRenderer assetRenderer = getWrapped();

		return assetRenderer.include(
			httpServletRequest, httpServletResponse, template);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		JournalArticleAuditingAssetRenderer.class);

	private AssetEntryUserStatusLocalService _assetEntryUserStatusLocalService;

}