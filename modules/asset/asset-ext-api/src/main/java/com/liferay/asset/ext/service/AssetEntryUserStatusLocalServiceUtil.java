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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for AssetEntryUserStatus. This utility wraps
 * <code>com.liferay.asset.ext.service.impl.AssetEntryUserStatusLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntryUserStatusLocalService
 * @generated
 */
public class AssetEntryUserStatusLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.asset.ext.service.impl.AssetEntryUserStatusLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the asset entry user status to the database. Also notifies the appropriate model listeners.
	 *
	 * @param assetEntryUserStatus the asset entry user status
	 * @return the asset entry user status that was added
	 */
	public static com.liferay.asset.ext.model.AssetEntryUserStatus
		addAssetEntryUserStatus(
			com.liferay.asset.ext.model.AssetEntryUserStatus
				assetEntryUserStatus) {

		return getService().addAssetEntryUserStatus(assetEntryUserStatus);
	}

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>AssetEntryUserStatusLocalService</code> via injection or a <code>ServiceTracker</code> or use <code>AssetEntryUserStatusLocalServiceUtil</code>.
	 */
	public static com.liferay.asset.ext.model.AssetEntryUserStatus
		addAssetEntryUserStatus(
			long entryId, long companyId, long groupId, long userId,
			boolean acknowledged, boolean downloaded, boolean read) {

		return getService().addAssetEntryUserStatus(
			entryId, companyId, groupId, userId, acknowledged, downloaded,
			read);
	}

	/**
	 * Creates a new asset entry user status with the primary key. Does not add the asset entry user status to the database.
	 *
	 * @param assetEntryUserStatusPK the primary key for the new asset entry user status
	 * @return the new asset entry user status
	 */
	public static com.liferay.asset.ext.model.AssetEntryUserStatus
		createAssetEntryUserStatus(
			com.liferay.asset.ext.service.persistence.AssetEntryUserStatusPK
				assetEntryUserStatusPK) {

		return getService().createAssetEntryUserStatus(assetEntryUserStatusPK);
	}

	public static void deleteAllByGroupId(long groupId) {
		getService().deleteAllByGroupId(groupId);
	}

	/**
	 * Deletes the asset entry user status from the database. Also notifies the appropriate model listeners.
	 *
	 * @param assetEntryUserStatus the asset entry user status
	 * @return the asset entry user status that was removed
	 */
	public static com.liferay.asset.ext.model.AssetEntryUserStatus
		deleteAssetEntryUserStatus(
			com.liferay.asset.ext.model.AssetEntryUserStatus
				assetEntryUserStatus) {

		return getService().deleteAssetEntryUserStatus(assetEntryUserStatus);
	}

	/**
	 * Deletes the asset entry user status with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param assetEntryUserStatusPK the primary key of the asset entry user status
	 * @return the asset entry user status that was removed
	 * @throws PortalException if a asset entry user status with the primary key could not be found
	 */
	public static com.liferay.asset.ext.model.AssetEntryUserStatus
			deleteAssetEntryUserStatus(
				com.liferay.asset.ext.service.persistence.AssetEntryUserStatusPK
					assetEntryUserStatusPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteAssetEntryUserStatus(assetEntryUserStatusPK);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			deletePersistedModel(
				com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery
		dynamicQuery() {

		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQuery(dynamicQuery);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.asset.ext.model.AssetEntryUserStatus
		fetchAssetEntryUserStatus(
			com.liferay.asset.ext.service.persistence.AssetEntryUserStatusPK
				assetEntryUserStatusPK) {

		return getService().fetchAssetEntryUserStatus(assetEntryUserStatusPK);
	}

	/**
	 * Returns the asset entry user status matching the UUID and group.
	 *
	 * @param uuid the asset entry user status's UUID
	 * @param groupId the primary key of the group
	 * @return the matching asset entry user status, or <code>null</code> if a matching asset entry user status could not be found
	 */
	public static com.liferay.asset.ext.model.AssetEntryUserStatus
		fetchAssetEntryUserStatusByUuidAndGroupId(String uuid, long groupId) {

		return getService().fetchAssetEntryUserStatusByUuidAndGroupId(
			uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the asset entry user status with the primary key.
	 *
	 * @param assetEntryUserStatusPK the primary key of the asset entry user status
	 * @return the asset entry user status
	 * @throws PortalException if a asset entry user status with the primary key could not be found
	 */
	public static com.liferay.asset.ext.model.AssetEntryUserStatus
			getAssetEntryUserStatus(
				com.liferay.asset.ext.service.persistence.AssetEntryUserStatusPK
					assetEntryUserStatusPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getAssetEntryUserStatus(assetEntryUserStatusPK);
	}

	public static java.util.Optional
		<com.liferay.asset.ext.model.AssetEntryUserStatus>
			getAssetEntryUserStatusByEntryIdUserId(long entryId, long userId) {

		return getService().getAssetEntryUserStatusByEntryIdUserId(
			entryId, userId);
	}

	/**
	 * Returns the asset entry user status matching the UUID and group.
	 *
	 * @param uuid the asset entry user status's UUID
	 * @param groupId the primary key of the group
	 * @return the matching asset entry user status
	 * @throws PortalException if a matching asset entry user status could not be found
	 */
	public static com.liferay.asset.ext.model.AssetEntryUserStatus
			getAssetEntryUserStatusByUuidAndGroupId(String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getAssetEntryUserStatusByUuidAndGroupId(
			uuid, groupId);
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
	public static java.util.List
		<com.liferay.asset.ext.model.AssetEntryUserStatus>
			getAssetEntryUserStatuses(int start, int end) {

		return getService().getAssetEntryUserStatuses(start, end);
	}

	/**
	 * Returns all the asset entry user statuses matching the UUID and company.
	 *
	 * @param uuid the UUID of the asset entry user statuses
	 * @param companyId the primary key of the company
	 * @return the matching asset entry user statuses, or an empty list if no matches were found
	 */
	public static java.util.List
		<com.liferay.asset.ext.model.AssetEntryUserStatus>
			getAssetEntryUserStatusesByUuidAndCompanyId(
				String uuid, long companyId) {

		return getService().getAssetEntryUserStatusesByUuidAndCompanyId(
			uuid, companyId);
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
	public static java.util.List
		<com.liferay.asset.ext.model.AssetEntryUserStatus>
			getAssetEntryUserStatusesByUuidAndCompanyId(
				String uuid, long companyId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.asset.ext.model.AssetEntryUserStatus>
						orderByComparator) {

		return getService().getAssetEntryUserStatusesByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of asset entry user statuses.
	 *
	 * @return the number of asset entry user statuses
	 */
	public static int getAssetEntryUserStatusesCount() {
		return getService().getAssetEntryUserStatusesCount();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the asset entry user status in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param assetEntryUserStatus the asset entry user status
	 * @return the asset entry user status that was updated
	 */
	public static com.liferay.asset.ext.model.AssetEntryUserStatus
		updateAssetEntryUserStatus(
			com.liferay.asset.ext.model.AssetEntryUserStatus
				assetEntryUserStatus) {

		return getService().updateAssetEntryUserStatus(assetEntryUserStatus);
	}

	public static AssetEntryUserStatusLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<AssetEntryUserStatusLocalService, AssetEntryUserStatusLocalService>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			AssetEntryUserStatusLocalService.class);

		ServiceTracker
			<AssetEntryUserStatusLocalService, AssetEntryUserStatusLocalService>
				serviceTracker =
					new ServiceTracker
						<AssetEntryUserStatusLocalService,
						 AssetEntryUserStatusLocalService>(
							 bundle.getBundleContext(),
							 AssetEntryUserStatusLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}