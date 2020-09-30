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

package com.liferay.asset.publisher.unread.content.filter.internal;

import com.liferay.asset.ext.model.AssetEntryUserStatus;
import com.liferay.asset.ext.service.AssetEntryUserStatusLocalService;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.persistence.AssetEntryQuery;
import com.liferay.asset.util.AssetHelper;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.TimeZone;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Neil Griffin
 */
@Component(
	configurationPid = "com.liferay.asset.publisher.unread.content.filter.internal.UnreadAssetsConfig",
	immediate = true, property = "service.ranking:Integer=101",
	service = AssetHelper.class
)
public class AssetHelperUnreadContentFilter extends AssetHelperWrapper {

	@Override
	public AssetHelper getWrapped() {
		return _assetHelper;
	}

	@Override
	public BaseModelSearchResult<AssetEntry> searchAssetEntries(
			AssetEntryQuery assetEntryQuery, long[] assetCategoryIds,
			String[] assetTagNames, Map<String, Serializable> attributes,
			long companyId, String keywords, Layout layout, Locale locale,
			long scopeGroupId, TimeZone timeZone, long userId, int start,
			int end)
		throws Exception {

		boolean unreadAssetsOnly = false;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		if (serviceContext != null) {
			ThemeDisplay themeDisplay = serviceContext.getThemeDisplay();

			if (themeDisplay != null) {
				PortletDisplay portletDisplay =
					themeDisplay.getPortletDisplay();

				if (portletDisplay != null) {
					String title = portletDisplay.getTitle();

					if (title != null) {
						for (String unreadAssetPublisherPortletTitle :
								unreadAssetsConfig.
									assetPublisherPortletTitles()) {

							if (title.contains(
									unreadAssetPublisherPortletTitle)) {

								unreadAssetsOnly = true;

								_log.debug(
									"Portlet with title \"" + title +
										"\" displays only UNREAD assets");

								break;
							}
						}

						if (!unreadAssetsOnly) {
							_log.debug(
								"Portlet with title \"" + title +
									"\" displays both READ and UNREAD assets");
						}
					}
					else {
						_log.error("Unable to get title from PortletDisplay");
					}
				}
				else {
					_log.error(
						"Unable to get PortletDisplay from ThemeDisplay");
				}
			}
			else {
				_log.error("Unable to get ThemeDisplay from ServiceContext");
			}
		}
		else {
			_log.error("Unable to get ServiceContext from ThreadLocal");
		}

		BaseModelSearchResult<AssetEntry> assetEntryBaseModelSearchResult =
			super.searchAssetEntries(
				assetEntryQuery, assetCategoryIds, assetTagNames, attributes,
				companyId, keywords, layout, locale, scopeGroupId, timeZone,
				userId, start, end);

		if (unreadAssetsOnly) {
			List<AssetEntry> unreadAssetEntries = new ArrayList<>();

			List<AssetEntry> assetEntries =
				assetEntryBaseModelSearchResult.getBaseModels();

			for (AssetEntry assetEntry : assetEntries) {
				Optional<AssetEntryUserStatus> optionalAssetEntryUserStatus =
					_assetEntryUserStatusLocalService.
						getAssetEntryUserStatusByEntryIdUserId(
							assetEntry.getEntryId(), userId);

				if (optionalAssetEntryUserStatus.isPresent()) {
					AssetEntryUserStatus assetUserStatus =
						optionalAssetEntryUserStatus.get();

					if (!assetUserStatus.isRead()) {
						unreadAssetEntries.add(assetEntry);
					}
				}
				else {
					unreadAssetEntries.add(assetEntry);
				}
			}

			return new BaseModelSearchResult<>(
				unreadAssetEntries, unreadAssetEntries.size());
		}

		return assetEntryBaseModelSearchResult;
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		unreadAssetsConfig = ConfigurableUtil.createConfigurable(
			UnreadAssetsConfig.class, properties);
	}

	protected UnreadAssetsConfig unreadAssetsConfig;

	private static final Log _log = LogFactoryUtil.getLog(
		AssetHelperUnreadContentFilter.class);

	@Reference
	private AssetEntryUserStatusLocalService _assetEntryUserStatusLocalService;

	@Reference(
		target = "(component.name=com.liferay.asset.internal.util.AssetHelperImpl)"
	)
	private AssetHelper _assetHelper;

}