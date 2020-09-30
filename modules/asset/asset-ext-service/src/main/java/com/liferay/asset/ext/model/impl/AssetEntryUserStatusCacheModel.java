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

package com.liferay.asset.ext.model.impl;

import com.liferay.asset.ext.model.AssetEntryUserStatus;
import com.liferay.asset.ext.service.persistence.AssetEntryUserStatusPK;
import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing AssetEntryUserStatus in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AssetEntryUserStatusCacheModel
	implements CacheModel<AssetEntryUserStatus>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof AssetEntryUserStatusCacheModel)) {
			return false;
		}

		AssetEntryUserStatusCacheModel assetEntryUserStatusCacheModel =
			(AssetEntryUserStatusCacheModel)object;

		if (assetEntryUserStatusPK.equals(
				assetEntryUserStatusCacheModel.assetEntryUserStatusPK)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, assetEntryUserStatusPK);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", assetEntryUserStatusId=");
		sb.append(assetEntryUserStatusId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", entryId=");
		sb.append(entryId);
		sb.append(", acknowledged=");
		sb.append(acknowledged);
		sb.append(", acknowledgementDate=");
		sb.append(acknowledgementDate);
		sb.append(", downloaded=");
		sb.append(downloaded);
		sb.append(", downloadDate=");
		sb.append(downloadDate);
		sb.append(", read=");
		sb.append(read);
		sb.append(", readDate=");
		sb.append(readDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public AssetEntryUserStatus toEntityModel() {
		AssetEntryUserStatusImpl assetEntryUserStatusImpl =
			new AssetEntryUserStatusImpl();

		if (uuid == null) {
			assetEntryUserStatusImpl.setUuid("");
		}
		else {
			assetEntryUserStatusImpl.setUuid(uuid);
		}

		assetEntryUserStatusImpl.setAssetEntryUserStatusId(
			assetEntryUserStatusId);
		assetEntryUserStatusImpl.setGroupId(groupId);
		assetEntryUserStatusImpl.setCompanyId(companyId);
		assetEntryUserStatusImpl.setUserId(userId);

		if (userName == null) {
			assetEntryUserStatusImpl.setUserName("");
		}
		else {
			assetEntryUserStatusImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			assetEntryUserStatusImpl.setCreateDate(null);
		}
		else {
			assetEntryUserStatusImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			assetEntryUserStatusImpl.setModifiedDate(null);
		}
		else {
			assetEntryUserStatusImpl.setModifiedDate(new Date(modifiedDate));
		}

		assetEntryUserStatusImpl.setEntryId(entryId);
		assetEntryUserStatusImpl.setAcknowledged(acknowledged);

		if (acknowledgementDate == Long.MIN_VALUE) {
			assetEntryUserStatusImpl.setAcknowledgementDate(null);
		}
		else {
			assetEntryUserStatusImpl.setAcknowledgementDate(
				new Date(acknowledgementDate));
		}

		assetEntryUserStatusImpl.setDownloaded(downloaded);

		if (downloadDate == Long.MIN_VALUE) {
			assetEntryUserStatusImpl.setDownloadDate(null);
		}
		else {
			assetEntryUserStatusImpl.setDownloadDate(new Date(downloadDate));
		}

		assetEntryUserStatusImpl.setRead(read);

		if (readDate == Long.MIN_VALUE) {
			assetEntryUserStatusImpl.setReadDate(null);
		}
		else {
			assetEntryUserStatusImpl.setReadDate(new Date(readDate));
		}

		assetEntryUserStatusImpl.resetOriginalValues();

		return assetEntryUserStatusImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		assetEntryUserStatusId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		entryId = objectInput.readLong();

		acknowledged = objectInput.readBoolean();
		acknowledgementDate = objectInput.readLong();

		downloaded = objectInput.readBoolean();
		downloadDate = objectInput.readLong();

		read = objectInput.readBoolean();
		readDate = objectInput.readLong();

		assetEntryUserStatusPK = new AssetEntryUserStatusPK(
			assetEntryUserStatusId, entryId);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(assetEntryUserStatusId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(entryId);

		objectOutput.writeBoolean(acknowledged);
		objectOutput.writeLong(acknowledgementDate);

		objectOutput.writeBoolean(downloaded);
		objectOutput.writeLong(downloadDate);

		objectOutput.writeBoolean(read);
		objectOutput.writeLong(readDate);
	}

	public String uuid;
	public long assetEntryUserStatusId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long entryId;
	public boolean acknowledged;
	public long acknowledgementDate;
	public boolean downloaded;
	public long downloadDate;
	public boolean read;
	public long readDate;
	public transient AssetEntryUserStatusPK assetEntryUserStatusPK;

}