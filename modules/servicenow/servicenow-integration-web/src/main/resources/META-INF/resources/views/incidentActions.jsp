<?xml version="1.0" encoding="UTF-8"?>
<jsp:root
	version="2.1"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:liferay-ui="http://liferay.com/tld/ui"
	xmlns:portlet="http://xmlns.jcp.org/portlet_3_0"
>
	<c:set value="${SEARCH_CONTAINER_RESULT_ROW.object}" var="incident" />

	<portlet:renderURL var="editIssueURL">
		<portlet:param name="category" value="${empty param['category'] ? 'inquiry' : param['category']}" />
		<portlet:param name="incidentId" value="${incident.incidentId}" />
		<portlet:param name="mvcRenderCommandName" value="incident-edit" />
		<!-- SearchContainer view state -->
		<portlet:param name="cur" value="${incidentSearchContainerViewState.cur}" />
		<portlet:param name="delta" value="${incidentSearchContainerViewState.delta}" />
		<portlet:param name="displayStyle" value="${incidentSearchContainerViewState.displayStyle}" />
		<portlet:param name="orderByCol" value="${incidentSearchContainerViewState.orderByCol}" />
		<portlet:param name="orderByType" value="${incidentSearchContainerViewState.orderByType}" />
		<portlet:param name="resetCur" value="${incidentSearchContainerViewState.resetCur}" />
	</portlet:renderURL>

	<portlet:renderURL var="viewIssueURL">
		<portlet:param name="category" value="${param['category']}" />
		<portlet:param name="incidentId" value="${incident.incidentId}" />
		<portlet:param name="mvcRenderCommandName" value="incident-view" />
		<!-- SearchContainer view state -->
		<portlet:param name="cur" value="${incidentSearchContainerViewState.cur}" />
		<portlet:param name="delta" value="${incidentSearchContainerViewState.delta}" />
		<portlet:param name="displayStyle" value="${incidentSearchContainerViewState.displayStyle}" />
		<portlet:param name="orderByCol" value="${incidentSearchContainerViewState.orderByCol}" />
		<portlet:param name="orderByType" value="${incidentSearchContainerViewState.orderByType}" />
		<portlet:param name="resetCur" value="${incidentSearchContainerViewState.resetCur}" />
	</portlet:renderURL>

	<liferay-ui:icon-menu markupView="lexicon">
		<liferay-ui:icon message="edit" url="${editIssueURL}" />
		<liferay-ui:icon message="view" url="${viewIssueURL}" />
	</liferay-ui:icon-menu>
</jsp:root>