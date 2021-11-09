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

	<c:set value="${issueSearchContainerViewState}" var="searchContainerViewState" />

	<clay:management-toolbar
		displayContext="${issueClayToolbarViewState}"
		itemsTotal="${issueCount}"
		searchContainerId="issues"
	/>

	<liferay-ui:search-container emptyResultsMessage="no-issues" id="issues" total="${issueCount}">
		<liferay-ui:search-container-results results="${issues}" />

		<liferay-ui:search-container-row
			className="com.liferay.jira.integration.JiraIssue"
			modelVar="issue"
		>
			<c:if test="${currentUser.mayViewIssue(issue.issueId)}">
				<portlet:renderURL var="viewIssueURL">
					<portlet:param name="issueId" value="${issue.issueId}" />
					<portlet:param name="javax.portlet.render" value="issue" />
					<portlet:param name="viewId" value="view" />
					<!-- SearchContainer view state -->
					<portlet:param name="cur" value="${searchContainerViewState.cur}" />
					<portlet:param name="delta" value="${searchContainerViewState.delta}" />
					<portlet:param name="displayStyle" value="${searchContainerViewState.displayStyle}" />
					<portlet:param name="orderByCol" value="${searchContainerViewState.orderByCol}" />
					<portlet:param name="orderByType" value="${searchContainerViewState.orderByType}" />
					<portlet:param name="resetCur" value="${searchContainerViewState.resetCur}" />
				</portlet:renderURL>
			</c:if>

			<c:choose>

				<!-- List display style -->
				<c:when test='${searchContainerViewState.displayStyle == "descriptive"}'>
					<liferay-ui:search-container-column-text colspan="2">
						<h5 class="text-default">
							<liferay-ui:message arguments="${issue.modifiedDateInfo}" key="x-modified-x-ago" />
						</h5>

						<h4>
							<aui:a href="${viewIssueURL}">${mvc.encoders.html(issue.description)}</aui:a>
						</h4>
					</liferay-ui:search-container-column-text>
				</c:when>

				<!-- Cards display style -->
				<c:when test='${searchContainerViewState.displayStyle == "icon"}'>
					<liferay-ui:search-container-column-text>
						<liferay-frontend:icon-vertical-card
							actionJsp="/views/issueActions.jspx"
							actionJspServletContext="${application}"
							icon="cards2"
							resultRow="${row}"
							title="${issue.issueName}"
							url="${viewIssueURL}"
						>
							<liferay-frontend:vertical-card-footer>
								<div class="truncate-text">${issue.strippedDescription}</div>
							</liferay-frontend:vertical-card-footer>
						</liferay-frontend:icon-vertical-card>
					</liferay-ui:search-container-column-text>
				</c:when>

				<!-- Table display style -->
				<c:otherwise>
					<liferay-ui:search-container-column-text href="${viewIssueURL}" name="key" value="${issue.key}" />

					<liferay-ui:search-container-column-date name="create-date" property="createdDate" />

					<liferay-ui:search-container-column-text name="status" value="${issue.status}" />

					<liferay-ui:search-container-column-text name="summary" value="${issue.summary}" />
					<!--
					<liferay-ui:search-container-column-jsp path="/views/issueActions.jspx" />
					-->
				</c:otherwise>
			</c:choose>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator displayStyle="${searchContainerViewState.displayStyle}" markupView="lexicon" />
	</liferay-ui:search-container>
</jsp:root>