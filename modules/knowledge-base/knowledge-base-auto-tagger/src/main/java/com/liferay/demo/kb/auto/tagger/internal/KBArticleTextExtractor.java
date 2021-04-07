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

import com.liferay.asset.auto.tagger.text.extractor.TextExtractor;
import com.liferay.knowledge.base.model.KBArticle;
import com.liferay.petra.string.StringPool;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;

/**
 * @author Neil Griffin
 */
@Component(service = TextExtractor.class)
public class KBArticleTextExtractor implements TextExtractor<KBArticle> {

	@Override
	public String extract(KBArticle kbArticle, Locale locale) {
		String content = kbArticle.getContent();

		if (content == null) {
			return StringPool.BLANK;
		}

		content = content.replaceAll("&lt;", "<");
		content = content.replaceAll("&gt;", ">");
		content = content.replaceAll("&apos;", "'");
		content = content.replaceAll("&quot;", "\"");
		content = content.replaceAll("&amp;", "&");
		content = content.replaceAll("\n", "");
		content = content.replaceAll("\r", "");
		content = content.replaceAll("\\<.*?\\>", "");

		return content;
	}

	@Override
	public String getClassName() {
		return KBArticle.class.getName();
	}

}