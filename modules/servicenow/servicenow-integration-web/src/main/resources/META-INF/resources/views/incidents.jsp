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

	<c:set value="${incidentSearchContainerViewState}" var="searchContainerViewState" />

	<clay:management-toolbar
		displayContext="${incidentManagementToolbarViewState}"
		itemsTotal="${incidentCount}"
		searchContainerId="incidents"
	/>

	<liferay-ui:search-container emptyResultsMessage="no-incidents" id="incidents" total="${incidentCount}">
		<liferay-ui:search-container-results results="${incidents}" />

		<liferay-ui:search-container-row
			className="com.liferay.demo.servicenow.Incident"
			modelVar="incident"
		>
			<c:if test="${currentUser.mayViewIncident(incident.incidentId)}">
				<portlet:renderURL var="viewIncidentURL">
					<portlet:param name="category" value="${incident.category}" />
					<portlet:param name="incidentId" value="${incident.incidentId}" />
					<portlet:param name="mvcRenderCommandName" value="incident-view" />
					<!-- SearchContainer view state -->
					<portlet:param name="categoryId" value="${searchContainerViewState.categoryId}" />
					<portlet:param name="cur" value="${searchContainerViewState.cur}" />
					<portlet:param name="delta" value="${searchContainerViewState.delta}" />
					<portlet:param name="displayStyle" value="${searchContainerViewState.displayStyle}" />
					<portlet:param name="navigation" value="${searchContainerViewState.navigation}" />
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
							<liferay-ui:message arguments="${incident.modifiedDateInfo}" key="x-modified-x-ago" />
						</h5>

						<h4>
							<aui:a href="${viewIncidentURL}">${mvc.encoders.html(incident.description)}</aui:a>
						</h4>
					</liferay-ui:search-container-column-text>
				</c:when>

				<!-- Cards display style -->
				<c:when test='${searchContainerViewState.displayStyle == "icon"}'>
					<liferay-ui:search-container-column-text>
						<liferay-frontend:icon-vertical-card
							actionJsp="/views/incidentActions.jspx"
							actionJspServletContext="${application}"
							icon="cards2"
							resultRow="${row}"
							title="${incident.incidentName}"
							url="${viewIncidentURL}"
						>
							<liferay-frontend:vertical-card-footer>
								<div class="truncate-text">${incident.strippedDescription}</div>
							</liferay-frontend:vertical-card-footer>
						</liferay-frontend:icon-vertical-card>
					</liferay-ui:search-container-column-text>
				</c:when>

				<!-- Table display style -->
				<c:otherwise>
					<liferay-ui:search-container-column-text href="${viewIncidentURL}" name="number" value="${incident.number}" />

					<liferay-ui:search-container-column-text name="category">
						<liferay-ui:message key="${incident.category}" />
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-text href="${incident.href}" name="short-description" target="_blank" value="${incident.shortDescription}" />

					<liferay-ui:search-container-column-jsp path="/views/incidentActions.jsp" />
				</c:otherwise>
			</c:choose>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator displayStyle="${searchContainerViewState.displayStyle}" markupView="lexicon" />
	</liferay-ui:search-container>
</jsp:root>