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

package com.liferay.asset.ext.service.persistence.impl;

import com.liferay.asset.ext.exception.NoSuchAssetEntryUserStatusException;
import com.liferay.asset.ext.model.AssetEntryUserStatus;
import com.liferay.asset.ext.model.impl.AssetEntryUserStatusImpl;
import com.liferay.asset.ext.model.impl.AssetEntryUserStatusModelImpl;
import com.liferay.asset.ext.service.persistence.AssetEntryUserStatusPK;
import com.liferay.asset.ext.service.persistence.AssetEntryUserStatusPersistence;
import com.liferay.asset.ext.service.persistence.impl.constants.ASSET_EXTPersistenceConstants;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the asset entry user status service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = AssetEntryUserStatusPersistence.class)
public class AssetEntryUserStatusPersistenceImpl
	extends BasePersistenceImpl<AssetEntryUserStatus>
	implements AssetEntryUserStatusPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>AssetEntryUserStatusUtil</code> to access the asset entry user status persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		AssetEntryUserStatusImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByUuid;
	private FinderPath _finderPathWithoutPaginationFindByUuid;
	private FinderPath _finderPathCountByUuid;

	/**
	 * Returns all the asset entry user statuses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching asset entry user statuses
	 */
	@Override
	public List<AssetEntryUserStatus> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<AssetEntryUserStatus> findByUuid(
		String uuid, int start, int end) {

		return findByUuid(uuid, start, end, null);
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
	@Override
	public List<AssetEntryUserStatus> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<AssetEntryUserStatus> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
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
	@Override
	public List<AssetEntryUserStatus> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<AssetEntryUserStatus> orderByComparator,
		boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUuid;
				finderArgs = new Object[] {uuid};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUuid;
			finderArgs = new Object[] {uuid, start, end, orderByComparator};
		}

		List<AssetEntryUserStatus> list = null;

		if (useFinderCache) {
			list = (List<AssetEntryUserStatus>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetEntryUserStatus assetEntryUserStatus : list) {
					if (!uuid.equals(assetEntryUserStatus.getUuid())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_ASSETENTRYUSERSTATUS_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(AssetEntryUserStatusModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				list = (List<AssetEntryUserStatus>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first asset entry user status in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry user status
	 * @throws NoSuchAssetEntryUserStatusException if a matching asset entry user status could not be found
	 */
	@Override
	public AssetEntryUserStatus findByUuid_First(
			String uuid,
			OrderByComparator<AssetEntryUserStatus> orderByComparator)
		throws NoSuchAssetEntryUserStatusException {

		AssetEntryUserStatus assetEntryUserStatus = fetchByUuid_First(
			uuid, orderByComparator);

		if (assetEntryUserStatus != null) {
			return assetEntryUserStatus;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchAssetEntryUserStatusException(sb.toString());
	}

	/**
	 * Returns the first asset entry user status in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry user status, or <code>null</code> if a matching asset entry user status could not be found
	 */
	@Override
	public AssetEntryUserStatus fetchByUuid_First(
		String uuid,
		OrderByComparator<AssetEntryUserStatus> orderByComparator) {

		List<AssetEntryUserStatus> list = findByUuid(
			uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset entry user status in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry user status
	 * @throws NoSuchAssetEntryUserStatusException if a matching asset entry user status could not be found
	 */
	@Override
	public AssetEntryUserStatus findByUuid_Last(
			String uuid,
			OrderByComparator<AssetEntryUserStatus> orderByComparator)
		throws NoSuchAssetEntryUserStatusException {

		AssetEntryUserStatus assetEntryUserStatus = fetchByUuid_Last(
			uuid, orderByComparator);

		if (assetEntryUserStatus != null) {
			return assetEntryUserStatus;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchAssetEntryUserStatusException(sb.toString());
	}

	/**
	 * Returns the last asset entry user status in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry user status, or <code>null</code> if a matching asset entry user status could not be found
	 */
	@Override
	public AssetEntryUserStatus fetchByUuid_Last(
		String uuid,
		OrderByComparator<AssetEntryUserStatus> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<AssetEntryUserStatus> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetEntryUserStatus[] findByUuid_PrevAndNext(
			AssetEntryUserStatusPK assetEntryUserStatusPK, String uuid,
			OrderByComparator<AssetEntryUserStatus> orderByComparator)
		throws NoSuchAssetEntryUserStatusException {

		uuid = Objects.toString(uuid, "");

		AssetEntryUserStatus assetEntryUserStatus = findByPrimaryKey(
			assetEntryUserStatusPK);

		Session session = null;

		try {
			session = openSession();

			AssetEntryUserStatus[] array = new AssetEntryUserStatusImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, assetEntryUserStatus, uuid, orderByComparator, true);

			array[1] = assetEntryUserStatus;

			array[2] = getByUuid_PrevAndNext(
				session, assetEntryUserStatus, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetEntryUserStatus getByUuid_PrevAndNext(
		Session session, AssetEntryUserStatus assetEntryUserStatus, String uuid,
		OrderByComparator<AssetEntryUserStatus> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_ASSETENTRYUSERSTATUS_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(AssetEntryUserStatusModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindUuid) {
			queryPos.add(uuid);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						assetEntryUserStatus)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<AssetEntryUserStatus> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset entry user statuses where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (AssetEntryUserStatus assetEntryUserStatus :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(assetEntryUserStatus);
		}
	}

	/**
	 * Returns the number of asset entry user statuses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching asset entry user statuses
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_ASSETENTRYUSERSTATUS_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_UUID_2 =
		"assetEntryUserStatus.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(assetEntryUserStatus.uuid IS NULL OR assetEntryUserStatus.uuid = '')";

	private FinderPath _finderPathFetchByUUID_G;
	private FinderPath _finderPathCountByUUID_G;

	/**
	 * Returns the asset entry user status where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchAssetEntryUserStatusException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching asset entry user status
	 * @throws NoSuchAssetEntryUserStatusException if a matching asset entry user status could not be found
	 */
	@Override
	public AssetEntryUserStatus findByUUID_G(String uuid, long groupId)
		throws NoSuchAssetEntryUserStatusException {

		AssetEntryUserStatus assetEntryUserStatus = fetchByUUID_G(
			uuid, groupId);

		if (assetEntryUserStatus == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("uuid=");
			sb.append(uuid);

			sb.append(", groupId=");
			sb.append(groupId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchAssetEntryUserStatusException(sb.toString());
		}

		return assetEntryUserStatus;
	}

	/**
	 * Returns the asset entry user status where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching asset entry user status, or <code>null</code> if a matching asset entry user status could not be found
	 */
	@Override
	public AssetEntryUserStatus fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the asset entry user status where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching asset entry user status, or <code>null</code> if a matching asset entry user status could not be found
	 */
	@Override
	public AssetEntryUserStatus fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {uuid, groupId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByUUID_G, finderArgs, this);
		}

		if (result instanceof AssetEntryUserStatus) {
			AssetEntryUserStatus assetEntryUserStatus =
				(AssetEntryUserStatus)result;

			if (!Objects.equals(uuid, assetEntryUserStatus.getUuid()) ||
				(groupId != assetEntryUserStatus.getGroupId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_ASSETENTRYUSERSTATUS_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(groupId);

				List<AssetEntryUserStatus> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByUUID_G, finderArgs, list);
					}
				}
				else {
					AssetEntryUserStatus assetEntryUserStatus = list.get(0);

					result = assetEntryUserStatus;

					cacheResult(assetEntryUserStatus);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(
						_finderPathFetchByUUID_G, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (AssetEntryUserStatus)result;
		}
	}

	/**
	 * Removes the asset entry user status where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the asset entry user status that was removed
	 */
	@Override
	public AssetEntryUserStatus removeByUUID_G(String uuid, long groupId)
		throws NoSuchAssetEntryUserStatusException {

		AssetEntryUserStatus assetEntryUserStatus = findByUUID_G(uuid, groupId);

		return remove(assetEntryUserStatus);
	}

	/**
	 * Returns the number of asset entry user statuses where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching asset entry user statuses
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUUID_G;

		Object[] finderArgs = new Object[] {uuid, groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_ASSETENTRYUSERSTATUS_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(groupId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_G_UUID_2 =
		"assetEntryUserStatus.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_G_UUID_3 =
		"(assetEntryUserStatus.uuid IS NULL OR assetEntryUserStatus.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 =
		"assetEntryUserStatus.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the asset entry user statuses where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching asset entry user statuses
	 */
	@Override
	public List<AssetEntryUserStatus> findByUuid_C(
		String uuid, long companyId) {

		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<AssetEntryUserStatus> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
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
	@Override
	public List<AssetEntryUserStatus> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<AssetEntryUserStatus> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
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
	@Override
	public List<AssetEntryUserStatus> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<AssetEntryUserStatus> orderByComparator,
		boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUuid_C;
				finderArgs = new Object[] {uuid, companyId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUuid_C;
			finderArgs = new Object[] {
				uuid, companyId, start, end, orderByComparator
			};
		}

		List<AssetEntryUserStatus> list = null;

		if (useFinderCache) {
			list = (List<AssetEntryUserStatus>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetEntryUserStatus assetEntryUserStatus : list) {
					if (!uuid.equals(assetEntryUserStatus.getUuid()) ||
						(companyId != assetEntryUserStatus.getCompanyId())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(4);
			}

			sb.append(_SQL_SELECT_ASSETENTRYUSERSTATUS_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(AssetEntryUserStatusModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(companyId);

				list = (List<AssetEntryUserStatus>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public AssetEntryUserStatus findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<AssetEntryUserStatus> orderByComparator)
		throws NoSuchAssetEntryUserStatusException {

		AssetEntryUserStatus assetEntryUserStatus = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (assetEntryUserStatus != null) {
			return assetEntryUserStatus;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchAssetEntryUserStatusException(sb.toString());
	}

	/**
	 * Returns the first asset entry user status in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry user status, or <code>null</code> if a matching asset entry user status could not be found
	 */
	@Override
	public AssetEntryUserStatus fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<AssetEntryUserStatus> orderByComparator) {

		List<AssetEntryUserStatus> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetEntryUserStatus findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<AssetEntryUserStatus> orderByComparator)
		throws NoSuchAssetEntryUserStatusException {

		AssetEntryUserStatus assetEntryUserStatus = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (assetEntryUserStatus != null) {
			return assetEntryUserStatus;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchAssetEntryUserStatusException(sb.toString());
	}

	/**
	 * Returns the last asset entry user status in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry user status, or <code>null</code> if a matching asset entry user status could not be found
	 */
	@Override
	public AssetEntryUserStatus fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<AssetEntryUserStatus> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<AssetEntryUserStatus> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetEntryUserStatus[] findByUuid_C_PrevAndNext(
			AssetEntryUserStatusPK assetEntryUserStatusPK, String uuid,
			long companyId,
			OrderByComparator<AssetEntryUserStatus> orderByComparator)
		throws NoSuchAssetEntryUserStatusException {

		uuid = Objects.toString(uuid, "");

		AssetEntryUserStatus assetEntryUserStatus = findByPrimaryKey(
			assetEntryUserStatusPK);

		Session session = null;

		try {
			session = openSession();

			AssetEntryUserStatus[] array = new AssetEntryUserStatusImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, assetEntryUserStatus, uuid, companyId,
				orderByComparator, true);

			array[1] = assetEntryUserStatus;

			array[2] = getByUuid_C_PrevAndNext(
				session, assetEntryUserStatus, uuid, companyId,
				orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetEntryUserStatus getByUuid_C_PrevAndNext(
		Session session, AssetEntryUserStatus assetEntryUserStatus, String uuid,
		long companyId,
		OrderByComparator<AssetEntryUserStatus> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_ASSETENTRYUSERSTATUS_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(AssetEntryUserStatusModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindUuid) {
			queryPos.add(uuid);
		}

		queryPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						assetEntryUserStatus)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<AssetEntryUserStatus> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset entry user statuses where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (AssetEntryUserStatus assetEntryUserStatus :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(assetEntryUserStatus);
		}
	}

	/**
	 * Returns the number of asset entry user statuses where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching asset entry user statuses
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_ASSETENTRYUSERSTATUS_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(companyId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_C_UUID_2 =
		"assetEntryUserStatus.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(assetEntryUserStatus.uuid IS NULL OR assetEntryUserStatus.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"assetEntryUserStatus.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByGroupId;
	private FinderPath _finderPathWithoutPaginationFindByGroupId;
	private FinderPath _finderPathCountByGroupId;

	/**
	 * Returns all the asset entry user statuses where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching asset entry user statuses
	 */
	@Override
	public List<AssetEntryUserStatus> findByGroupId(long groupId) {
		return findByGroupId(
			groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<AssetEntryUserStatus> findByGroupId(
		long groupId, int start, int end) {

		return findByGroupId(groupId, start, end, null);
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
	@Override
	public List<AssetEntryUserStatus> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<AssetEntryUserStatus> orderByComparator) {

		return findByGroupId(groupId, start, end, orderByComparator, true);
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
	@Override
	public List<AssetEntryUserStatus> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<AssetEntryUserStatus> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByGroupId;
				finderArgs = new Object[] {groupId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByGroupId;
			finderArgs = new Object[] {groupId, start, end, orderByComparator};
		}

		List<AssetEntryUserStatus> list = null;

		if (useFinderCache) {
			list = (List<AssetEntryUserStatus>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetEntryUserStatus assetEntryUserStatus : list) {
					if (groupId != assetEntryUserStatus.getGroupId()) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_ASSETENTRYUSERSTATUS_WHERE);

			sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(AssetEntryUserStatusModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				list = (List<AssetEntryUserStatus>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first asset entry user status in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry user status
	 * @throws NoSuchAssetEntryUserStatusException if a matching asset entry user status could not be found
	 */
	@Override
	public AssetEntryUserStatus findByGroupId_First(
			long groupId,
			OrderByComparator<AssetEntryUserStatus> orderByComparator)
		throws NoSuchAssetEntryUserStatusException {

		AssetEntryUserStatus assetEntryUserStatus = fetchByGroupId_First(
			groupId, orderByComparator);

		if (assetEntryUserStatus != null) {
			return assetEntryUserStatus;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchAssetEntryUserStatusException(sb.toString());
	}

	/**
	 * Returns the first asset entry user status in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry user status, or <code>null</code> if a matching asset entry user status could not be found
	 */
	@Override
	public AssetEntryUserStatus fetchByGroupId_First(
		long groupId,
		OrderByComparator<AssetEntryUserStatus> orderByComparator) {

		List<AssetEntryUserStatus> list = findByGroupId(
			groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last asset entry user status in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry user status
	 * @throws NoSuchAssetEntryUserStatusException if a matching asset entry user status could not be found
	 */
	@Override
	public AssetEntryUserStatus findByGroupId_Last(
			long groupId,
			OrderByComparator<AssetEntryUserStatus> orderByComparator)
		throws NoSuchAssetEntryUserStatusException {

		AssetEntryUserStatus assetEntryUserStatus = fetchByGroupId_Last(
			groupId, orderByComparator);

		if (assetEntryUserStatus != null) {
			return assetEntryUserStatus;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchAssetEntryUserStatusException(sb.toString());
	}

	/**
	 * Returns the last asset entry user status in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry user status, or <code>null</code> if a matching asset entry user status could not be found
	 */
	@Override
	public AssetEntryUserStatus fetchByGroupId_Last(
		long groupId,
		OrderByComparator<AssetEntryUserStatus> orderByComparator) {

		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<AssetEntryUserStatus> list = findByGroupId(
			groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetEntryUserStatus[] findByGroupId_PrevAndNext(
			AssetEntryUserStatusPK assetEntryUserStatusPK, long groupId,
			OrderByComparator<AssetEntryUserStatus> orderByComparator)
		throws NoSuchAssetEntryUserStatusException {

		AssetEntryUserStatus assetEntryUserStatus = findByPrimaryKey(
			assetEntryUserStatusPK);

		Session session = null;

		try {
			session = openSession();

			AssetEntryUserStatus[] array = new AssetEntryUserStatusImpl[3];

			array[0] = getByGroupId_PrevAndNext(
				session, assetEntryUserStatus, groupId, orderByComparator,
				true);

			array[1] = assetEntryUserStatus;

			array[2] = getByGroupId_PrevAndNext(
				session, assetEntryUserStatus, groupId, orderByComparator,
				false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetEntryUserStatus getByGroupId_PrevAndNext(
		Session session, AssetEntryUserStatus assetEntryUserStatus,
		long groupId, OrderByComparator<AssetEntryUserStatus> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_ASSETENTRYUSERSTATUS_WHERE);

		sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(AssetEntryUserStatusModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(groupId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						assetEntryUserStatus)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<AssetEntryUserStatus> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset entry user statuses where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (AssetEntryUserStatus assetEntryUserStatus :
				findByGroupId(
					groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(assetEntryUserStatus);
		}
	}

	/**
	 * Returns the number of asset entry user statuses where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching asset entry user statuses
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = _finderPathCountByGroupId;

		Object[] finderArgs = new Object[] {groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_ASSETENTRYUSERSTATUS_WHERE);

			sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 =
		"assetEntryUserStatus.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUserIdEntryId;
	private FinderPath _finderPathWithoutPaginationFindByUserIdEntryId;
	private FinderPath _finderPathCountByUserIdEntryId;

	/**
	 * Returns all the asset entry user statuses where userId = &#63; and entryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param entryId the entry ID
	 * @return the matching asset entry user statuses
	 */
	@Override
	public List<AssetEntryUserStatus> findByUserIdEntryId(
		long userId, long entryId) {

		return findByUserIdEntryId(
			userId, entryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<AssetEntryUserStatus> findByUserIdEntryId(
		long userId, long entryId, int start, int end) {

		return findByUserIdEntryId(userId, entryId, start, end, null);
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
	@Override
	public List<AssetEntryUserStatus> findByUserIdEntryId(
		long userId, long entryId, int start, int end,
		OrderByComparator<AssetEntryUserStatus> orderByComparator) {

		return findByUserIdEntryId(
			userId, entryId, start, end, orderByComparator, true);
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
	@Override
	public List<AssetEntryUserStatus> findByUserIdEntryId(
		long userId, long entryId, int start, int end,
		OrderByComparator<AssetEntryUserStatus> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUserIdEntryId;
				finderArgs = new Object[] {userId, entryId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUserIdEntryId;
			finderArgs = new Object[] {
				userId, entryId, start, end, orderByComparator
			};
		}

		List<AssetEntryUserStatus> list = null;

		if (useFinderCache) {
			list = (List<AssetEntryUserStatus>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (AssetEntryUserStatus assetEntryUserStatus : list) {
					if ((userId != assetEntryUserStatus.getUserId()) ||
						(entryId != assetEntryUserStatus.getEntryId())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(4);
			}

			sb.append(_SQL_SELECT_ASSETENTRYUSERSTATUS_WHERE);

			sb.append(_FINDER_COLUMN_USERIDENTRYID_USERID_2);

			sb.append(_FINDER_COLUMN_USERIDENTRYID_ENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(AssetEntryUserStatusModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				queryPos.add(entryId);

				list = (List<AssetEntryUserStatus>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public AssetEntryUserStatus findByUserIdEntryId_First(
			long userId, long entryId,
			OrderByComparator<AssetEntryUserStatus> orderByComparator)
		throws NoSuchAssetEntryUserStatusException {

		AssetEntryUserStatus assetEntryUserStatus = fetchByUserIdEntryId_First(
			userId, entryId, orderByComparator);

		if (assetEntryUserStatus != null) {
			return assetEntryUserStatus;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append(", entryId=");
		sb.append(entryId);

		sb.append("}");

		throw new NoSuchAssetEntryUserStatusException(sb.toString());
	}

	/**
	 * Returns the first asset entry user status in the ordered set where userId = &#63; and entryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param entryId the entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching asset entry user status, or <code>null</code> if a matching asset entry user status could not be found
	 */
	@Override
	public AssetEntryUserStatus fetchByUserIdEntryId_First(
		long userId, long entryId,
		OrderByComparator<AssetEntryUserStatus> orderByComparator) {

		List<AssetEntryUserStatus> list = findByUserIdEntryId(
			userId, entryId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetEntryUserStatus findByUserIdEntryId_Last(
			long userId, long entryId,
			OrderByComparator<AssetEntryUserStatus> orderByComparator)
		throws NoSuchAssetEntryUserStatusException {

		AssetEntryUserStatus assetEntryUserStatus = fetchByUserIdEntryId_Last(
			userId, entryId, orderByComparator);

		if (assetEntryUserStatus != null) {
			return assetEntryUserStatus;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append(", entryId=");
		sb.append(entryId);

		sb.append("}");

		throw new NoSuchAssetEntryUserStatusException(sb.toString());
	}

	/**
	 * Returns the last asset entry user status in the ordered set where userId = &#63; and entryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param entryId the entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching asset entry user status, or <code>null</code> if a matching asset entry user status could not be found
	 */
	@Override
	public AssetEntryUserStatus fetchByUserIdEntryId_Last(
		long userId, long entryId,
		OrderByComparator<AssetEntryUserStatus> orderByComparator) {

		int count = countByUserIdEntryId(userId, entryId);

		if (count == 0) {
			return null;
		}

		List<AssetEntryUserStatus> list = findByUserIdEntryId(
			userId, entryId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public AssetEntryUserStatus[] findByUserIdEntryId_PrevAndNext(
			AssetEntryUserStatusPK assetEntryUserStatusPK, long userId,
			long entryId,
			OrderByComparator<AssetEntryUserStatus> orderByComparator)
		throws NoSuchAssetEntryUserStatusException {

		AssetEntryUserStatus assetEntryUserStatus = findByPrimaryKey(
			assetEntryUserStatusPK);

		Session session = null;

		try {
			session = openSession();

			AssetEntryUserStatus[] array = new AssetEntryUserStatusImpl[3];

			array[0] = getByUserIdEntryId_PrevAndNext(
				session, assetEntryUserStatus, userId, entryId,
				orderByComparator, true);

			array[1] = assetEntryUserStatus;

			array[2] = getByUserIdEntryId_PrevAndNext(
				session, assetEntryUserStatus, userId, entryId,
				orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected AssetEntryUserStatus getByUserIdEntryId_PrevAndNext(
		Session session, AssetEntryUserStatus assetEntryUserStatus, long userId,
		long entryId, OrderByComparator<AssetEntryUserStatus> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_ASSETENTRYUSERSTATUS_WHERE);

		sb.append(_FINDER_COLUMN_USERIDENTRYID_USERID_2);

		sb.append(_FINDER_COLUMN_USERIDENTRYID_ENTRYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(AssetEntryUserStatusModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(userId);

		queryPos.add(entryId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						assetEntryUserStatus)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<AssetEntryUserStatus> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the asset entry user statuses where userId = &#63; and entryId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param entryId the entry ID
	 */
	@Override
	public void removeByUserIdEntryId(long userId, long entryId) {
		for (AssetEntryUserStatus assetEntryUserStatus :
				findByUserIdEntryId(
					userId, entryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(assetEntryUserStatus);
		}
	}

	/**
	 * Returns the number of asset entry user statuses where userId = &#63; and entryId = &#63;.
	 *
	 * @param userId the user ID
	 * @param entryId the entry ID
	 * @return the number of matching asset entry user statuses
	 */
	@Override
	public int countByUserIdEntryId(long userId, long entryId) {
		FinderPath finderPath = _finderPathCountByUserIdEntryId;

		Object[] finderArgs = new Object[] {userId, entryId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_ASSETENTRYUSERSTATUS_WHERE);

			sb.append(_FINDER_COLUMN_USERIDENTRYID_USERID_2);

			sb.append(_FINDER_COLUMN_USERIDENTRYID_ENTRYID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				queryPos.add(entryId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_USERIDENTRYID_USERID_2 =
		"assetEntryUserStatus.userId = ? AND ";

	private static final String _FINDER_COLUMN_USERIDENTRYID_ENTRYID_2 =
		"assetEntryUserStatus.id.entryId = ?";

	public AssetEntryUserStatusPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");
		dbColumnNames.put("read", "read_");

		setDBColumnNames(dbColumnNames);

		setModelClass(AssetEntryUserStatus.class);

		setModelImplClass(AssetEntryUserStatusImpl.class);
		setModelPKClass(AssetEntryUserStatusPK.class);
	}

	/**
	 * Caches the asset entry user status in the entity cache if it is enabled.
	 *
	 * @param assetEntryUserStatus the asset entry user status
	 */
	@Override
	public void cacheResult(AssetEntryUserStatus assetEntryUserStatus) {
		entityCache.putResult(
			entityCacheEnabled, AssetEntryUserStatusImpl.class,
			assetEntryUserStatus.getPrimaryKey(), assetEntryUserStatus);

		finderCache.putResult(
			_finderPathFetchByUUID_G,
			new Object[] {
				assetEntryUserStatus.getUuid(),
				assetEntryUserStatus.getGroupId()
			},
			assetEntryUserStatus);

		assetEntryUserStatus.resetOriginalValues();
	}

	/**
	 * Caches the asset entry user statuses in the entity cache if it is enabled.
	 *
	 * @param assetEntryUserStatuses the asset entry user statuses
	 */
	@Override
	public void cacheResult(List<AssetEntryUserStatus> assetEntryUserStatuses) {
		for (AssetEntryUserStatus assetEntryUserStatus :
				assetEntryUserStatuses) {

			if (entityCache.getResult(
					entityCacheEnabled, AssetEntryUserStatusImpl.class,
					assetEntryUserStatus.getPrimaryKey()) == null) {

				cacheResult(assetEntryUserStatus);
			}
			else {
				assetEntryUserStatus.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all asset entry user statuses.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(AssetEntryUserStatusImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the asset entry user status.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AssetEntryUserStatus assetEntryUserStatus) {
		entityCache.removeResult(
			entityCacheEnabled, AssetEntryUserStatusImpl.class,
			assetEntryUserStatus.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(
			(AssetEntryUserStatusModelImpl)assetEntryUserStatus, true);
	}

	@Override
	public void clearCache(List<AssetEntryUserStatus> assetEntryUserStatuses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AssetEntryUserStatus assetEntryUserStatus :
				assetEntryUserStatuses) {

			entityCache.removeResult(
				entityCacheEnabled, AssetEntryUserStatusImpl.class,
				assetEntryUserStatus.getPrimaryKey());

			clearUniqueFindersCache(
				(AssetEntryUserStatusModelImpl)assetEntryUserStatus, true);
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				entityCacheEnabled, AssetEntryUserStatusImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		AssetEntryUserStatusModelImpl assetEntryUserStatusModelImpl) {

		Object[] args = new Object[] {
			assetEntryUserStatusModelImpl.getUuid(),
			assetEntryUserStatusModelImpl.getGroupId()
		};

		finderCache.putResult(
			_finderPathCountByUUID_G, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByUUID_G, args, assetEntryUserStatusModelImpl,
			false);
	}

	protected void clearUniqueFindersCache(
		AssetEntryUserStatusModelImpl assetEntryUserStatusModelImpl,
		boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				assetEntryUserStatusModelImpl.getUuid(),
				assetEntryUserStatusModelImpl.getGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}

		if ((assetEntryUserStatusModelImpl.getColumnBitmask() &
			 _finderPathFetchByUUID_G.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				assetEntryUserStatusModelImpl.getOriginalUuid(),
				assetEntryUserStatusModelImpl.getOriginalGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}
	}

	/**
	 * Creates a new asset entry user status with the primary key. Does not add the asset entry user status to the database.
	 *
	 * @param assetEntryUserStatusPK the primary key for the new asset entry user status
	 * @return the new asset entry user status
	 */
	@Override
	public AssetEntryUserStatus create(
		AssetEntryUserStatusPK assetEntryUserStatusPK) {

		AssetEntryUserStatus assetEntryUserStatus =
			new AssetEntryUserStatusImpl();

		assetEntryUserStatus.setNew(true);
		assetEntryUserStatus.setPrimaryKey(assetEntryUserStatusPK);

		String uuid = PortalUUIDUtil.generate();

		assetEntryUserStatus.setUuid(uuid);

		assetEntryUserStatus.setCompanyId(CompanyThreadLocal.getCompanyId());

		return assetEntryUserStatus;
	}

	/**
	 * Removes the asset entry user status with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param assetEntryUserStatusPK the primary key of the asset entry user status
	 * @return the asset entry user status that was removed
	 * @throws NoSuchAssetEntryUserStatusException if a asset entry user status with the primary key could not be found
	 */
	@Override
	public AssetEntryUserStatus remove(
			AssetEntryUserStatusPK assetEntryUserStatusPK)
		throws NoSuchAssetEntryUserStatusException {

		return remove((Serializable)assetEntryUserStatusPK);
	}

	/**
	 * Removes the asset entry user status with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the asset entry user status
	 * @return the asset entry user status that was removed
	 * @throws NoSuchAssetEntryUserStatusException if a asset entry user status with the primary key could not be found
	 */
	@Override
	public AssetEntryUserStatus remove(Serializable primaryKey)
		throws NoSuchAssetEntryUserStatusException {

		Session session = null;

		try {
			session = openSession();

			AssetEntryUserStatus assetEntryUserStatus =
				(AssetEntryUserStatus)session.get(
					AssetEntryUserStatusImpl.class, primaryKey);

			if (assetEntryUserStatus == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAssetEntryUserStatusException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(assetEntryUserStatus);
		}
		catch (NoSuchAssetEntryUserStatusException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected AssetEntryUserStatus removeImpl(
		AssetEntryUserStatus assetEntryUserStatus) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(assetEntryUserStatus)) {
				assetEntryUserStatus = (AssetEntryUserStatus)session.get(
					AssetEntryUserStatusImpl.class,
					assetEntryUserStatus.getPrimaryKeyObj());
			}

			if (assetEntryUserStatus != null) {
				session.delete(assetEntryUserStatus);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (assetEntryUserStatus != null) {
			clearCache(assetEntryUserStatus);
		}

		return assetEntryUserStatus;
	}

	@Override
	public AssetEntryUserStatus updateImpl(
		AssetEntryUserStatus assetEntryUserStatus) {

		boolean isNew = assetEntryUserStatus.isNew();

		if (!(assetEntryUserStatus instanceof AssetEntryUserStatusModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(assetEntryUserStatus.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					assetEntryUserStatus);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in assetEntryUserStatus proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom AssetEntryUserStatus implementation " +
					assetEntryUserStatus.getClass());
		}

		AssetEntryUserStatusModelImpl assetEntryUserStatusModelImpl =
			(AssetEntryUserStatusModelImpl)assetEntryUserStatus;

		if (Validator.isNull(assetEntryUserStatus.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			assetEntryUserStatus.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (assetEntryUserStatus.getCreateDate() == null)) {
			if (serviceContext == null) {
				assetEntryUserStatus.setCreateDate(now);
			}
			else {
				assetEntryUserStatus.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!assetEntryUserStatusModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				assetEntryUserStatus.setModifiedDate(now);
			}
			else {
				assetEntryUserStatus.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (assetEntryUserStatus.isNew()) {
				session.save(assetEntryUserStatus);

				assetEntryUserStatus.setNew(false);
			}
			else {
				assetEntryUserStatus = (AssetEntryUserStatus)session.merge(
					assetEntryUserStatus);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!_columnBitmaskEnabled) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {
				assetEntryUserStatusModelImpl.getUuid()
			};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			args = new Object[] {
				assetEntryUserStatusModelImpl.getUuid(),
				assetEntryUserStatusModelImpl.getCompanyId()
			};

			finderCache.removeResult(_finderPathCountByUuid_C, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid_C, args);

			args = new Object[] {assetEntryUserStatusModelImpl.getGroupId()};

			finderCache.removeResult(_finderPathCountByGroupId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByGroupId, args);

			args = new Object[] {
				assetEntryUserStatusModelImpl.getUserId(),
				assetEntryUserStatusModelImpl.getEntryId()
			};

			finderCache.removeResult(_finderPathCountByUserIdEntryId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUserIdEntryId, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((assetEntryUserStatusModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					assetEntryUserStatusModelImpl.getOriginalUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {assetEntryUserStatusModelImpl.getUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}

			if ((assetEntryUserStatusModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid_C.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					assetEntryUserStatusModelImpl.getOriginalUuid(),
					assetEntryUserStatusModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);

				args = new Object[] {
					assetEntryUserStatusModelImpl.getUuid(),
					assetEntryUserStatusModelImpl.getCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);
			}

			if ((assetEntryUserStatusModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByGroupId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					assetEntryUserStatusModelImpl.getOriginalGroupId()
				};

				finderCache.removeResult(_finderPathCountByGroupId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByGroupId, args);

				args = new Object[] {
					assetEntryUserStatusModelImpl.getGroupId()
				};

				finderCache.removeResult(_finderPathCountByGroupId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByGroupId, args);
			}

			if ((assetEntryUserStatusModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUserIdEntryId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					assetEntryUserStatusModelImpl.getOriginalUserId(),
					assetEntryUserStatusModelImpl.getOriginalEntryId()
				};

				finderCache.removeResult(_finderPathCountByUserIdEntryId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUserIdEntryId, args);

				args = new Object[] {
					assetEntryUserStatusModelImpl.getUserId(),
					assetEntryUserStatusModelImpl.getEntryId()
				};

				finderCache.removeResult(_finderPathCountByUserIdEntryId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUserIdEntryId, args);
			}
		}

		entityCache.putResult(
			entityCacheEnabled, AssetEntryUserStatusImpl.class,
			assetEntryUserStatus.getPrimaryKey(), assetEntryUserStatus, false);

		clearUniqueFindersCache(assetEntryUserStatusModelImpl, false);
		cacheUniqueFindersCache(assetEntryUserStatusModelImpl);

		assetEntryUserStatus.resetOriginalValues();

		return assetEntryUserStatus;
	}

	/**
	 * Returns the asset entry user status with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the asset entry user status
	 * @return the asset entry user status
	 * @throws NoSuchAssetEntryUserStatusException if a asset entry user status with the primary key could not be found
	 */
	@Override
	public AssetEntryUserStatus findByPrimaryKey(Serializable primaryKey)
		throws NoSuchAssetEntryUserStatusException {

		AssetEntryUserStatus assetEntryUserStatus = fetchByPrimaryKey(
			primaryKey);

		if (assetEntryUserStatus == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchAssetEntryUserStatusException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return assetEntryUserStatus;
	}

	/**
	 * Returns the asset entry user status with the primary key or throws a <code>NoSuchAssetEntryUserStatusException</code> if it could not be found.
	 *
	 * @param assetEntryUserStatusPK the primary key of the asset entry user status
	 * @return the asset entry user status
	 * @throws NoSuchAssetEntryUserStatusException if a asset entry user status with the primary key could not be found
	 */
	@Override
	public AssetEntryUserStatus findByPrimaryKey(
			AssetEntryUserStatusPK assetEntryUserStatusPK)
		throws NoSuchAssetEntryUserStatusException {

		return findByPrimaryKey((Serializable)assetEntryUserStatusPK);
	}

	/**
	 * Returns the asset entry user status with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param assetEntryUserStatusPK the primary key of the asset entry user status
	 * @return the asset entry user status, or <code>null</code> if a asset entry user status with the primary key could not be found
	 */
	@Override
	public AssetEntryUserStatus fetchByPrimaryKey(
		AssetEntryUserStatusPK assetEntryUserStatusPK) {

		return fetchByPrimaryKey((Serializable)assetEntryUserStatusPK);
	}

	/**
	 * Returns all the asset entry user statuses.
	 *
	 * @return the asset entry user statuses
	 */
	@Override
	public List<AssetEntryUserStatus> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<AssetEntryUserStatus> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<AssetEntryUserStatus> findAll(
		int start, int end,
		OrderByComparator<AssetEntryUserStatus> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<AssetEntryUserStatus> findAll(
		int start, int end,
		OrderByComparator<AssetEntryUserStatus> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<AssetEntryUserStatus> list = null;

		if (useFinderCache) {
			list = (List<AssetEntryUserStatus>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_ASSETENTRYUSERSTATUS);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_ASSETENTRYUSERSTATUS;

				sql = sql.concat(AssetEntryUserStatusModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<AssetEntryUserStatus>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the asset entry user statuses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (AssetEntryUserStatus assetEntryUserStatus : findAll()) {
			remove(assetEntryUserStatus);
		}
	}

	/**
	 * Returns the number of asset entry user statuses.
	 *
	 * @return the number of asset entry user statuses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(
					_SQL_COUNT_ASSETENTRYUSERSTATUS);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	public Set<String> getCompoundPKColumnNames() {
		return _compoundPKColumnNames;
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "assetEntryUserStatusPK";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_ASSETENTRYUSERSTATUS;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return AssetEntryUserStatusModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the asset entry user status persistence.
	 */
	@Activate
	public void activate() {
		AssetEntryUserStatusModelImpl.setEntityCacheEnabled(entityCacheEnabled);
		AssetEntryUserStatusModelImpl.setFinderCacheEnabled(finderCacheEnabled);

		_finderPathWithPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			AssetEntryUserStatusImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			AssetEntryUserStatusImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			AssetEntryUserStatusImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			AssetEntryUserStatusImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()},
			AssetEntryUserStatusModelImpl.UUID_COLUMN_BITMASK |
			AssetEntryUserStatusModelImpl.USERNAME_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});

		_finderPathFetchByUUID_G = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			AssetEntryUserStatusImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()},
			AssetEntryUserStatusModelImpl.UUID_COLUMN_BITMASK |
			AssetEntryUserStatusModelImpl.GROUPID_COLUMN_BITMASK);

		_finderPathCountByUUID_G = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			AssetEntryUserStatusImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			AssetEntryUserStatusImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			AssetEntryUserStatusModelImpl.UUID_COLUMN_BITMASK |
			AssetEntryUserStatusModelImpl.COMPANYID_COLUMN_BITMASK |
			AssetEntryUserStatusModelImpl.USERNAME_COLUMN_BITMASK);

		_finderPathCountByUuid_C = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByGroupId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			AssetEntryUserStatusImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByGroupId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			AssetEntryUserStatusImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] {Long.class.getName()},
			AssetEntryUserStatusModelImpl.GROUPID_COLUMN_BITMASK |
			AssetEntryUserStatusModelImpl.USERNAME_COLUMN_BITMASK);

		_finderPathCountByGroupId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByUserIdEntryId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			AssetEntryUserStatusImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserIdEntryId",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUserIdEntryId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			AssetEntryUserStatusImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserIdEntryId",
			new String[] {Long.class.getName(), Long.class.getName()},
			AssetEntryUserStatusModelImpl.USERID_COLUMN_BITMASK |
			AssetEntryUserStatusModelImpl.ENTRYID_COLUMN_BITMASK |
			AssetEntryUserStatusModelImpl.USERNAME_COLUMN_BITMASK);

		_finderPathCountByUserIdEntryId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserIdEntryId",
			new String[] {Long.class.getName(), Long.class.getName()});
	}

	@Deactivate
	public void deactivate() {
		entityCache.removeCache(AssetEntryUserStatusImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	@Reference(
		target = ASSET_EXTPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
		super.setConfiguration(configuration);

		_columnBitmaskEnabled = GetterUtil.getBoolean(
			configuration.get(
				"value.object.column.bitmask.enabled.com.liferay.asset.ext.model.AssetEntryUserStatus"),
			true);
	}

	@Override
	@Reference(
		target = ASSET_EXTPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = ASSET_EXTPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	private boolean _columnBitmaskEnabled;

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_ASSETENTRYUSERSTATUS =
		"SELECT assetEntryUserStatus FROM AssetEntryUserStatus assetEntryUserStatus";

	private static final String _SQL_SELECT_ASSETENTRYUSERSTATUS_WHERE =
		"SELECT assetEntryUserStatus FROM AssetEntryUserStatus assetEntryUserStatus WHERE ";

	private static final String _SQL_COUNT_ASSETENTRYUSERSTATUS =
		"SELECT COUNT(assetEntryUserStatus) FROM AssetEntryUserStatus assetEntryUserStatus";

	private static final String _SQL_COUNT_ASSETENTRYUSERSTATUS_WHERE =
		"SELECT COUNT(assetEntryUserStatus) FROM AssetEntryUserStatus assetEntryUserStatus WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"assetEntryUserStatus.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No AssetEntryUserStatus exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No AssetEntryUserStatus exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		AssetEntryUserStatusPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid", "read"});
	private static final Set<String> _compoundPKColumnNames = SetUtil.fromArray(
		new String[] {"assetEntryUserStatusId", "entryId"});

	static {
		try {
			Class.forName(ASSET_EXTPersistenceConstants.class.getName());
		}
		catch (ClassNotFoundException classNotFoundException) {
			throw new ExceptionInInitializerError(classNotFoundException);
		}
	}

}