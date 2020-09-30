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

package com.liferay.asset.ext.service.persistence;

import com.liferay.asset.ext.model.AssetEntryUserStatus;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the asset entry user status service. This utility wraps <code>com.liferay.asset.ext.service.persistence.impl.AssetEntryUserStatusPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntryUserStatusPersistence
 * @generated
 */
public class AssetEntryUserStatusUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(AssetEntryUserStatus assetEntryUserStatus) {
		getPersistence().clearCache(assetEntryUserStatus);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, AssetEntryUserStatus> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<AssetEntryUserStatus> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AssetEntryUserStatus> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AssetEntryUserStatus> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<AssetEntryUserStatus> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static AssetEntryUserStatus update(
		AssetEntryUserStatus assetEntryUserStatus) {

		return getPersistence().update(assetEntryUserStatus);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static AssetEntryUserStatus update(
		AssetEntryUserStatus assetEntryUserStatus,
		ServiceContext serviceContext) {

		return getPersistence().update(assetEntryUserStatus, serviceContext);
	}

	/**
	 * Returns all the asset entry user statuses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching asset entry user statuses
	 */
	public static List<AssetEntryUserStatus> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the asset entry user statuses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetEntryUserStatusModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of asset entry user statuses
	 * @param end the upper bound of the range of asset entry user statuses (not inclusive)
	 * @return the range of matching asset entry user statuses
	 */
	public static List<AssetEntryUserStatus> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the asset entry user statuses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetEntryUserStatusModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of asset entry user statuses
	 * @param end the upper bound of the range of asset entry user statuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset entry user statuses
	 */
	public static List<AssetEntryUserStatus> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<AssetEntryUserStatus> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the asset entry user statuses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetEntryUserStatusModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of asset entry user statuses
	 * @param end the upper bound of the range of asset entry user statuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching asset entry user statuses
	 */
	public static List<AssetEntryUserStatus> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<AssetEntryUserStatus> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first asset entry user status in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry user status
	 * @throws NoSuchAssetEntryUserStatusException if a matching asset entry user status could not be found
	 */
	public static AssetEntryUserStatus findByUuid_First(
			String uuid,
			OrderByComparator<AssetEntryUserStatus> orderByComparator)
		throws com.liferay.asset.ext.exception.
			NoSuchAssetEntryUserStatusException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first asset entry user status in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry user status, or <code>null</code> if a matching asset entry user status could not be found
	 */
	public static AssetEntryUserStatus fetchByUuid_First(
		String uuid,
		OrderByComparator<AssetEntryUserStatus> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last asset entry user status in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry user status
	 * @throws NoSuchAssetEntryUserStatusException if a matching asset entry user status could not be found
	 */
	public static AssetEntryUserStatus findByUuid_Last(
			String uuid,
			OrderByComparator<AssetEntryUserStatus> orderByComparator)
		throws com.liferay.asset.ext.exception.
			NoSuchAssetEntryUserStatusException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last asset entry user status in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry user status, or <code>null</code> if a matching asset entry user status could not be found
	 */
	public static AssetEntryUserStatus fetchByUuid_Last(
		String uuid,
		OrderByComparator<AssetEntryUserStatus> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the asset entry user statuses before and after the current asset entry user status in the ordered set where uuid = &#63;.
	 *
	 * @param assetEntryUserStatusPK the primary key of the current asset entry user status
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset entry user status
	 * @throws NoSuchAssetEntryUserStatusException if a asset entry user status with the primary key could not be found
	 */
	public static AssetEntryUserStatus[] findByUuid_PrevAndNext(
			AssetEntryUserStatusPK assetEntryUserStatusPK, String uuid,
			OrderByComparator<AssetEntryUserStatus> orderByComparator)
		throws com.liferay.asset.ext.exception.
			NoSuchAssetEntryUserStatusException {

		return getPersistence().findByUuid_PrevAndNext(
			assetEntryUserStatusPK, uuid, orderByComparator);
	}

	/**
	 * Removes all the asset entry user statuses where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of asset entry user statuses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching asset entry user statuses
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the asset entry user status where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchAssetEntryUserStatusException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching asset entry user status
	 * @throws NoSuchAssetEntryUserStatusException if a matching asset entry user status could not be found
	 */
	public static AssetEntryUserStatus findByUUID_G(String uuid, long groupId)
		throws com.liferay.asset.ext.exception.
			NoSuchAssetEntryUserStatusException {

		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the asset entry user status where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching asset entry user status, or <code>null</code> if a matching asset entry user status could not be found
	 */
	public static AssetEntryUserStatus fetchByUUID_G(
		String uuid, long groupId) {

		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the asset entry user status where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching asset entry user status, or <code>null</code> if a matching asset entry user status could not be found
	 */
	public static AssetEntryUserStatus fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		return getPersistence().fetchByUUID_G(uuid, groupId, useFinderCache);
	}

	/**
	 * Removes the asset entry user status where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the asset entry user status that was removed
	 */
	public static AssetEntryUserStatus removeByUUID_G(String uuid, long groupId)
		throws com.liferay.asset.ext.exception.
			NoSuchAssetEntryUserStatusException {

		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the number of asset entry user statuses where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching asset entry user statuses
	 */
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	 * Returns all the asset entry user statuses where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching asset entry user statuses
	 */
	public static List<AssetEntryUserStatus> findByUuid_C(
		String uuid, long companyId) {

		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the asset entry user statuses where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetEntryUserStatusModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of asset entry user statuses
	 * @param end the upper bound of the range of asset entry user statuses (not inclusive)
	 * @return the range of matching asset entry user statuses
	 */
	public static List<AssetEntryUserStatus> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the asset entry user statuses where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetEntryUserStatusModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of asset entry user statuses
	 * @param end the upper bound of the range of asset entry user statuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset entry user statuses
	 */
	public static List<AssetEntryUserStatus> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<AssetEntryUserStatus> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the asset entry user statuses where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetEntryUserStatusModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of asset entry user statuses
	 * @param end the upper bound of the range of asset entry user statuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching asset entry user statuses
	 */
	public static List<AssetEntryUserStatus> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<AssetEntryUserStatus> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first asset entry user status in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry user status
	 * @throws NoSuchAssetEntryUserStatusException if a matching asset entry user status could not be found
	 */
	public static AssetEntryUserStatus findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<AssetEntryUserStatus> orderByComparator)
		throws com.liferay.asset.ext.exception.
			NoSuchAssetEntryUserStatusException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first asset entry user status in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry user status, or <code>null</code> if a matching asset entry user status could not be found
	 */
	public static AssetEntryUserStatus fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<AssetEntryUserStatus> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last asset entry user status in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry user status
	 * @throws NoSuchAssetEntryUserStatusException if a matching asset entry user status could not be found
	 */
	public static AssetEntryUserStatus findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<AssetEntryUserStatus> orderByComparator)
		throws com.liferay.asset.ext.exception.
			NoSuchAssetEntryUserStatusException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last asset entry user status in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry user status, or <code>null</code> if a matching asset entry user status could not be found
	 */
	public static AssetEntryUserStatus fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<AssetEntryUserStatus> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the asset entry user statuses before and after the current asset entry user status in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param assetEntryUserStatusPK the primary key of the current asset entry user status
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset entry user status
	 * @throws NoSuchAssetEntryUserStatusException if a asset entry user status with the primary key could not be found
	 */
	public static AssetEntryUserStatus[] findByUuid_C_PrevAndNext(
			AssetEntryUserStatusPK assetEntryUserStatusPK, String uuid,
			long companyId,
			OrderByComparator<AssetEntryUserStatus> orderByComparator)
		throws com.liferay.asset.ext.exception.
			NoSuchAssetEntryUserStatusException {

		return getPersistence().findByUuid_C_PrevAndNext(
			assetEntryUserStatusPK, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the asset entry user statuses where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of asset entry user statuses where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching asset entry user statuses
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns all the asset entry user statuses where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching asset entry user statuses
	 */
	public static List<AssetEntryUserStatus> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	 * Returns a range of all the asset entry user statuses where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetEntryUserStatusModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of asset entry user statuses
	 * @param end the upper bound of the range of asset entry user statuses (not inclusive)
	 * @return the range of matching asset entry user statuses
	 */
	public static List<AssetEntryUserStatus> findByGroupId(
		long groupId, int start, int end) {

		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	 * Returns an ordered range of all the asset entry user statuses where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetEntryUserStatusModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of asset entry user statuses
	 * @param end the upper bound of the range of asset entry user statuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset entry user statuses
	 */
	public static List<AssetEntryUserStatus> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<AssetEntryUserStatus> orderByComparator) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the asset entry user statuses where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetEntryUserStatusModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of asset entry user statuses
	 * @param end the upper bound of the range of asset entry user statuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching asset entry user statuses
	 */
	public static List<AssetEntryUserStatus> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<AssetEntryUserStatus> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first asset entry user status in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry user status
	 * @throws NoSuchAssetEntryUserStatusException if a matching asset entry user status could not be found
	 */
	public static AssetEntryUserStatus findByGroupId_First(
			long groupId,
			OrderByComparator<AssetEntryUserStatus> orderByComparator)
		throws com.liferay.asset.ext.exception.
			NoSuchAssetEntryUserStatusException {

		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	 * Returns the first asset entry user status in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry user status, or <code>null</code> if a matching asset entry user status could not be found
	 */
	public static AssetEntryUserStatus fetchByGroupId_First(
		long groupId,
		OrderByComparator<AssetEntryUserStatus> orderByComparator) {

		return getPersistence().fetchByGroupId_First(
			groupId, orderByComparator);
	}

	/**
	 * Returns the last asset entry user status in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry user status
	 * @throws NoSuchAssetEntryUserStatusException if a matching asset entry user status could not be found
	 */
	public static AssetEntryUserStatus findByGroupId_Last(
			long groupId,
			OrderByComparator<AssetEntryUserStatus> orderByComparator)
		throws com.liferay.asset.ext.exception.
			NoSuchAssetEntryUserStatusException {

		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the last asset entry user status in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry user status, or <code>null</code> if a matching asset entry user status could not be found
	 */
	public static AssetEntryUserStatus fetchByGroupId_Last(
		long groupId,
		OrderByComparator<AssetEntryUserStatus> orderByComparator) {

		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the asset entry user statuses before and after the current asset entry user status in the ordered set where groupId = &#63;.
	 *
	 * @param assetEntryUserStatusPK the primary key of the current asset entry user status
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset entry user status
	 * @throws NoSuchAssetEntryUserStatusException if a asset entry user status with the primary key could not be found
	 */
	public static AssetEntryUserStatus[] findByGroupId_PrevAndNext(
			AssetEntryUserStatusPK assetEntryUserStatusPK, long groupId,
			OrderByComparator<AssetEntryUserStatus> orderByComparator)
		throws com.liferay.asset.ext.exception.
			NoSuchAssetEntryUserStatusException {

		return getPersistence().findByGroupId_PrevAndNext(
			assetEntryUserStatusPK, groupId, orderByComparator);
	}

	/**
	 * Removes all the asset entry user statuses where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	 * Returns the number of asset entry user statuses where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching asset entry user statuses
	 */
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	 * Returns all the asset entry user statuses where userId = &#63; and entryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param entryId the entry ID
	 * @return the matching asset entry user statuses
	 */
	public static List<AssetEntryUserStatus> findByUserIdEntryId(
		long userId, long entryId) {

		return getPersistence().findByUserIdEntryId(userId, entryId);
	}

	/**
	 * Returns a range of all the asset entry user statuses where userId = &#63; and entryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetEntryUserStatusModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param entryId the entry ID
	 * @param start the lower bound of the range of asset entry user statuses
	 * @param end the upper bound of the range of asset entry user statuses (not inclusive)
	 * @return the range of matching asset entry user statuses
	 */
	public static List<AssetEntryUserStatus> findByUserIdEntryId(
		long userId, long entryId, int start, int end) {

		return getPersistence().findByUserIdEntryId(
			userId, entryId, start, end);
	}

	/**
	 * Returns an ordered range of all the asset entry user statuses where userId = &#63; and entryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetEntryUserStatusModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param entryId the entry ID
	 * @param start the lower bound of the range of asset entry user statuses
	 * @param end the upper bound of the range of asset entry user statuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching asset entry user statuses
	 */
	public static List<AssetEntryUserStatus> findByUserIdEntryId(
		long userId, long entryId, int start, int end,
		OrderByComparator<AssetEntryUserStatus> orderByComparator) {

		return getPersistence().findByUserIdEntryId(
			userId, entryId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the asset entry user statuses where userId = &#63; and entryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetEntryUserStatusModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param entryId the entry ID
	 * @param start the lower bound of the range of asset entry user statuses
	 * @param end the upper bound of the range of asset entry user statuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching asset entry user statuses
	 */
	public static List<AssetEntryUserStatus> findByUserIdEntryId(
		long userId, long entryId, int start, int end,
		OrderByComparator<AssetEntryUserStatus> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUserIdEntryId(
			userId, entryId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first asset entry user status in the ordered set where userId = &#63; and entryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param entryId the entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry user status
	 * @throws NoSuchAssetEntryUserStatusException if a matching asset entry user status could not be found
	 */
	public static AssetEntryUserStatus findByUserIdEntryId_First(
			long userId, long entryId,
			OrderByComparator<AssetEntryUserStatus> orderByComparator)
		throws com.liferay.asset.ext.exception.
			NoSuchAssetEntryUserStatusException {

		return getPersistence().findByUserIdEntryId_First(
			userId, entryId, orderByComparator);
	}

	/**
	 * Returns the first asset entry user status in the ordered set where userId = &#63; and entryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param entryId the entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry user status, or <code>null</code> if a matching asset entry user status could not be found
	 */
	public static AssetEntryUserStatus fetchByUserIdEntryId_First(
		long userId, long entryId,
		OrderByComparator<AssetEntryUserStatus> orderByComparator) {

		return getPersistence().fetchByUserIdEntryId_First(
			userId, entryId, orderByComparator);
	}

	/**
	 * Returns the last asset entry user status in the ordered set where userId = &#63; and entryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param entryId the entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry user status
	 * @throws NoSuchAssetEntryUserStatusException if a matching asset entry user status could not be found
	 */
	public static AssetEntryUserStatus findByUserIdEntryId_Last(
			long userId, long entryId,
			OrderByComparator<AssetEntryUserStatus> orderByComparator)
		throws com.liferay.asset.ext.exception.
			NoSuchAssetEntryUserStatusException {

		return getPersistence().findByUserIdEntryId_Last(
			userId, entryId, orderByComparator);
	}

	/**
	 * Returns the last asset entry user status in the ordered set where userId = &#63; and entryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param entryId the entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry user status, or <code>null</code> if a matching asset entry user status could not be found
	 */
	public static AssetEntryUserStatus fetchByUserIdEntryId_Last(
		long userId, long entryId,
		OrderByComparator<AssetEntryUserStatus> orderByComparator) {

		return getPersistence().fetchByUserIdEntryId_Last(
			userId, entryId, orderByComparator);
	}

	/**
	 * Returns the asset entry user statuses before and after the current asset entry user status in the ordered set where userId = &#63; and entryId = &#63;.
	 *
	 * @param assetEntryUserStatusPK the primary key of the current asset entry user status
	 * @param userId the user ID
	 * @param entryId the entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset entry user status
	 * @throws NoSuchAssetEntryUserStatusException if a asset entry user status with the primary key could not be found
	 */
	public static AssetEntryUserStatus[] findByUserIdEntryId_PrevAndNext(
			AssetEntryUserStatusPK assetEntryUserStatusPK, long userId,
			long entryId,
			OrderByComparator<AssetEntryUserStatus> orderByComparator)
		throws com.liferay.asset.ext.exception.
			NoSuchAssetEntryUserStatusException {

		return getPersistence().findByUserIdEntryId_PrevAndNext(
			assetEntryUserStatusPK, userId, entryId, orderByComparator);
	}

	/**
	 * Removes all the asset entry user statuses where userId = &#63; and entryId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param entryId the entry ID
	 */
	public static void removeByUserIdEntryId(long userId, long entryId) {
		getPersistence().removeByUserIdEntryId(userId, entryId);
	}

	/**
	 * Returns the number of asset entry user statuses where userId = &#63; and entryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param entryId the entry ID
	 * @return the number of matching asset entry user statuses
	 */
	public static int countByUserIdEntryId(long userId, long entryId) {
		return getPersistence().countByUserIdEntryId(userId, entryId);
	}

	/**
	 * Caches the asset entry user status in the entity cache if it is enabled.
	 *
	 * @param assetEntryUserStatus the asset entry user status
	 */
	public static void cacheResult(AssetEntryUserStatus assetEntryUserStatus) {
		getPersistence().cacheResult(assetEntryUserStatus);
	}

	/**
	 * Caches the asset entry user statuses in the entity cache if it is enabled.
	 *
	 * @param assetEntryUserStatuses the asset entry user statuses
	 */
	public static void cacheResult(
		List<AssetEntryUserStatus> assetEntryUserStatuses) {

		getPersistence().cacheResult(assetEntryUserStatuses);
	}

	/**
	 * Creates a new asset entry user status with the primary key. Does not add the asset entry user status to the database.
	 *
	 * @param assetEntryUserStatusPK the primary key for the new asset entry user status
	 * @return the new asset entry user status
	 */
	public static AssetEntryUserStatus create(
		AssetEntryUserStatusPK assetEntryUserStatusPK) {

		return getPersistence().create(assetEntryUserStatusPK);
	}

	/**
	 * Removes the asset entry user status with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param assetEntryUserStatusPK the primary key of the asset entry user status
	 * @return the asset entry user status that was removed
	 * @throws NoSuchAssetEntryUserStatusException if a asset entry user status with the primary key could not be found
	 */
	public static AssetEntryUserStatus remove(
			AssetEntryUserStatusPK assetEntryUserStatusPK)
		throws com.liferay.asset.ext.exception.
			NoSuchAssetEntryUserStatusException {

		return getPersistence().remove(assetEntryUserStatusPK);
	}

	public static AssetEntryUserStatus updateImpl(
		AssetEntryUserStatus assetEntryUserStatus) {

		return getPersistence().updateImpl(assetEntryUserStatus);
	}

	/**
	 * Returns the asset entry user status with the primary key or throws a <code>NoSuchAssetEntryUserStatusException</code> if it could not be found.
	 *
	 * @param assetEntryUserStatusPK the primary key of the asset entry user status
	 * @return the asset entry user status
	 * @throws NoSuchAssetEntryUserStatusException if a asset entry user status with the primary key could not be found
	 */
	public static AssetEntryUserStatus findByPrimaryKey(
			AssetEntryUserStatusPK assetEntryUserStatusPK)
		throws com.liferay.asset.ext.exception.
			NoSuchAssetEntryUserStatusException {

		return getPersistence().findByPrimaryKey(assetEntryUserStatusPK);
	}

	/**
	 * Returns the asset entry user status with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param assetEntryUserStatusPK the primary key of the asset entry user status
	 * @return the asset entry user status, or <code>null</code> if a asset entry user status with the primary key could not be found
	 */
	public static AssetEntryUserStatus fetchByPrimaryKey(
		AssetEntryUserStatusPK assetEntryUserStatusPK) {

		return getPersistence().fetchByPrimaryKey(assetEntryUserStatusPK);
	}

	/**
	 * Returns all the asset entry user statuses.
	 *
	 * @return the asset entry user statuses
	 */
	public static List<AssetEntryUserStatus> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the asset entry user statuses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetEntryUserStatusModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset entry user statuses
	 * @param end the upper bound of the range of asset entry user statuses (not inclusive)
	 * @return the range of asset entry user statuses
	 */
	public static List<AssetEntryUserStatus> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the asset entry user statuses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetEntryUserStatusModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset entry user statuses
	 * @param end the upper bound of the range of asset entry user statuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of asset entry user statuses
	 */
	public static List<AssetEntryUserStatus> findAll(
		int start, int end,
		OrderByComparator<AssetEntryUserStatus> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the asset entry user statuses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AssetEntryUserStatusModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset entry user statuses
	 * @param end the upper bound of the range of asset entry user statuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of asset entry user statuses
	 */
	public static List<AssetEntryUserStatus> findAll(
		int start, int end,
		OrderByComparator<AssetEntryUserStatus> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the asset entry user statuses from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of asset entry user statuses.
	 *
	 * @return the number of asset entry user statuses
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getCompoundPKColumnNames() {
		return getPersistence().getCompoundPKColumnNames();
	}

	public static AssetEntryUserStatusPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<AssetEntryUserStatusPersistence, AssetEntryUserStatusPersistence>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			AssetEntryUserStatusPersistence.class);

		ServiceTracker
			<AssetEntryUserStatusPersistence, AssetEntryUserStatusPersistence>
				serviceTracker =
					new ServiceTracker
						<AssetEntryUserStatusPersistence,
						 AssetEntryUserStatusPersistence>(
							 bundle.getBundleContext(),
							 AssetEntryUserStatusPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}