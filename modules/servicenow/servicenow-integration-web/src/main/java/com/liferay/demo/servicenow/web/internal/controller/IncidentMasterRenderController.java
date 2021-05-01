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

package com.liferay.demo.servicenow.web.internal.controller;

import com.liferay.demo.servicenow.Incident;
import com.liferay.demo.servicenow.ServiceNowIncidentService;
import com.liferay.demo.servicenow.ServiceNowToken;
import com.liferay.demo.servicenow.ServiceNowTokenService;
import com.liferay.demo.servicenow.web.PortletKeys;
import com.liferay.demo.servicenow.web.internal.el.CurrentUser;
import com.liferay.demo.servicenow.web.internal.portlet.ServiceNowTokenUtil;
import com.liferay.demo.servicenow.web.internal.view.IncidentClayToolbarViewState;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portlet.view.state.ClayToolbarViewState;
import com.liferay.portlet.view.state.ClayToolbarViewStateFactory;
import com.liferay.portlet.view.state.SearchContainerViewState;
import com.liferay.portlet.view.state.SearchContainerViewStateFactory;

import java.io.IOException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;

import javax.portlet.PortletConfig;
import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.RenderParameters;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Neil Griffin
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + PortletKeys.INCIDENTS, "mvc.command.name=/",
		"mvc.command.name=incidents"
	},
	service = MVCRenderCommand.class
)
public class IncidentMasterRenderController implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		RenderParameters renderParameters = renderRequest.getRenderParameters();

		String category = GetterUtil.get(
			renderParameters.getValue("category"), "inquiry");

		PortletSession portletSession = renderRequest.getPortletSession();

		Map<String, List<Incident>> serviceNowIncidentMap =
			(Map<String, List<Incident>>)portletSession.getAttribute(
				"serviceNowIncidentMap");

		if (serviceNowIncidentMap == null) {
			serviceNowIncidentMap = new HashMap<>();
		}

		SearchContainerViewState incidentSearchContainerViewState =
			_searchContainerViewStateFactory.create(
				"list", "number", "asc", renderRequest,
				new String[] {"number", "short_description"});

		renderRequest.setAttribute(
			"incidentSearchContainerViewState",
			incidentSearchContainerViewState);

		PortletConfig portletConfig = (PortletConfig)renderRequest.getAttribute(
			"javax.portlet.config");

		ResourceBundle resourceBundle = portletConfig.getResourceBundle(
			renderRequest.getLocale());

		// TODO: These are currently hard-coded but need to be addressed
		// as part of ClayToolBarViewState.getViewTypeItems()

		boolean showDisplayStyleCard = false;
		boolean showDisplayStyleList = true;
		boolean showDisplayStyleTable = true;

		// TODO: Permissions?

		boolean currentUserMayAddIncidentEntry = true;

		PortalPreferences portalPreferences =
			PortletPreferencesFactoryUtil.getPortalPreferences(renderRequest);

		ClayToolbarViewState clayToolbarViewState =
			_clayToolbarViewStateFactory.create(
				LanguageUtil.get(resourceBundle, "action.ADD_INCIDENT"),
				portalPreferences.getValue(
					PortletKeys.INCIDENTS, "display-style", "list"),
				"number", "asc", renderRequest, renderResponse,
				currentUserMayAddIncidentEntry, showDisplayStyleCard,
				showDisplayStyleList, showDisplayStyleTable);

		String mapKey =
			category + incidentSearchContainerViewState.getOrderByType() +
				incidentSearchContainerViewState.getOrderByCol();

		List<Incident> incidents = serviceNowIncidentMap.get(mapKey);

		if (incidents == null) {
			ServiceNowToken serviceNowToken = null;

			try {
				serviceNowToken =
					ServiceNowTokenUtil.getServiceNowTokenFromPortletSession(
						_serviceNowTokenService, portletSession);

				incidents = _serviceNowIncidentService.getIncidents(
					serviceNowToken, category,
					Objects.equals(
						incidentSearchContainerViewState.getOrderByType(),
						"asc"),
					incidentSearchContainerViewState.getOrderByCol());

				serviceNowIncidentMap.put(mapKey, incidents);
			}
			catch (IOException ioException) {
				throw new PortletException(ioException);
			}
		}

		portletSession.setAttribute(
			"serviceNowIncidentMap", serviceNowIncidentMap);

		renderRequest.setAttribute("currentUser", new CurrentUser());
		renderRequest.setAttribute("incidentCount", incidents.size());
		renderRequest.setAttribute("incidents", incidents);

		String filterNavigationMessage = LanguageUtil.get(
			resourceBundle, "filter-navigation");
		String orderByMessage = LanguageUtil.get(resourceBundle, "order-by");
		String numberMessage = LanguageUtil.get(resourceBundle, "number");
		String shortDescriptionMessage = LanguageUtil.get(
			resourceBundle, "short-description");

		renderRequest.setAttribute(
			"incidentClayToolbarViewState",
			new IncidentClayToolbarViewState(
				clayToolbarViewState,
				_portal.getHttpServletRequest(renderRequest),
				_portal.getLiferayPortletResponse(renderResponse), category,
				filterNavigationMessage, orderByMessage, numberMessage,
				shortDescriptionMessage));

		return "/views/incidents.jsp";
	}

	@Reference
	private ClayToolbarViewStateFactory _clayToolbarViewStateFactory;

	@Reference
	private Portal _portal;

	@Reference
	private SearchContainerViewStateFactory _searchContainerViewStateFactory;

	@Reference
	private ServiceNowIncidentService _serviceNowIncidentService;

	@Reference
	private ServiceNowTokenService _serviceNowTokenService;

}