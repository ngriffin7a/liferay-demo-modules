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

import com.liferay.asset.ext.service.AssetEntryUserStatusLocalService;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.ClassTypeReader;
import com.liferay.journal.constants.JournalPortletKeys;
import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.util.Tuple;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Neil Griffin
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + JournalPortletKeys.JOURNAL,
		"service.ranking:Integer=101"
	},
	service = AssetRendererFactory.class
)
public class JournalArticleAuditingAssetRendererFactory
	implements AssetRendererFactory<JournalArticle> {

	@Override
	public AssetEntry getAssetEntry(long assetEntryId) throws PortalException {
		return _assetRendererFactory.getAssetEntry(assetEntryId);
	}

	@Override
	public AssetEntry getAssetEntry(String classNameId, long classPK)
		throws PortalException {

		return _assetRendererFactory.getAssetEntry(classNameId, classPK);
	}

	@Override
	public AssetRenderer<JournalArticle> getAssetRenderer(
			JournalArticle entry, int type)
		throws PortalException {

		return _assetRendererFactory.getAssetRenderer(entry, type);
	}

	@Override
	public AssetRenderer<JournalArticle> getAssetRenderer(long classPK)
		throws PortalException {

		return getAssetRenderer(classPK, TYPE_LATEST_APPROVED);
	}

	@Override
	public AssetRenderer<JournalArticle> getAssetRenderer(
			long classPK, int type)
		throws PortalException {

		return new JournalArticleAuditingAssetRenderer(
			_assetRendererFactory.getAssetRenderer(classPK, type),
			_assetEntryUserStatusLocalService);
	}

	@Override
	public AssetRenderer<JournalArticle> getAssetRenderer(
			long groupId, String urlTitle)
		throws PortalException {

		return _assetRendererFactory.getAssetRenderer(groupId, urlTitle);
	}

	@Override
	public String getClassName() {
		return _assetRendererFactory.getClassName();
	}

	@Override
	public long getClassNameId() {
		return _assetRendererFactory.getClassNameId();
	}

	@Override
	public Tuple getClassTypeFieldName(
			long classTypeId, String fieldName, Locale locale)
		throws Exception {

		return _assetRendererFactory.getClassTypeFieldName(
			classTypeId, fieldName, locale);
	}

	@Override
	public List<Tuple> getClassTypeFieldNames(
			long classTypeId, Locale locale, int start, int end)
		throws Exception {

		return _assetRendererFactory.getClassTypeFieldNames(
			classTypeId, locale, start, end);
	}

	@Override
	public int getClassTypeFieldNamesCount(long classTypeId, Locale locale)
		throws Exception {

		return _assetRendererFactory.getClassTypeFieldNamesCount(
			classTypeId, locale);
	}

	@Override
	public ClassTypeReader getClassTypeReader() {
		return _assetRendererFactory.getClassTypeReader();
	}

	@Override
	public Map<Long, String> getClassTypes(long[] groupIds, Locale locale)
		throws Exception {

		return _assetRendererFactory.getClassTypes(groupIds, locale);
	}

	@Override
	public String getIconCssClass() {
		return _assetRendererFactory.getIconCssClass();
	}

	@Override
	public String getIconPath(PortletRequest portletRequest) {
		return _assetRendererFactory.getIconPath(portletRequest);
	}

	@Override
	public String getPortletId() {
		return _assetRendererFactory.getPortletId();
	}

	@Override
	public String getSubtypeTitle(Locale locale) {
		return _assetRendererFactory.getSubtypeTitle(locale);
	}

	@Override
	public String getType() {
		return _assetRendererFactory.getType();
	}

	@Override
	public String getTypeName(Locale locale) {
		return _assetRendererFactory.getTypeName(locale);
	}

	@Override
	public String getTypeName(Locale locale, boolean hasSubtypes) {
		return _assetRendererFactory.getTypeName(locale, hasSubtypes);
	}

	@Override
	public String getTypeName(Locale locale, long subtypeId) {
		return _assetRendererFactory.getTypeName(locale, subtypeId);
	}

	@Override
	public PortletURL getURLAdd(
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse)
		throws PortalException {

		return _assetRendererFactory.getURLAdd(
			liferayPortletRequest, liferayPortletResponse);
	}

	@Override
	public PortletURL getURLAdd(
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse, long classTypeId)
		throws PortalException {

		return _assetRendererFactory.getURLAdd(
			liferayPortletRequest, liferayPortletResponse, classTypeId);
	}

	@Override
	public PortletURL getURLView(
			LiferayPortletResponse liferayPortletResponse,
			WindowState windowState)
		throws PortalException {

		return _assetRendererFactory.getURLView(
			liferayPortletResponse, windowState);
	}

	@Override
	public boolean hasAddPermission(
			PermissionChecker permissionChecker, long groupId, long classTypeId)
		throws Exception {

		return _assetRendererFactory.hasAddPermission(
			permissionChecker, groupId, classTypeId);
	}

	@Override
	public boolean hasClassTypeFieldNames(long classTypeId, Locale locale)
		throws Exception {

		return _assetRendererFactory.hasClassTypeFieldNames(
			classTypeId, locale);
	}

	@Override
	public boolean hasPermission(
			PermissionChecker permissionChecker, long entryClassPK,
			String actionId)
		throws Exception {

		return _assetRendererFactory.hasPermission(
			permissionChecker, entryClassPK, actionId);
	}

	@Override
	public boolean isActive(long companyId) {
		return _assetRendererFactory.isActive(companyId);
	}

	@Override
	public boolean isCategorizable() {
		return _assetRendererFactory.isCategorizable();
	}

	@Override
	public boolean isLinkable() {
		return _assetRendererFactory.isLinkable();
	}

	@Override
	public boolean isSearchable() {
		return _assetRendererFactory.isSearchable();
	}

	@Override
	public boolean isSelectable() {
		return _assetRendererFactory.isSelectable();
	}

	@Override
	public boolean isSupportsClassTypes() {
		return _assetRendererFactory.isSupportsClassTypes();
	}

	@Override
	public void setClassName(String className) {
		_assetRendererFactory.setClassName(className);
	}

	@Override
	public void setPortletId(String portletId) {
		_assetRendererFactory.setPortletId(portletId);
	}

	@Reference
	private AssetEntryUserStatusLocalService _assetEntryUserStatusLocalService;

	@Reference(
		target = "(component.name=com.liferay.journal.web.internal.asset.model.JournalArticleAssetRendererFactory)"
	)
	private AssetRendererFactory<JournalArticle> _assetRendererFactory;

}