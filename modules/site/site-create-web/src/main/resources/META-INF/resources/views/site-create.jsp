<?xml version="1.0" encoding="UTF-8"?>
<jsp:root
	version="2.1"
	xmlns:aui="http://liferay.com/tld/aui"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:clay="http://liferay.com/tld/clay"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:liferay-frontend="http://liferay.com/tld/frontend"
	xmlns:liferay-ui="http://liferay.com/tld/ui"
	xmlns:portlet="http://xmlns.jcp.org/portlet_3_0"
>
	<jsp:directive.page contentType="text/html" pageEncoding="UTF-8" />

	<portlet:defineObjects />

	<portlet:actionURL name="createSite" var="createSiteURL" />

	<aui:form action="${createSiteURL}">
		<aui:select label="site-template" name="layoutSetPrototypeId" value="">
			<aui:option label="blank-site" value="0" />

			<c:forEach items="${siteTemplates}" var="siteTemplate">
				<aui:option label="${siteTemplate.name}" value="${siteTemplate.id}" />
			</c:forEach>
		</aui:select>

		<aui:input label="site-name" name="name" required="true" />
		<aui:input label="friendly-url" name="friendlyURL" required="true" />
		<aui:input name="active" type="toggle-switch" value="true" />
		<aui:input label="allow-manual-membership-management" name="manualMembership" type="toggle-switch" value="true" />

		<aui:select label="membership-type" name="type">
			<aui:option label="open" value="${GroupConstants.TYPE_SITE_OPEN}" />
			<aui:option label="restricted" value="${GroupConstants.TYPE_SITE_RESTRICTED}" />
			<aui:option label="private" value="${GroupConstants.TYPE_SITE_PRIVATE}" />
		</aui:select>

		<hr />

		<aui:button type="submit" />
	</aui:form>
</jsp:root>