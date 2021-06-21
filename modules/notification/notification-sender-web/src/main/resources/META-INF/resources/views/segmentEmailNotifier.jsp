<?xml version="1.0" encoding="UTF-8"?>
<jsp:root
	version="2.1"
	xmlns:aui="http://liferay.com/tld/aui"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:liferay-frontend="http://liferay.com/tld/frontend"
	xmlns:liferay-ui="http://liferay.com/tld/ui"
	xmlns:portlet="http://xmlns.jcp.org/portlet_3_0"
>
	<jsp:directive.page contentType="text/html" pageEncoding="UTF-8" />

	<portlet:defineObjects />

	<portlet:actionURL name="sendEmails" var="sendEmailsURL" />

	<aui:form action="${sendEmailsURL}">
		<aui:input label="from-address" name="fromAddress" required="true" value="${fromAddress}" />

		<aui:input label="from-name" name="fromName" required="true" value="${fromName}" />

		<aui:select label="segment" name="segmentsEntryId" value="">
			<c:forEach items="${segmentsEntries}" var="segmentsEntry">
				<aui:option label="${segmentsEntry.name}" value="${segmentsEntry.segmentsEntryId}" />
			</c:forEach>
		</aui:select>

		<liferay-ui:error key="emailBody" message="please-enter-a-valid-body" />

		<liferay-frontend:email-notification-settings
			bodyLabel="${bodyLabel}"
			emailBody="${emailBody}"
			emailParam="emailBody"
			fieldPrefix=""
			helpMessage="help-message"
			showEmailEnabled="false"
			showSubject="true"
		/>

		<hr />

		<aui:button type="submit" value="send-email-messages" />
	</aui:form>
</jsp:root>