package com.liferay.demo.kb.auto.tagger.internal;

import com.liferay.asset.auto.tagger.AssetAutoTagProvider;
import com.liferay.knowledge.base.model.KBArticle;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component(
	property = "model.class.name=com.liferay.knowledge.base.model.KBArticle",
	service = AssetAutoTagProvider.class
)
public class KBAssetAutoTagProvider implements AssetAutoTagProvider<KBArticle> {

	@Override
	public Collection<String> getTagNames(
		KBArticle kbArticle) {

		List<String> tagNames = new ArrayList<>();

		// This gets added for every KB article
		tagNames.add("Knowledge Base");

		return tagNames;
	}
}
