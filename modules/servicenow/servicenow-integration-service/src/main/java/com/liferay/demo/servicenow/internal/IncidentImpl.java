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

package com.liferay.demo.servicenow.internal;

import com.liferay.demo.servicenow.Incident;

import java.util.Date;

/**
 * @author Neil Griffin
 */
public class IncidentImpl implements Incident {

	public IncidentImpl() {
	}

	public IncidentImpl(
		String incidentId, String category, Date dueDate, String href,
		String number, String shortDescription, String subcategory) {

		_incidentId = incidentId;
		_category = category;
		_dueDate = dueDate;
		_href = href;
		_number = number;
		_shortDescription = shortDescription;
		_subcategory = subcategory;
	}

	@Override
	public String getCategory() {
		return _category;
	}

	@Override
	public Date getDueDate() {
		return _dueDate;
	}

	public String getHref() {
		return _href;
	}

	@Override
	public String getIncidentId() {
		return _incidentId;
	}

	@Override
	public String getNumber() {
		return _number;
	}

	@Override
	public String getShortDescription() {
		return _shortDescription;
	}

	@Override
	public String getSubcategory() {
		return _subcategory;
	}

	@Override
	public void setCategory(String category) {
		_category = category;
	}

	@Override
	public void setDueDate(Date dueDate) {
		_dueDate = dueDate;
	}

	@Override
	public void setHref(String href) {
		_href = href;
	}

	@Override
	public void setIncidentId(String incidentId) {
		_incidentId = incidentId;
	}

	@Override
	public void setNumber(String number) {
		_number = number;
	}

	@Override
	public void setShortDescription(String shortDescription) {
		_shortDescription = shortDescription;
	}

	@Override
	public void setSubcategory(String subcategory) {
		_subcategory = subcategory;
	}

	private String _category;
	private Date _dueDate;
	private String _href;
	private String _incidentId;
	private String _number;
	private String _shortDescription;
	private String _subcategory;

}