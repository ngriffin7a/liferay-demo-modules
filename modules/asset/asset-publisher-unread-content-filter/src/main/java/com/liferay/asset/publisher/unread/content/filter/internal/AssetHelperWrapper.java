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

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.service.persistence.AssetEntryQuery;
import com.liferay.asset.util.AssetHelper;
import com.liferay.asset.util.AssetPublisherAddItemHolder;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.SearchContext;

import java.io.Serializable;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Neil Griffin
 */
public abstract class AssetHelperWrapper implements AssetHelper {

	@Override
	public Set<String> addLayoutTags(
		HttpServletRequest httpServletRequest, List<AssetTag> tags) {

		AssetHelper assetHelper = getWrapped();

		return assetHelper.addLayoutTags(httpServletRequest, tags);
	}

	@Override
	public PortletURL getAddPortletURL(
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse, long groupId,
			String className, long classTypeId, long[] allAssetCategoryIds,
			String[] allAssetTagNames, String redirect)
		throws Exception {

		AssetHelper assetHelper = getWrapped();

		return assetHelper.getAddPortletURL(
			liferayPortletRequest, liferayPortletResponse, groupId, className,
			classTypeId, allAssetCategoryIds, allAssetTagNames, redirect);
	}

	@Override
	public String getAddURLPopUp(
		long groupId, long plid, PortletURL addPortletURL,
		boolean addDisplayPageParameter, Layout layout) {

		AssetHelper assetHelper = getWrapped();

		return assetHelper.getAddURLPopUp(
			groupId, plid, addPortletURL, addDisplayPageParameter, layout);
	}

	@Override
	public List<AssetEntry> getAssetEntries(Hits hits) {
		AssetHelper assetHelper = getWrapped();

		return assetHelper.getAssetEntries(hits);
	}

	@Override
	public String getAssetKeywords(String className, long classPK) {
		AssetHelper assetHelper = getWrapped();

		return assetHelper.getAssetKeywords(className, classPK);
	}

	@Override
	public List<AssetPublisherAddItemHolder> getAssetPublisherAddItemHolders(
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse, long groupId,
			long[] classNameIds, long[] classTypeIds,
			long[] allAssetCategoryIds, String[] allAssetTagNames,
			String redirect)
		throws Exception {

		AssetHelper assetHelper = getWrapped();

		return assetHelper.getAssetPublisherAddItemHolders(
			liferayPortletRequest, liferayPortletResponse, groupId,
			classNameIds, classTypeIds, allAssetCategoryIds, allAssetTagNames,
			redirect);
	}

	public abstract AssetHelper getWrapped();

	@Override
	public Hits search(
			HttpServletRequest httpServletRequest,
			AssetEntryQuery assetEntryQuery, int start, int end)
		throws Exception {

		AssetHelper assetHelper = getWrapped();

		return assetHelper.search(
			httpServletRequest, assetEntryQuery, start, end);
	}

	@Override
	public Hits search(
			SearchContext searchContext, AssetEntryQuery assetEntryQuery,
			int start, int end)
		throws Exception {

		AssetHelper assetHelper = getWrapped();

		return assetHelper.search(searchContext, assetEntryQuery, start, end);
	}

	@Override
	public BaseModelSearchResult<AssetEntry> searchAssetEntries(
			AssetEntryQuery assetEntryQuery, long[] assetCategoryIds,
			String[] assetTagNames, Map<String, Serializable> attributes,
			long companyId, String keywords, Layout layout, Locale locale,
			long scopeGroupId, TimeZone timeZone, long userId, int start,
			int end)
		throws Exception {

		AssetHelper assetHelper = getWrapped();

		return assetHelper.searchAssetEntries(
			assetEntryQuery, assetCategoryIds, assetTagNames, attributes,
			companyId, keywords, layout, locale, scopeGroupId, timeZone, userId,
			start, end);
	}

	@Override
	public BaseModelSearchResult<AssetEntry> searchAssetEntries(
			HttpServletRequest httpServletRequest,
			AssetEntryQuery assetEntryQuery, int start, int end)
		throws Exception {

		AssetHelper assetHelper = getWrapped();

		return assetHelper.searchAssetEntries(
			httpServletRequest, assetEntryQuery, start, end);
	}

	@Override
	public BaseModelSearchResult<AssetEntry> searchAssetEntries(
			SearchContext searchContext, AssetEntryQuery assetEntryQuery,
			int start, int end)
		throws Exception {

		AssetHelper assetHelper = getWrapped();

		return assetHelper.searchAssetEntries(
			searchContext, assetEntryQuery, start, end);
	}

}