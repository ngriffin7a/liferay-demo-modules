<?xml version="1.0" encoding="UTF-8"?>
<jsp:root
	version="2.1"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:liferay-ui="http://liferay.com/tld/ui"
	xmlns:portlet="http://xmlns.jcp.org/portlet_3_0"
>
	<c:set value="${SEARCH_CONTAINER_RESULT_ROW.object}" var="issue" />

	<portlet:renderURL var="editIssueURL">
		<portlet:param name="status" value="${empty param['status'] ? 'all' : param['status']}" />
		<portlet:param name="issueId" value="${issue.issueId}" />
		<portlet:param name="mvcRenderCommandName" value="issue-edit" />
		<!-- SearchContainer view state -->
		<portlet:param name="categoryId" value="${issueSearchContainerViewState.categoryId}" />
		<portlet:param name="cur" value="${issueSearchContainerViewState.cur}" />
		<portlet:param name="delta" value="${issueSearchContainerViewState.delta}" />
		<portlet:param name="displayStyle" value="${issueSearchContainerViewState.displayStyle}" />
		<portlet:param name="navigation" value="${issueSearchContainerViewState.navigation}" />
		<portlet:param name="orderByCol" value="${issueSearchContainerViewState.orderByCol}" />
		<portlet:param name="orderByType" value="${issueSearchContainerViewState.orderByType}" />
		<portlet:param name="resetCur" value="${issueSearchContainerViewState.resetCur}" />
	</portlet:renderURL>

	<portlet:renderURL var="viewIssueURL">
		<portlet:param name="status" value="${param['status']}" />
		<portlet:param name="issueId" value="${issue.issueId}" />
		<portlet:param name="mvcRenderCommandName" value="issue-view" />
		<!-- SearchContainer view state -->
		<portlet:param name="categoryId" value="${issueSearchContainerViewState.categoryId}" />
		<portlet:param name="cur" value="${issueSearchContainerViewState.cur}" />
		<portlet:param name="delta" value="${issueSearchContainerViewState.delta}" />
		<portlet:param name="displayStyle" value="${issueSearchContainerViewState.displayStyle}" />
		<portlet:param name="navigation" value="${issueSearchContainerViewState.navigation}" />
		<portlet:param name="orderByCol" value="${issueSearchContainerViewState.orderByCol}" />
		<portlet:param name="orderByType" value="${issueSearchContainerViewState.orderByType}" />
		<portlet:param name="resetCur" value="${issueSearchContainerViewState.resetCur}" />
	</portlet:renderURL>

	<liferay-ui:icon-menu markupView="lexicon">
		<liferay-ui:icon message="edit" url="${editIssueURL}" />
		<liferay-ui:icon message="view" url="${viewIssueURL}" />
	</liferay-ui:icon-menu>
</jsp:root>