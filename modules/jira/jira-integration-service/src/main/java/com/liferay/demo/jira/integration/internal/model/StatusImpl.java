package com.liferay.demo.jira.integration.internal.model;

import com.liferay.demo.jira.integration.model.Status;

public class StatusImpl implements Status {
	@Override
	public String getStatusId() {
		return _statusId;
	}

	@Override
	public void setStatusId(String statusId) {
		_statusId = statusId;
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public void setName(String name) {
		_name = name;
	}

	private String _name;
	private String _statusId;
}
