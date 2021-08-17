package com.liferay.demo.notification.sender.web.internal.portlet;

import java.util.Locale;

public class Recipient {

	public Recipient(String emailAddress) {
		System.err.println("!@#$ creating recipient#1 emailAddress: " + emailAddress);
		_emailAddress = emailAddress;
	}

	public Recipient(long userId, String firstName, String lastName, String fullName, String emailAddress, String screenName, Locale locale) {
		System.err.println("!@#$ creating recipient#2 emailAddress: " + emailAddress);
		_userId = userId;
		_firstName = firstName;
		_lastName = lastName;
		_fullName = fullName;
		_emailAddress = emailAddress;
		_screenName = screenName;
		_locale = locale;
	}

	public String getFirstName() {
		return _firstName;
	}

	public String getFullName() {
		return _fullName;
	}

	public String getLastName() {
		return _lastName;
	}

	public String getEmailAddress() {
		return _emailAddress;
	}

	public Locale getLocale() {
		return _locale;
	}

	public String getScreenName() {
		return _screenName;
	}

	public long getUserId() {
		return _userId;
	}

	@Override
	public String toString() {
		return "Recipient {" +
			   "_firstName='" + _firstName + '\'' +
			   ", _fullName='" + _fullName + '\'' +
			   ", _lastName='" + _lastName + '\'' +
			   ", _locale=" + _locale +
			   ", _emailAddress='" + _emailAddress + '\'' +
			   ", _screenName='" + _screenName + '\'' +
			   ", _userId=" + _userId +
			   '}';
	}

	private String _firstName;
	private String _fullName;
	private String _lastName;
	private Locale _locale;
	private String _emailAddress;
	private String _screenName;
	private long _userId;
}
