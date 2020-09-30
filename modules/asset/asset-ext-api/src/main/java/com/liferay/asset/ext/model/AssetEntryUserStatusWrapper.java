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

package com.liferay.asset.ext.model;

import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link AssetEntryUserStatus}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntryUserStatus
 * @generated
 */
public class AssetEntryUserStatusWrapper
	extends BaseModelWrapper<AssetEntryUserStatus>
	implements AssetEntryUserStatus, ModelWrapper<AssetEntryUserStatus> {

	public AssetEntryUserStatusWrapper(
		AssetEntryUserStatus assetEntryUserStatus) {

		super(assetEntryUserStatus);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("assetEntryUserStatusId", getAssetEntryUserStatusId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("entryId", getEntryId());
		attributes.put("acknowledged", isAcknowledged());
		attributes.put("acknowledgementDate", getAcknowledgementDate());
		attributes.put("downloaded", isDownloaded());
		attributes.put("downloadDate", getDownloadDate());
		attributes.put("read", isRead());
		attributes.put("readDate", getReadDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long assetEntryUserStatusId = (Long)attributes.get(
			"assetEntryUserStatusId");

		if (assetEntryUserStatusId != null) {
			setAssetEntryUserStatusId(assetEntryUserStatusId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long entryId = (Long)attributes.get("entryId");

		if (entryId != null) {
			setEntryId(entryId);
		}

		Boolean acknowledged = (Boolean)attributes.get("acknowledged");

		if (acknowledged != null) {
			setAcknowledged(acknowledged);
		}

		Date acknowledgementDate = (Date)attributes.get("acknowledgementDate");

		if (acknowledgementDate != null) {
			setAcknowledgementDate(acknowledgementDate);
		}

		Boolean downloaded = (Boolean)attributes.get("downloaded");

		if (downloaded != null) {
			setDownloaded(downloaded);
		}

		Date downloadDate = (Date)attributes.get("downloadDate");

		if (downloadDate != null) {
			setDownloadDate(downloadDate);
		}

		Boolean read = (Boolean)attributes.get("read");

		if (read != null) {
			setRead(read);
		}

		Date readDate = (Date)attributes.get("readDate");

		if (readDate != null) {
			setReadDate(readDate);
		}
	}

	/**
	 * Returns the acknowledged of this asset entry user status.
	 *
	 * @return the acknowledged of this asset entry user status
	 */
	@Override
	public boolean getAcknowledged() {
		return model.getAcknowledged();
	}

	/**
	 * Returns the acknowledgement date of this asset entry user status.
	 *
	 * @return the acknowledgement date of this asset entry user status
	 */
	@Override
	public Date getAcknowledgementDate() {
		return model.getAcknowledgementDate();
	}

	/**
	 * Returns the asset entry user status ID of this asset entry user status.
	 *
	 * @return the asset entry user status ID of this asset entry user status
	 */
	@Override
	public long getAssetEntryUserStatusId() {
		return model.getAssetEntryUserStatusId();
	}

	/**
	 * Returns the company ID of this asset entry user status.
	 *
	 * @return the company ID of this asset entry user status
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this asset entry user status.
	 *
	 * @return the create date of this asset entry user status
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the download date of this asset entry user status.
	 *
	 * @return the download date of this asset entry user status
	 */
	@Override
	public Date getDownloadDate() {
		return model.getDownloadDate();
	}

	/**
	 * Returns the downloaded of this asset entry user status.
	 *
	 * @return the downloaded of this asset entry user status
	 */
	@Override
	public boolean getDownloaded() {
		return model.getDownloaded();
	}

	/**
	 * Returns the entry ID of this asset entry user status.
	 *
	 * @return the entry ID of this asset entry user status
	 */
	@Override
	public long getEntryId() {
		return model.getEntryId();
	}

	/**
	 * Returns the group ID of this asset entry user status.
	 *
	 * @return the group ID of this asset entry user status
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the modified date of this asset entry user status.
	 *
	 * @return the modified date of this asset entry user status
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the primary key of this asset entry user status.
	 *
	 * @return the primary key of this asset entry user status
	 */
	@Override
	public com.liferay.asset.ext.service.persistence.AssetEntryUserStatusPK
		getPrimaryKey() {

		return model.getPrimaryKey();
	}

	/**
	 * Returns the read of this asset entry user status.
	 *
	 * @return the read of this asset entry user status
	 */
	@Override
	public boolean getRead() {
		return model.getRead();
	}

	/**
	 * Returns the read date of this asset entry user status.
	 *
	 * @return the read date of this asset entry user status
	 */
	@Override
	public Date getReadDate() {
		return model.getReadDate();
	}

	/**
	 * Returns the user ID of this asset entry user status.
	 *
	 * @return the user ID of this asset entry user status
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this asset entry user status.
	 *
	 * @return the user name of this asset entry user status
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this asset entry user status.
	 *
	 * @return the user uuid of this asset entry user status
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this asset entry user status.
	 *
	 * @return the uuid of this asset entry user status
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	/**
	 * Returns <code>true</code> if this asset entry user status is acknowledged.
	 *
	 * @return <code>true</code> if this asset entry user status is acknowledged; <code>false</code> otherwise
	 */
	@Override
	public boolean isAcknowledged() {
		return model.isAcknowledged();
	}

	/**
	 * Returns <code>true</code> if this asset entry user status is downloaded.
	 *
	 * @return <code>true</code> if this asset entry user status is downloaded; <code>false</code> otherwise
	 */
	@Override
	public boolean isDownloaded() {
		return model.isDownloaded();
	}

	/**
	 * Returns <code>true</code> if this asset entry user status is read.
	 *
	 * @return <code>true</code> if this asset entry user status is read; <code>false</code> otherwise
	 */
	@Override
	public boolean isRead() {
		return model.isRead();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets whether this asset entry user status is acknowledged.
	 *
	 * @param acknowledged the acknowledged of this asset entry user status
	 */
	@Override
	public void setAcknowledged(boolean acknowledged) {
		model.setAcknowledged(acknowledged);
	}

	/**
	 * Sets the acknowledgement date of this asset entry user status.
	 *
	 * @param acknowledgementDate the acknowledgement date of this asset entry user status
	 */
	@Override
	public void setAcknowledgementDate(Date acknowledgementDate) {
		model.setAcknowledgementDate(acknowledgementDate);
	}

	/**
	 * Sets the asset entry user status ID of this asset entry user status.
	 *
	 * @param assetEntryUserStatusId the asset entry user status ID of this asset entry user status
	 */
	@Override
	public void setAssetEntryUserStatusId(long assetEntryUserStatusId) {
		model.setAssetEntryUserStatusId(assetEntryUserStatusId);
	}

	/**
	 * Sets the company ID of this asset entry user status.
	 *
	 * @param companyId the company ID of this asset entry user status
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this asset entry user status.
	 *
	 * @param createDate the create date of this asset entry user status
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the download date of this asset entry user status.
	 *
	 * @param downloadDate the download date of this asset entry user status
	 */
	@Override
	public void setDownloadDate(Date downloadDate) {
		model.setDownloadDate(downloadDate);
	}

	/**
	 * Sets whether this asset entry user status is downloaded.
	 *
	 * @param downloaded the downloaded of this asset entry user status
	 */
	@Override
	public void setDownloaded(boolean downloaded) {
		model.setDownloaded(downloaded);
	}

	/**
	 * Sets the entry ID of this asset entry user status.
	 *
	 * @param entryId the entry ID of this asset entry user status
	 */
	@Override
	public void setEntryId(long entryId) {
		model.setEntryId(entryId);
	}

	/**
	 * Sets the group ID of this asset entry user status.
	 *
	 * @param groupId the group ID of this asset entry user status
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the modified date of this asset entry user status.
	 *
	 * @param modifiedDate the modified date of this asset entry user status
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the primary key of this asset entry user status.
	 *
	 * @param primaryKey the primary key of this asset entry user status
	 */
	@Override
	public void setPrimaryKey(
		com.liferay.asset.ext.service.persistence.AssetEntryUserStatusPK
			primaryKey) {

		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets whether this asset entry user status is read.
	 *
	 * @param read the read of this asset entry user status
	 */
	@Override
	public void setRead(boolean read) {
		model.setRead(read);
	}

	/**
	 * Sets the read date of this asset entry user status.
	 *
	 * @param readDate the read date of this asset entry user status
	 */
	@Override
	public void setReadDate(Date readDate) {
		model.setReadDate(readDate);
	}

	/**
	 * Sets the user ID of this asset entry user status.
	 *
	 * @param userId the user ID of this asset entry user status
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this asset entry user status.
	 *
	 * @param userName the user name of this asset entry user status
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this asset entry user status.
	 *
	 * @param userUuid the user uuid of this asset entry user status
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this asset entry user status.
	 *
	 * @param uuid the uuid of this asset entry user status
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	@Override
	public StagedModelType getStagedModelType() {
		return model.getStagedModelType();
	}

	@Override
	protected AssetEntryUserStatusWrapper wrap(
		AssetEntryUserStatus assetEntryUserStatus) {

		return new AssetEntryUserStatusWrapper(assetEntryUserStatus);
	}

}