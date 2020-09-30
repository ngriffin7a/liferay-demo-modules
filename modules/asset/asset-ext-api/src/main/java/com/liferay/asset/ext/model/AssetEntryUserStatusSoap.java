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

import com.liferay.asset.ext.service.persistence.AssetEntryUserStatusPK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.asset.ext.service.http.AssetEntryUserStatusServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AssetEntryUserStatusSoap implements Serializable {

	public static AssetEntryUserStatusSoap toSoapModel(
		AssetEntryUserStatus model) {

		AssetEntryUserStatusSoap soapModel = new AssetEntryUserStatusSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setAssetEntryUserStatusId(model.getAssetEntryUserStatusId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setEntryId(model.getEntryId());
		soapModel.setAcknowledged(model.isAcknowledged());
		soapModel.setAcknowledgementDate(model.getAcknowledgementDate());
		soapModel.setDownloaded(model.isDownloaded());
		soapModel.setDownloadDate(model.getDownloadDate());
		soapModel.setRead(model.isRead());
		soapModel.setReadDate(model.getReadDate());

		return soapModel;
	}

	public static AssetEntryUserStatusSoap[] toSoapModels(
		AssetEntryUserStatus[] models) {

		AssetEntryUserStatusSoap[] soapModels =
			new AssetEntryUserStatusSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AssetEntryUserStatusSoap[][] toSoapModels(
		AssetEntryUserStatus[][] models) {

		AssetEntryUserStatusSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new AssetEntryUserStatusSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AssetEntryUserStatusSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AssetEntryUserStatusSoap[] toSoapModels(
		List<AssetEntryUserStatus> models) {

		List<AssetEntryUserStatusSoap> soapModels =
			new ArrayList<AssetEntryUserStatusSoap>(models.size());

		for (AssetEntryUserStatus model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new AssetEntryUserStatusSoap[soapModels.size()]);
	}

	public AssetEntryUserStatusSoap() {
	}

	public AssetEntryUserStatusPK getPrimaryKey() {
		return new AssetEntryUserStatusPK(_assetEntryUserStatusId, _entryId);
	}

	public void setPrimaryKey(AssetEntryUserStatusPK pk) {
		setAssetEntryUserStatusId(pk.assetEntryUserStatusId);
		setEntryId(pk.entryId);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getAssetEntryUserStatusId() {
		return _assetEntryUserStatusId;
	}

	public void setAssetEntryUserStatusId(long assetEntryUserStatusId) {
		_assetEntryUserStatusId = assetEntryUserStatusId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getEntryId() {
		return _entryId;
	}

	public void setEntryId(long entryId) {
		_entryId = entryId;
	}

	public boolean getAcknowledged() {
		return _acknowledged;
	}

	public boolean isAcknowledged() {
		return _acknowledged;
	}

	public void setAcknowledged(boolean acknowledged) {
		_acknowledged = acknowledged;
	}

	public Date getAcknowledgementDate() {
		return _acknowledgementDate;
	}

	public void setAcknowledgementDate(Date acknowledgementDate) {
		_acknowledgementDate = acknowledgementDate;
	}

	public boolean getDownloaded() {
		return _downloaded;
	}

	public boolean isDownloaded() {
		return _downloaded;
	}

	public void setDownloaded(boolean downloaded) {
		_downloaded = downloaded;
	}

	public Date getDownloadDate() {
		return _downloadDate;
	}

	public void setDownloadDate(Date downloadDate) {
		_downloadDate = downloadDate;
	}

	public boolean getRead() {
		return _read;
	}

	public boolean isRead() {
		return _read;
	}

	public void setRead(boolean read) {
		_read = read;
	}

	public Date getReadDate() {
		return _readDate;
	}

	public void setReadDate(Date readDate) {
		_readDate = readDate;
	}

	private String _uuid;
	private long _assetEntryUserStatusId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _entryId;
	private boolean _acknowledged;
	private Date _acknowledgementDate;
	private boolean _downloaded;
	private Date _downloadDate;
	private boolean _read;
	private Date _readDate;

}