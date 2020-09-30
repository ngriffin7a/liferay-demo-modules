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

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AssetEntryUserStatusPK
	implements Comparable<AssetEntryUserStatusPK>, Serializable {

	public long assetEntryUserStatusId;
	public long entryId;

	public AssetEntryUserStatusPK() {
	}

	public AssetEntryUserStatusPK(long assetEntryUserStatusId, long entryId) {
		this.assetEntryUserStatusId = assetEntryUserStatusId;
		this.entryId = entryId;
	}

	public long getAssetEntryUserStatusId() {
		return assetEntryUserStatusId;
	}

	public void setAssetEntryUserStatusId(long assetEntryUserStatusId) {
		this.assetEntryUserStatusId = assetEntryUserStatusId;
	}

	public long getEntryId() {
		return entryId;
	}

	public void setEntryId(long entryId) {
		this.entryId = entryId;
	}

	@Override
	public int compareTo(AssetEntryUserStatusPK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

		if (assetEntryUserStatusId < pk.assetEntryUserStatusId) {
			value = -1;
		}
		else if (assetEntryUserStatusId > pk.assetEntryUserStatusId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (entryId < pk.entryId) {
			value = -1;
		}
		else if (entryId > pk.entryId) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof AssetEntryUserStatusPK)) {
			return false;
		}

		AssetEntryUserStatusPK pk = (AssetEntryUserStatusPK)object;

		if ((assetEntryUserStatusId == pk.assetEntryUserStatusId) &&
			(entryId == pk.entryId)) {

			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int hashCode = 0;

		hashCode = HashUtil.hash(hashCode, assetEntryUserStatusId);
		hashCode = HashUtil.hash(hashCode, entryId);

		return hashCode;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(6);

		sb.append("{");

		sb.append("assetEntryUserStatusId=");

		sb.append(assetEntryUserStatusId);
		sb.append(", entryId=");

		sb.append(entryId);

		sb.append("}");

		return sb.toString();
	}

}