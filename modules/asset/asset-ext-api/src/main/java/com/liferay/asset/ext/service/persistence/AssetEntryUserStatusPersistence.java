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

import com.liferay.asset.ext.exception.NoSuchAssetEntryUserStatusException;
import com.liferay.asset.ext.model.AssetEntryUserStatus;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.util.Set;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the asset entry user status service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntryUserStatusUtil
 * @generated
 */
@ProviderType
public interface AssetEntryUserStatusPersistence
	extends BasePersistence<AssetEntryUserStatus> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AssetEntryUserStatusUtil} to access the asset entry user status persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the asset entry user statuses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching asset entry user statuses
	 */
	public java.util.List<AssetEntryUserStatus> findByUuid(String uuid);

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
	public java.util.List<AssetEntryUserStatus> findByUuid(
		String uuid, int start, int end);

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
	public java.util.List<AssetEntryUserStatus> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntryUserStatus>
			orderByComparator);

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
	public java.util.List<AssetEntryUserStatus> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntryUserStatus>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first asset entry user status in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry user status
	 * @throws NoSuchAssetEntryUserStatusException if a matching asset entry user status could not be found
	 */
	public AssetEntryUserStatus findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator
				<AssetEntryUserStatus> orderByComparator)
		throws NoSuchAssetEntryUserStatusException;

	/**
	 * Returns the first asset entry user status in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry user status, or <code>null</code> if a matching asset entry user status could not be found
	 */
	public AssetEntryUserStatus fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntryUserStatus>
			orderByComparator);

	/**
	 * Returns the last asset entry user status in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry user status
	 * @throws NoSuchAssetEntryUserStatusException if a matching asset entry user status could not be found
	 */
	public AssetEntryUserStatus findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator
				<AssetEntryUserStatus> orderByComparator)
		throws NoSuchAssetEntryUserStatusException;

	/**
	 * Returns the last asset entry user status in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry user status, or <code>null</code> if a matching asset entry user status could not be found
	 */
	public AssetEntryUserStatus fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntryUserStatus>
			orderByComparator);

	/**
	 * Returns the asset entry user statuses before and after the current asset entry user status in the ordered set where uuid = &#63;.
	 *
	 * @param assetEntryUserStatusPK the primary key of the current asset entry user status
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset entry user status
	 * @throws NoSuchAssetEntryUserStatusException if a asset entry user status with the primary key could not be found
	 */
	public AssetEntryUserStatus[] findByUuid_PrevAndNext(
			AssetEntryUserStatusPK assetEntryUserStatusPK, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator
				<AssetEntryUserStatus> orderByComparator)
		throws NoSuchAssetEntryUserStatusException;

	/**
	 * Removes all the asset entry user statuses where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of asset entry user statuses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching asset entry user statuses
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the asset entry user status where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchAssetEntryUserStatusException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching asset entry user status
	 * @throws NoSuchAssetEntryUserStatusException if a matching asset entry user status could not be found
	 */
	public AssetEntryUserStatus findByUUID_G(String uuid, long groupId)
		throws NoSuchAssetEntryUserStatusException;

	/**
	 * Returns the asset entry user status where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching asset entry user status, or <code>null</code> if a matching asset entry user status could not be found
	 */
	public AssetEntryUserStatus fetchByUUID_G(String uuid, long groupId);

	/**
	 * Returns the asset entry user status where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching asset entry user status, or <code>null</code> if a matching asset entry user status could not be found
	 */
	public AssetEntryUserStatus fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache);

	/**
	 * Removes the asset entry user status where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the asset entry user status that was removed
	 */
	public AssetEntryUserStatus removeByUUID_G(String uuid, long groupId)
		throws NoSuchAssetEntryUserStatusException;

	/**
	 * Returns the number of asset entry user statuses where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching asset entry user statuses
	 */
	public int countByUUID_G(String uuid, long groupId);

	/**
	 * Returns all the asset entry user statuses where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching asset entry user statuses
	 */
	public java.util.List<AssetEntryUserStatus> findByUuid_C(
		String uuid, long companyId);

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
	public java.util.List<AssetEntryUserStatus> findByUuid_C(
		String uuid, long companyId, int start, int end);

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
	public java.util.List<AssetEntryUserStatus> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntryUserStatus>
			orderByComparator);

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
	public java.util.List<AssetEntryUserStatus> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntryUserStatus>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first asset entry user status in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry user status
	 * @throws NoSuchAssetEntryUserStatusException if a matching asset entry user status could not be found
	 */
	public AssetEntryUserStatus findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator
				<AssetEntryUserStatus> orderByComparator)
		throws NoSuchAssetEntryUserStatusException;

	/**
	 * Returns the first asset entry user status in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry user status, or <code>null</code> if a matching asset entry user status could not be found
	 */
	public AssetEntryUserStatus fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntryUserStatus>
			orderByComparator);

	/**
	 * Returns the last asset entry user status in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry user status
	 * @throws NoSuchAssetEntryUserStatusException if a matching asset entry user status could not be found
	 */
	public AssetEntryUserStatus findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator
				<AssetEntryUserStatus> orderByComparator)
		throws NoSuchAssetEntryUserStatusException;

	/**
	 * Returns the last asset entry user status in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry user status, or <code>null</code> if a matching asset entry user status could not be found
	 */
	public AssetEntryUserStatus fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntryUserStatus>
			orderByComparator);

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
	public AssetEntryUserStatus[] findByUuid_C_PrevAndNext(
			AssetEntryUserStatusPK assetEntryUserStatusPK, String uuid,
			long companyId,
			com.liferay.portal.kernel.util.OrderByComparator
				<AssetEntryUserStatus> orderByComparator)
		throws NoSuchAssetEntryUserStatusException;

	/**
	 * Removes all the asset entry user statuses where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of asset entry user statuses where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching asset entry user statuses
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns all the asset entry user statuses where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching asset entry user statuses
	 */
	public java.util.List<AssetEntryUserStatus> findByGroupId(long groupId);

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
	public java.util.List<AssetEntryUserStatus> findByGroupId(
		long groupId, int start, int end);

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
	public java.util.List<AssetEntryUserStatus> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntryUserStatus>
			orderByComparator);

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
	public java.util.List<AssetEntryUserStatus> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntryUserStatus>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first asset entry user status in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry user status
	 * @throws NoSuchAssetEntryUserStatusException if a matching asset entry user status could not be found
	 */
	public AssetEntryUserStatus findByGroupId_First(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator
				<AssetEntryUserStatus> orderByComparator)
		throws NoSuchAssetEntryUserStatusException;

	/**
	 * Returns the first asset entry user status in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry user status, or <code>null</code> if a matching asset entry user status could not be found
	 */
	public AssetEntryUserStatus fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntryUserStatus>
			orderByComparator);

	/**
	 * Returns the last asset entry user status in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry user status
	 * @throws NoSuchAssetEntryUserStatusException if a matching asset entry user status could not be found
	 */
	public AssetEntryUserStatus findByGroupId_Last(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator
				<AssetEntryUserStatus> orderByComparator)
		throws NoSuchAssetEntryUserStatusException;

	/**
	 * Returns the last asset entry user status in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry user status, or <code>null</code> if a matching asset entry user status could not be found
	 */
	public AssetEntryUserStatus fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntryUserStatus>
			orderByComparator);

	/**
	 * Returns the asset entry user statuses before and after the current asset entry user status in the ordered set where groupId = &#63;.
	 *
	 * @param assetEntryUserStatusPK the primary key of the current asset entry user status
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next asset entry user status
	 * @throws NoSuchAssetEntryUserStatusException if a asset entry user status with the primary key could not be found
	 */
	public AssetEntryUserStatus[] findByGroupId_PrevAndNext(
			AssetEntryUserStatusPK assetEntryUserStatusPK, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator
				<AssetEntryUserStatus> orderByComparator)
		throws NoSuchAssetEntryUserStatusException;

	/**
	 * Removes all the asset entry user statuses where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public void removeByGroupId(long groupId);

	/**
	 * Returns the number of asset entry user statuses where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching asset entry user statuses
	 */
	public int countByGroupId(long groupId);

	/**
	 * Returns all the asset entry user statuses where userId = &#63; and entryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param entryId the entry ID
	 * @return the matching asset entry user statuses
	 */
	public java.util.List<AssetEntryUserStatus> findByUserIdEntryId(
		long userId, long entryId);

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
	public java.util.List<AssetEntryUserStatus> findByUserIdEntryId(
		long userId, long entryId, int start, int end);

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
	public java.util.List<AssetEntryUserStatus> findByUserIdEntryId(
		long userId, long entryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntryUserStatus>
			orderByComparator);

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
	public java.util.List<AssetEntryUserStatus> findByUserIdEntryId(
		long userId, long entryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntryUserStatus>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first asset entry user status in the ordered set where userId = &#63; and entryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param entryId the entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry user status
	 * @throws NoSuchAssetEntryUserStatusException if a matching asset entry user status could not be found
	 */
	public AssetEntryUserStatus findByUserIdEntryId_First(
			long userId, long entryId,
			com.liferay.portal.kernel.util.OrderByComparator
				<AssetEntryUserStatus> orderByComparator)
		throws NoSuchAssetEntryUserStatusException;

	/**
	 * Returns the first asset entry user status in the ordered set where userId = &#63; and entryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param entryId the entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry user status, or <code>null</code> if a matching asset entry user status could not be found
	 */
	public AssetEntryUserStatus fetchByUserIdEntryId_First(
		long userId, long entryId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntryUserStatus>
			orderByComparator);

	/**
	 * Returns the last asset entry user status in the ordered set where userId = &#63; and entryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param entryId the entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry user status
	 * @throws NoSuchAssetEntryUserStatusException if a matching asset entry user status could not be found
	 */
	public AssetEntryUserStatus findByUserIdEntryId_Last(
			long userId, long entryId,
			com.liferay.portal.kernel.util.OrderByComparator
				<AssetEntryUserStatus> orderByComparator)
		throws NoSuchAssetEntryUserStatusException;

	/**
	 * Returns the last asset entry user status in the ordered set where userId = &#63; and entryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param entryId the entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry user status, or <code>null</code> if a matching asset entry user status could not be found
	 */
	public AssetEntryUserStatus fetchByUserIdEntryId_Last(
		long userId, long entryId,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntryUserStatus>
			orderByComparator);

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
	public AssetEntryUserStatus[] findByUserIdEntryId_PrevAndNext(
			AssetEntryUserStatusPK assetEntryUserStatusPK, long userId,
			long entryId,
			com.liferay.portal.kernel.util.OrderByComparator
				<AssetEntryUserStatus> orderByComparator)
		throws NoSuchAssetEntryUserStatusException;

	/**
	 * Removes all the asset entry user statuses where userId = &#63; and entryId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param entryId the entry ID
	 */
	public void removeByUserIdEntryId(long userId, long entryId);

	/**
	 * Returns the number of asset entry user statuses where userId = &#63; and entryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param entryId the entry ID
	 * @return the number of matching asset entry user statuses
	 */
	public int countByUserIdEntryId(long userId, long entryId);

	/**
	 * Caches the asset entry user status in the entity cache if it is enabled.
	 *
	 * @param assetEntryUserStatus the asset entry user status
	 */
	public void cacheResult(AssetEntryUserStatus assetEntryUserStatus);

	/**
	 * Caches the asset entry user statuses in the entity cache if it is enabled.
	 *
	 * @param assetEntryUserStatuses the asset entry user statuses
	 */
	public void cacheResult(
		java.util.List<AssetEntryUserStatus> assetEntryUserStatuses);

	/**
	 * Creates a new asset entry user status with the primary key. Does not add the asset entry user status to the database.
	 *
	 * @param assetEntryUserStatusPK the primary key for the new asset entry user status
	 * @return the new asset entry user status
	 */
	public AssetEntryUserStatus create(
		AssetEntryUserStatusPK assetEntryUserStatusPK);

	/**
	 * Removes the asset entry user status with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param assetEntryUserStatusPK the primary key of the asset entry user status
	 * @return the asset entry user status that was removed
	 * @throws NoSuchAssetEntryUserStatusException if a asset entry user status with the primary key could not be found
	 */
	public AssetEntryUserStatus remove(
			AssetEntryUserStatusPK assetEntryUserStatusPK)
		throws NoSuchAssetEntryUserStatusException;

	public AssetEntryUserStatus updateImpl(
		AssetEntryUserStatus assetEntryUserStatus);

	/**
	 * Returns the asset entry user status with the primary key or throws a <code>NoSuchAssetEntryUserStatusException</code> if it could not be found.
	 *
	 * @param assetEntryUserStatusPK the primary key of the asset entry user status
	 * @return the asset entry user status
	 * @throws NoSuchAssetEntryUserStatusException if a asset entry user status with the primary key could not be found
	 */
	public AssetEntryUserStatus findByPrimaryKey(
			AssetEntryUserStatusPK assetEntryUserStatusPK)
		throws NoSuchAssetEntryUserStatusException;

	/**
	 * Returns the asset entry user status with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param assetEntryUserStatusPK the primary key of the asset entry user status
	 * @return the asset entry user status, or <code>null</code> if a asset entry user status with the primary key could not be found
	 */
	public AssetEntryUserStatus fetchByPrimaryKey(
		AssetEntryUserStatusPK assetEntryUserStatusPK);

	/**
	 * Returns all the asset entry user statuses.
	 *
	 * @return the asset entry user statuses
	 */
	public java.util.List<AssetEntryUserStatus> findAll();

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
	public java.util.List<AssetEntryUserStatus> findAll(int start, int end);

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
	public java.util.List<AssetEntryUserStatus> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntryUserStatus>
			orderByComparator);

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
	public java.util.List<AssetEntryUserStatus> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AssetEntryUserStatus>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the asset entry user statuses from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of asset entry user statuses.
	 *
	 * @return the number of asset entry user statuses
	 */
	public int countAll();

	public Set<String> getCompoundPKColumnNames();

}