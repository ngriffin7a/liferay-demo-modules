<?xml version="1.0" encoding="UTF-8"?>
<jsp:root
	version="2.1"
	xmlns:aui="http://liferay.com/tld/aui"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:portlet="http://xmlns.jcp.org/portlet_3_0"
>
	<jsp:directive.page contentType="text/html" pageEncoding="UTF-8" />

	<portlet:defineObjects />

	<portlet:actionURL name="sendSms" var="sendSmsURL" />

	<aui:form action="${sendSmsURL}">
		<aui:select label="segment" name="segmentsEntryId" value="">
			<c:forEach items="${segmentsEntries}" var="segmentsEntry">
				<aui:option label="${segmentsEntry.name}" value="${segmentsEntry.segmentsEntryId}" />
			</c:forEach>
		</aui:select>

		<aui:input label="message" name="message" required="true" />

		<hr />

		<aui:button type="submit" value="send-sms-messages" />
	</aui:form>
</jsp:root>