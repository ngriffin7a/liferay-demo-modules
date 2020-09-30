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

package com.liferay.asset.ext.service.impl;

import com.liferay.asset.ext.model.AssetEntryUserStatus;
import com.liferay.asset.ext.service.base.AssetEntryUserStatusLocalServiceBaseImpl;
import com.liferay.asset.ext.service.persistence.AssetEntryUserStatusPK;
import com.liferay.asset.ext.service.persistence.AssetEntryUserStatusPersistence;
import com.liferay.portal.aop.AopService;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * The implementation of the asset entry user status local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.liferay.asset.ext.service.AssetEntryUserStatusLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntryUserStatusLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=com.liferay.asset.ext.model.AssetEntryUserStatus",
	service = AopService.class
)
public class AssetEntryUserStatusLocalServiceImpl
	extends AssetEntryUserStatusLocalServiceBaseImpl {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>com.liferay.asset.ext.service.AssetEntryUserStatusLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.asset.ext.service.AssetEntryUserStatusLocalServiceUtil</code>.
	 */
	public AssetEntryUserStatus addAssetEntryUserStatus(
		long entryId, long companyId, long groupId, long userId,
		boolean acknowledged, boolean downloaded, boolean read) {

		long id = counterLocalService.increment();

		AssetEntryUserStatus assetEntryUserStatus = createAssetEntryUserStatus(
			new AssetEntryUserStatusPK(id, entryId));

		assetEntryUserStatus.setCompanyId(companyId);
		assetEntryUserStatus.setGroupId(groupId);
		assetEntryUserStatus.setUserId(userId);

		Calendar calendar = Calendar.getInstance();

		Date todayDate = calendar.getTime();

		if (acknowledged) {
			assetEntryUserStatus.setAcknowledged(acknowledged);
			assetEntryUserStatus.setAcknowledgementDate(todayDate);
		}

		if (downloaded) {
			assetEntryUserStatus.setDownloaded(downloaded);
			assetEntryUserStatus.setDownloadDate(todayDate);
		}

		if (read) {
			assetEntryUserStatus.setRead(true);
			assetEntryUserStatus.setReadDate(todayDate);
		}

		addAssetEntryUserStatus(assetEntryUserStatus);

		return assetEntryUserStatus;
	}

	public void deleteAllByGroupId(long groupId) {
		List<AssetEntryUserStatus> assetEntryUserStatuses =
			_assetEntryUserStatusPersistence.findByGroupId(groupId);

		for (AssetEntryUserStatus assetEntryUserStatus :
				assetEntryUserStatuses) {

			deleteAssetEntryUserStatus(assetEntryUserStatus);
		}
	}

	public Optional<AssetEntryUserStatus>
		getAssetEntryUserStatusByEntryIdUserId(long entryId, long userId) {

		List<AssetEntryUserStatus> assetEntryUserStatuses =
			_assetEntryUserStatusPersistence.findByUserIdEntryId(
				userId, entryId);

		if (assetEntryUserStatuses.isEmpty()) {
			return Optional.empty();
		}

		return Optional.of(assetEntryUserStatuses.get(0));
	}

	@Reference
	private AssetEntryUserStatusPersistence _assetEntryUserStatusPersistence;

}