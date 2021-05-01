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
import com.liferay.demo.servicenow.ServiceNowTokenService;
import com.liferay.demo.servicenow.web.PortletKeys;
import com.liferay.demo.servicenow.web.internal.el.CurrentUser;
import com.liferay.demo.servicenow.web.internal.portlet.ServiceNowTokenUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.view.state.SearchContainerViewState;
import com.liferay.portlet.view.state.SearchContainerViewStateFactory;

import java.io.IOException;

import java.util.Optional;

import javax.portlet.PortletException;
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
		"javax.portlet.name=" + PortletKeys.INCIDENTS,
		"mvc.command.name=incident-add", "mvc.command.name=incident-edit",
		"mvc.command.name=incident-view"
	},
	service = MVCRenderCommand.class
)
public class IncidentDetailRenderController implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		renderRequest.setAttribute("currentUser", new CurrentUser());

		SearchContainerViewState incidentSearchContainerViewState =
			_searchContainerViewStateFactory.create(
				"list", "number", "asc", renderRequest,
				new String[] {"number", "short_description"});

		renderRequest.setAttribute(
			"incidentSearchContainerViewState",
			incidentSearchContainerViewState);

		Incident incident = _serviceNowIncidentService.newIncident();

		RenderParameters renderParameters = renderRequest.getRenderParameters();

		String incidentId = renderParameters.getValue("incidentId");

		if (Validator.isNotNull(incidentId)) {
			try {
				Optional<Incident> optionalIncident =
					_serviceNowIncidentService.getIncident(
						ServiceNowTokenUtil.
							getServiceNowTokenFromPortletSession(
								_serviceNowTokenService,
								renderRequest.getPortletSession()),
						incidentId);

				if (optionalIncident.isPresent()) {
					incident = optionalIncident.get();
				}
				else {
					throw new PortletException(
						"No incident found with id=" + incidentId);
				}
			}
			catch (IOException ioException) {
				throw new PortletException(ioException);
			}
		}

		renderRequest.setAttribute("incident", incident);

		return "/views/incident.jsp";
	}

	@Reference
	private SearchContainerViewStateFactory _searchContainerViewStateFactory;

	@Reference
	private ServiceNowIncidentService _serviceNowIncidentService;

	@Reference
	private ServiceNowTokenService _serviceNowTokenService;

}