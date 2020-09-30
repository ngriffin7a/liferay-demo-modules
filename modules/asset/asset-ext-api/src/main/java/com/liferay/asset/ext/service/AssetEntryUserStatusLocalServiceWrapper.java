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

package com.liferay.asset.ext.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AssetEntryUserStatusLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntryUserStatusLocalService
 * @generated
 */
public class AssetEntryUserStatusLocalServiceWrapper
	implements AssetEntryUserStatusLocalService,
			   ServiceWrapper<AssetEntryUserStatusLocalService> {

	public AssetEntryUserStatusLocalServiceWrapper(
		AssetEntryUserStatusLocalService assetEntryUserStatusLocalService) {

		_assetEntryUserStatusLocalService = assetEntryUserStatusLocalService;
	}

	/**
	 * Adds the asset entry user status to the database. Also notifies the appropriate model listeners.
	 *
	 * @param assetEntryUserStatus the asset entry user status
	 * @return the asset entry user status that was added
	 */
	@Override
	public com.liferay.asset.ext.model.AssetEntryUserStatus
		addAssetEntryUserStatus(
			com.liferay.asset.ext.model.AssetEntryUserStatus
				assetEntryUserStatus) {

		return _assetEntryUserStatusLocalService.addAssetEntryUserStatus(
			assetEntryUserStatus);
	}

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>AssetEntryUserStatusLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>AssetEntryUserStatusLocalServiceUtil</code>.
	 */
	@Override
	public com.liferay.asset.ext.model.AssetEntryUserStatus
		addAssetEntryUserStatus(
			long entryId, long companyId, long groupId, long userId,
			boolean acknowledged, boolean downloaded, boolean read) {

		return _assetEntryUserStatusLocalService.addAssetEntryUserStatus(
			entryId, companyId, groupId, userId, acknowledged, downloaded,
			read);
	}

	/**
	 * Creates a new asset entry user status with the primary key. Does not add the asset entry user status to the database.
	 *
	 * @param assetEntryUserStatusPK the primary key for the new asset entry user status
	 * @return the new asset entry user status
	 */
	@Override
	public com.liferay.asset.ext.model.AssetEntryUserStatus
		createAssetEntryUserStatus(
			com.liferay.asset.ext.service.persistence.AssetEntryUserStatusPK
				assetEntryUserStatusPK) {

		return _assetEntryUserStatusLocalService.createAssetEntryUserStatus(
			assetEntryUserStatusPK);
	}

	@Override
	public void deleteAllByGroupId(long groupId) {
		_assetEntryUserStatusLocalService.deleteAllByGroupId(groupId);
	}

	/**
	 * Deletes the asset entry user status from the database. Also notifies the appropriate model listeners.
	 *
	 * @param assetEntryUserStatus the asset entry user status
	 * @return the asset entry user status that was removed
	 */
	@Override
	public com.liferay.asset.ext.model.AssetEntryUserStatus
		deleteAssetEntryUserStatus(
			com.liferay.asset.ext.model.AssetEntryUserStatus
				assetEntryUserStatus) {

		return _assetEntryUserStatusLocalService.deleteAssetEntryUserStatus(
			assetEntryUserStatus);
	}

	/**
	 * Deletes the asset entry user status with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param assetEntryUserStatusPK the primary key of the asset entry user status
	 * @return the asset entry user status that was removed
	 * @throws PortalException if a asset entry user status with the primary key could not be found
	 */
	@Override
	public com.liferay.asset.ext.model.AssetEntryUserStatus
			deleteAssetEntryUserStatus(
				com.liferay.asset.ext.service.persistence.AssetEntryUserStatusPK
					assetEntryUserStatusPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _assetEntryUserStatusLocalService.deleteAssetEntryUserStatus(
			assetEntryUserStatusPK);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _assetEntryUserStatusLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _assetEntryUserStatusLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _assetEntryUserStatusLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.asset.ext.model.impl.AssetEntryUserStatusModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _assetEntryUserStatusLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.asset.ext.model.impl.AssetEntryUserStatusModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _assetEntryUserStatusLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _assetEntryUserStatusLocalService.dynamicQueryCount(
			dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _assetEntryUserStatusLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.asset.ext.model.AssetEntryUserStatus
		fetchAssetEntryUserStatus(
			com.liferay.asset.ext.service.persistence.AssetEntryUserStatusPK
				assetEntryUserStatusPK) {

		return _assetEntryUserStatusLocalService.fetchAssetEntryUserStatus(
			assetEntryUserStatusPK);
	}

	/**
	 * Returns the asset entry user status matching the UUID and group.
	 *
	 * @param uuid the asset entry user status's UUID
	 * @param groupId the primary key of the group
	 * @return the matching asset entry user status, or <code>null</code> if a matching asset entry user status could not be found
	 */
	@Override
	public com.liferay.asset.ext.model.AssetEntryUserStatus
		fetchAssetEntryUserStatusByUuidAndGroupId(String uuid, long groupId) {

		return _assetEntryUserStatusLocalService.
			fetchAssetEntryUserStatusByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _assetEntryUserStatusLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the asset entry user status with the primary key.
	 *
	 * @param assetEntryUserStatusPK the primary key of the asset entry user status
	 * @return the asset entry user status
	 * @throws PortalException if a asset entry user status with the primary key could not be found
	 */
	@Override
	public com.liferay.asset.ext.model.AssetEntryUserStatus
			getAssetEntryUserStatus(
				com.liferay.asset.ext.service.persistence.AssetEntryUserStatusPK
					assetEntryUserStatusPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _assetEntryUserStatusLocalService.getAssetEntryUserStatus(
			assetEntryUserStatusPK);
	}

	@Override
	public java.util.Optional<com.liferay.asset.ext.model.AssetEntryUserStatus>
		getAssetEntryUserStatusByEntryIdUserId(long entryId, long userId) {

		return _assetEntryUserStatusLocalService.
			getAssetEntryUserStatusByEntryIdUserId(entryId, userId);
	}

	/**
	 * Returns the asset entry user status matching the UUID and group.
	 *
	 * @param uuid the asset entry user status's UUID
	 * @param groupId the primary key of the group
	 * @return the matching asset entry user status
	 * @throws PortalException if a matching asset entry user status could not be found
	 */
	@Override
	public com.liferay.asset.ext.model.AssetEntryUserStatus
			getAssetEntryUserStatusByUuidAndGroupId(String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _assetEntryUserStatusLocalService.
			getAssetEntryUserStatusByUuidAndGroupId(uuid, groupId);
	}

	/**
	 * Returns a range of all the asset entry user statuses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.asset.ext.model.impl.AssetEntryUserStatusModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset entry user statuses
	 * @param end the upper bound of the range of asset entry user statuses (not inclusive)
	 * @return the range of asset entry user statuses
	 */
	@Override
	public java.util.List<com.liferay.asset.ext.model.AssetEntryUserStatus>
		getAssetEntryUserStatuses(int start, int end) {

		return _assetEntryUserStatusLocalService.getAssetEntryUserStatuses(
			start, end);
	}

	/**
	 * Returns all the asset entry user statuses matching the UUID and company.
	 *
	 * @param uuid the UUID of the asset entry user statuses
	 * @param companyId the primary key of the company
	 * @return the matching asset entry user statuses, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<com.liferay.asset.ext.model.AssetEntryUserStatus>
		getAssetEntryUserStatusesByUuidAndCompanyId(
			String uuid, long companyId) {

		return _assetEntryUserStatusLocalService.
			getAssetEntryUserStatusesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	 * Returns a range of asset entry user statuses matching the UUID and company.
	 *
	 * @param uuid the UUID of the asset entry user statuses
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of asset entry user statuses
	 * @param end the upper bound of the range of asset entry user statuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching asset entry user statuses, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<com.liferay.asset.ext.model.AssetEntryUserStatus>
		getAssetEntryUserStatusesByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.asset.ext.model.AssetEntryUserStatus>
					orderByComparator) {

		return _assetEntryUserStatusLocalService.
			getAssetEntryUserStatusesByUuidAndCompanyId(
				uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of asset entry user statuses.
	 *
	 * @return the number of asset entry user statuses
	 */
	@Override
	public int getAssetEntryUserStatusesCount() {
		return _assetEntryUserStatusLocalService.
			getAssetEntryUserStatusesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _assetEntryUserStatusLocalService.
			getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _assetEntryUserStatusLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _assetEntryUserStatusLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _assetEntryUserStatusLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Updates the asset entry user status in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param assetEntryUserStatus the asset entry user status
	 * @return the asset entry user status that was updated
	 */
	@Override
	public com.liferay.asset.ext.model.AssetEntryUserStatus
		updateAssetEntryUserStatus(
			com.liferay.asset.ext.model.AssetEntryUserStatus
				assetEntryUserStatus) {

		return _assetEntryUserStatusLocalService.updateAssetEntryUserStatus(
			assetEntryUserStatus);
	}

	@Override
	public AssetEntryUserStatusLocalService getWrappedService() {
		return _assetEntryUserStatusLocalService;
	}

	@Override
	public void setWrappedService(
		AssetEntryUserStatusLocalService assetEntryUserStatusLocalService) {

		_assetEntryUserStatusLocalService = assetEntryUserStatusLocalService;
	}

	private AssetEntryUserStatusLocalService _assetEntryUserStatusLocalService;

}