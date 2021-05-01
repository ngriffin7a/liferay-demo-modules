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

package com.liferay.demo.servicenow.web.internal.view;

import com.liferay.frontend.taglib.clay.servlet.taglib.util.CreationMenu;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemList;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portlet.view.state.ClayToolbarViewState;
import com.liferay.portlet.view.state.ClayToolbarViewStateWrapper;

import java.util.List;
import java.util.Objects;

import javax.portlet.MutableRenderParameters;
import javax.portlet.PortletURL;
import javax.portlet.RenderURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Neil Griffin
 */
public class IncidentClayToolbarViewState extends ClayToolbarViewStateWrapper {

	public IncidentClayToolbarViewState(
		ClayToolbarViewState clayToolbarViewState,
		HttpServletRequest httpServletRequest,
		LiferayPortletResponse liferayPortletResponse, String category,
		String filterNavigationMessage, String orderByMessage,
		String numberMessage, String shortDescriptionMessage) {

		super(clayToolbarViewState);

		_httpServletRequest = httpServletRequest;
		_liferayPortletResponse = liferayPortletResponse;
		_category = category;
		_filterNavigationMessage = filterNavigationMessage;

		_addEntryURL = clayToolbarViewState.getAddEntryURL();

		MutableRenderParameters renderParameters =
			_addEntryURL.getRenderParameters();

		renderParameters.setValue("category", "inquiry");
		renderParameters.setValue("mvcRenderCommandName", "incident-add");

		_orderByMessage = orderByMessage;
		_numberMessage = numberMessage;
		_shortDescriptionMessage = shortDescriptionMessage;
	}

	@Override
	public CreationMenu getCreationMenu() {
		if (_creationMenu == null) {
			_creationMenu = new CreationMenu();

			_creationMenu.addDropdownItem(
				dropdownItem -> {
					dropdownItem.setHref(_addEntryURL);

					ClayToolbarViewState clayToolbarViewState = getWrapped();

					dropdownItem.setLabel(
						clayToolbarViewState.getAddEntryMessage());
				});
		}

		return _creationMenu;
	}

	@Override
	public List<DropdownItem> getFilterDropdownItems() {
		if (_filterDropdownItems == null) {
			_filterDropdownItems = new DropdownItemList();

			_filterDropdownItems.addGroup(
				dropdownGroupItem -> {
					dropdownGroupItem.setDropdownItems(
						_getFilterNavigationDropdownItems());
					dropdownGroupItem.setLabel(_filterNavigationMessage);
				});

			_filterDropdownItems.addGroup(
				dropdownGroupItem -> {
					dropdownGroupItem.setDropdownItems(
						_getOrderByDropdownItems());
					dropdownGroupItem.setLabel(_orderByMessage);
				});
		}

		return _filterDropdownItems;
	}

	@Override
	public String getSearchActionURL() {
		RenderURL searchURL = getSearchURL();

		return searchURL.toString();
	}

	@Override
	public RenderURL getSearchURL() {
		RenderURL searchURL = super.getSearchURL();

		MutableRenderParameters renderParameters =
			searchURL.getRenderParameters();

		renderParameters.setValue("category", _category);

		return searchURL;
	}

	@Override
	public String getSortingURL() {
		RenderURL sortingURLReverse = getSortingURLReverse();

		return sortingURLReverse.toString();
	}

	@Override
	public RenderURL getSortingURLCurrent() {
		RenderURL sortingURLCurrent = super.getSortingURLCurrent();

		MutableRenderParameters renderParameters =
			sortingURLCurrent.getRenderParameters();

		renderParameters.setValue("category", _category);

		return sortingURLCurrent;
	}

	@Override
	public RenderURL getSortingURLReverse() {
		RenderURL sortingURLReverse = super.getSortingURLReverse();

		MutableRenderParameters renderParameters =
			sortingURLReverse.getRenderParameters();

		renderParameters.setValue("category", _category);

		return sortingURLReverse;
	}

	private List<DropdownItem> _getFilterNavigationDropdownItems() {
		return new DropdownItemList() {
			{
				String[] categoryKeys = {
					"inquiry", "software", "hardware", "network", "database"
				};

				PortletURL portletURL = _getRenderURL(getSearchURL());

				for (String categoryKey : categoryKeys) {
					add(
						dropdownItem -> {
							dropdownItem.setActive(
								categoryKey.equals(_getNavigation()));

							PortletURL categoryURL = PortletURLUtil.clone(
								portletURL, _liferayPortletResponse);

							dropdownItem.setHref(
								categoryURL, "category", categoryKey);

							dropdownItem.setLabel(
								LanguageUtil.get(
									_httpServletRequest, categoryKey));
						});
				}
			}
		};
	}

	private String _getNavigation() {
		return ParamUtil.getString(_httpServletRequest, "category", "incident");
	}

	private List<DropdownItem> _getOrderByDropdownItems() {
		DropdownItemList orderByDropDownItems = new DropdownItemList();

		orderByDropDownItems.add(
			dropdownItem -> {
				dropdownItem.setActive(
					Objects.equals(getSortingOrder(), "number"));
				dropdownItem.setHref(
					getSortingURLCurrent(), "orderByCol", "number");
				dropdownItem.setLabel(_numberMessage);
			});

		orderByDropDownItems.add(
			dropdownItem -> {
				dropdownItem.setActive(
					Objects.equals(getSortingOrder(), "short_description"));
				dropdownItem.setHref(
					getSortingURLCurrent(), "orderByCol", "short_description");
				dropdownItem.setLabel(_shortDescriptionMessage);
			});

		return orderByDropDownItems;
	}

	private RenderURL _getRenderURL(RenderURL renderURL) {
		MutableRenderParameters renderParameters =
			renderURL.getRenderParameters();

		// TODO: This may be bookmarks specific
		// renderParameters.setValue("categoryId", StringPool.BLANK);

		int deltaEntry = ParamUtil.getInteger(
			_httpServletRequest, "deltaEntry");

		if (deltaEntry > 0) {
			renderParameters.setValue("deltaEntry", String.valueOf(deltaEntry));
		}

		return renderURL;
	}

	private RenderURL _addEntryURL;
	private String _category;
	private CreationMenu _creationMenu;
	private DropdownItemList _filterDropdownItems;
	private String _filterNavigationMessage;
	private HttpServletRequest _httpServletRequest;
	private LiferayPortletResponse _liferayPortletResponse;
	private String _numberMessage;
	private String _orderByMessage;
	private String _shortDescriptionMessage;

}