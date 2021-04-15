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

package com.liferay.demo.document.library.web.override.internal;

import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;

import java.util.Map;

import javax.portlet.PortletConfig;
import javax.portlet.PortletResponse;
import javax.portlet.filter.PortletRequestWrapper;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Neil Griffin
 */
public class LiferayPortletRequestWrapper
	extends PortletRequestWrapper implements LiferayPortletRequest {

	public LiferayPortletRequestWrapper(
		LiferayPortletRequest liferayPortletRequest) {

		super(liferayPortletRequest);

		_liferayPortletRequest = liferayPortletRequest;
	}

	@Override
	public void cleanUp() {
		_liferayPortletRequest.cleanUp();
	}

	@Override
	public Map<String, String[]> clearRenderParameters() {
		return _liferayPortletRequest.clearRenderParameters();
	}

	@Override
	public void defineObjects(
		PortletConfig portletConfig, PortletResponse portletResponse) {

		_liferayPortletRequest.defineObjects(portletConfig, portletResponse);
	}

	@Override
	public HttpServletRequest getHttpServletRequest() {
		return _liferayPortletRequest.getHttpServletRequest();
	}

	@Override
	public String getLifecycle() {
		return _liferayPortletRequest.getLifecycle();
	}

	@Override
	public HttpServletRequest getOriginalHttpServletRequest() {
		return _liferayPortletRequest.getOriginalHttpServletRequest();
	}

	@Override
	public long getPlid() {
		return _liferayPortletRequest.getPlid();
	}

	@Override
	public Portlet getPortlet() {
		return _liferayPortletRequest.getPortlet();
	}

	@Override
	public String getPortletName() {
		return _liferayPortletRequest.getPortletName();
	}

	@Override
	public HttpServletRequest getPortletRequestDispatcherRequest() {
		return _liferayPortletRequest.getPortletRequestDispatcherRequest();
	}

	@Override
	public void invalidateSession() {
		_liferayPortletRequest.invalidateSession();
	}

	@Override
	public void setPortletRequestDispatcherRequest(
		HttpServletRequest httpServletRequest) {

		_liferayPortletRequest.setPortletRequestDispatcherRequest(
			httpServletRequest);
	}

	private LiferayPortletRequest _liferayPortletRequest;

}