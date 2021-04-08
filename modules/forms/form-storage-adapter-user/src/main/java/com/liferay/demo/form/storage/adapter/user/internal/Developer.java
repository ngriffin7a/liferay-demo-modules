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

package com.liferay.demo.form.storage.adapter.user.internal;

/**
 * @author Neil Griffin
 */
public class Developer {

	public String getAddressLine1() {
		return _addressLine1;
	}

	public String getAddressLine2() {
		return _addressLine2;
	}

	public String getCity() {
		return _city;
	}

	public String getCompanyName() {
		return _companyName;
	}

	public long getCountryId() {
		return _countryId;
	}

	public String getEmailAddress() {
		return _emailAddress;
	}

	public String getFirstName() {
		return _firstName;
	}

	public String getJobTitle() {
		return _jobTitle;
	}

	public String getLastName() {
		return _lastName;
	}

	public String getMobilePhone() {
		return _mobilePhone;
	}

	public String getPostalCode() {
		return _postalCode;
	}

	public long getRegionId() {
		return _regionId;
	}

	public String getWebsiteURL() {
		return _websiteURL;
	}

	public void setAddressLine1(String addressLine1) {
		_addressLine1 = addressLine1;
	}

	public void setAddressLine2(String addressLine2) {
		_addressLine2 = addressLine2;
	}

	public void setCity(String city) {
		_city = city;
	}

	public void setCompanyName(String companyName) {
		_companyName = companyName;
	}

	public void setCountryId(long countryId) {
		_countryId = countryId;
	}

	public void setEmailAddress(String emailAddress) {
		_emailAddress = emailAddress;
	}

	public void setFirstName(String firstName) {
		_firstName = firstName;
	}

	public void setJobTitle(String jobTitle) {
		_jobTitle = jobTitle;
	}

	public void setLastName(String lastName) {
		_lastName = lastName;
	}

	public void setMobilePhone(String mobilePhone) {
		_mobilePhone = mobilePhone;
	}

	public void setPostalCode(String postalCode) {
		_postalCode = postalCode;
	}

	public void setRegionId(long regionId) {
		_regionId = regionId;
	}

	public void setWebsiteURL(String websiteURL) {
		_websiteURL = websiteURL;
	}

	private String _addressLine1;
	private String _addressLine2;
	private String _city;
	private String _companyName;
	private long _countryId;
	private String _emailAddress;
	private String _firstName;
	private String _jobTitle;
	private String _lastName;
	private String _mobilePhone;
	private String _postalCode;
	private long _regionId;
	private String _websiteURL;

}