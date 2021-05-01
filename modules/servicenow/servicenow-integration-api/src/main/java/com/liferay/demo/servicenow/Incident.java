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

package com.liferay.demo.servicenow;

import java.util.Date;

/**
 * @author Neil Griffin
 */
public interface Incident {

	public String getCategory();

	public Date getDueDate();

	public String getHref();

	public String getIncidentId();

	public String getNumber();

	public String getShortDescription();

	public String getSubcategory();

	public void setCategory(String category);

	public void setDueDate(Date dueDate);

	public void setHref(String href);

	public void setIncidentId(String incidentId);

	public void setNumber(String number);

	public void setShortDescription(String shortDescription);

	public void setSubcategory(String subcategory);

}