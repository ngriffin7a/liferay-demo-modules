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

package com.liferay.demo.jira.integration.web.internal.view;

import com.liferay.demo.jira.integration.model.Status;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.CreationMenu;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemList;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.PortletURLUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.view.state.ManagementToolbarViewState;
import com.liferay.view.state.ManagementToolbarViewStateWrapper;

import java.util.List;
import java.util.Objects;

import javax.portlet.MutableRenderParameters;
import javax.portlet.PortletURL;
import javax.portlet.RenderURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Neil Griffin
 */
public class IssueManagementToolbarViewState
	extends ManagementToolbarViewStateWrapper {

	public IssueManagementToolbarViewState(
		ManagementToolbarViewState managementToolbarViewState,
		HttpServletRequest httpServletRequest,
		LiferayPortletResponse liferayPortletResponse, String status,
		String filterNavigationMessage, String orderByMessage,
		String keyMessage, String summaryMessage, List<Status> statuses) {

		super(managementToolbarViewState);

		_httpServletRequest = httpServletRequest;
		_liferayPortletResponse = liferayPortletResponse;
		_status = status;
		_filterNavigationMessage = filterNavigationMessage;

		_addEntryURL = managementToolbarViewState.getAddEntryURL();

		MutableRenderParameters renderParameters =
			_addEntryURL.getRenderParameters();

		renderParameters.setValue("status", "all");
		renderParameters.setValue("mvcRenderCommandName", "issue-add");

		_orderByMessage = orderByMessage;
		_keyMessage = keyMessage;
		_summaryMessage = summaryMessage;
		_statuses = statuses;
	}

	@Override
	public CreationMenu getCreationMenu() {
		if (_creationMenu == null) {
			_creationMenu = new CreationMenu();

			_creationMenu.addDropdownItem(
				dropdownItem -> {
					dropdownItem.setHref(_addEntryURL);

					ManagementToolbarViewState managementToolbarViewState =
						getWrapped();

					dropdownItem.setLabel(
						managementToolbarViewState.getAddEntryMessage());
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
	public RenderURL getSearchActionURL() {
		return getSearchURL();
	}

	@Override
	public RenderURL getSearchURL() {
		RenderURL searchURL = super.getSearchURL();

		MutableRenderParameters renderParameters =
			searchURL.getRenderParameters();

		renderParameters.setValue("status", _status);

		return searchURL;
	}

	@Override
	public RenderURL getSortingURL() {
		return getSortingURLReverse();
	}

	@Override
	public RenderURL getSortingURLCurrent() {
		RenderURL sortingURLCurrent = super.getSortingURLCurrent();

		MutableRenderParameters renderParameters =
			sortingURLCurrent.getRenderParameters();

		renderParameters.setValue("status", _status);

		return sortingURLCurrent;
	}

	@Override
	public RenderURL getSortingURLReverse() {
		RenderURL sortingURLReverse = super.getSortingURLReverse();

		MutableRenderParameters renderParameters =
			sortingURLReverse.getRenderParameters();

		renderParameters.setValue("status", _status);

		return sortingURLReverse;
	}

	private List<DropdownItem> _getFilterNavigationDropdownItems() {
		return new DropdownItemList() {
			{
				PortletURL portletURL = _getRenderURL(getSearchURL());

				for (Status status : _statuses) {
					add(
						dropdownItem -> {
							dropdownItem.setActive(
								status.getName(
								).equals(
									_getNavigation()
								));

							PortletURL statusURL = PortletURLUtil.clone(
								portletURL, _liferayPortletResponse);

							dropdownItem.setHref(
								statusURL, "status", status.getStatusId());

							dropdownItem.setLabel(
								LanguageUtil.get(
									_httpServletRequest, status.getName()));
						});
				}
			}
		};
	}

	private String _getNavigation() {
		return ParamUtil.getString(_httpServletRequest, "status", "issue");
	}

	private List<DropdownItem> _getOrderByDropdownItems() {
		DropdownItemList orderByDropDownItems = new DropdownItemList();

		orderByDropDownItems.add(
			dropdownItem -> {
				dropdownItem.setActive(
					Objects.equals(getSortingOrder(), "key"));
				dropdownItem.setHref(
					getSortingURLCurrent(), "orderByCol", "key");
				dropdownItem.setLabel(_keyMessage);
			});

		orderByDropDownItems.add(
			dropdownItem -> {
				dropdownItem.setActive(
					Objects.equals(getSortingOrder(), "summary"));
				dropdownItem.setHref(
					getSortingURLCurrent(), "orderByCol", "summary");
				dropdownItem.setLabel(_summaryMessage);
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
	private CreationMenu _creationMenu;
	private DropdownItemList _filterDropdownItems;
	private String _filterNavigationMessage;
	private HttpServletRequest _httpServletRequest;
	private String _keyMessage;
	private LiferayPortletResponse _liferayPortletResponse;
	private String _orderByMessage;
	private String _status;
	private List<Status> _statuses;
	private String _summaryMessage;

}