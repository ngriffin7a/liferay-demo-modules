package com.liferay.demo.form.storage.adapter.internal;

/**
 * @author Neil Griffin
 */
public class Demo {

	public String getFirstName() {
		return _firstName;
	}

	public void setFirstName(String firstName) {
		_firstName = firstName;
	}

	public String getLastName() {
		return _lastName;
	}

	public void setLastName(String lastName) {
		_lastName = lastName;
	}

	private String _firstName;
	private String _lastName;
}
