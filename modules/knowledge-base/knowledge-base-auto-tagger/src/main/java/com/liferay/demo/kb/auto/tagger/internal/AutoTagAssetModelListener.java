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

package com.liferay.demo.kb.auto.tagger.internal;

import com.liferay.asset.auto.tagger.internal.constants.AssetAutoTaggerDestinationNames;
import com.liferay.asset.auto.tagger.model.AssetAutoTaggerEntry;
import com.liferay.asset.auto.tagger.service.AssetAutoTaggerEntryLocalService;
import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.knowledge.base.model.KBArticle;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBus;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.transaction.TransactionCommitCallbackUtil;
import com.liferay.portal.kernel.util.ListUtil;

import java.util.concurrent.Callable;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * This class is largely copied from {@link com.liferay.asset.auto.tagger.internal.messaging.AssetAutoTaggerMessageListener}.
 *
 * @author Alejandro Tardín
 * @author Neil Griffin
 */
@Component(immediate = true, service = ModelListener.class)
public class AutoTagAssetModelListener extends BaseModelListener<AssetEntry> {

	@Override
	public void onAfterRemoveAssociation(
			Object classPK, String associationClassName,
			Object associationClassPK)
		throws ModelListenerException {

		if (associationClassName.equals(AssetTag.class.getName())) {
			AssetAutoTaggerEntry assetAutoTaggerEntry =
				_assetAutoTaggerEntryLocalService.fetchAssetAutoTaggerEntry(
					(Long)classPK, (Long)associationClassPK);

			if (assetAutoTaggerEntry != null) {
				_assetAutoTaggerEntryLocalService.deleteAssetAutoTaggerEntry(
					assetAutoTaggerEntry);
			}
		}
	}

	@Override
	public void onBeforeUpdate(AssetEntry assetEntry)
		throws ModelListenerException {

		AssetEntry assetEntryFromDatabase =
			_assetEntryLocalService.fetchAssetEntry(assetEntry.getEntryId());

		if (assetEntryFromDatabase.getPublishDate() == null) {
			TransactionCommitCallbackUtil.registerCallback(
				(Callable<Void>)() -> {
					String assetEntryClassName = assetEntry.getClassName();

					if (assetEntryClassName.equals(KBArticle.class.getName()) &&
						ListUtil.isEmpty(assetEntry.getTags())) {

						Message message = new Message();

						message.setPayload(assetEntry);

						_messageBus.sendMessage(
							AssetAutoTaggerDestinationNames.ASSET_AUTO_TAGGER,
							message);
					}

					return null;
				});
		}
	}

	@Reference
	private AssetAutoTaggerEntryLocalService _assetAutoTaggerEntryLocalService;

	@Reference
	private AssetEntryLocalService _assetEntryLocalService;

	@Reference
	private MessageBus _messageBus;

}