<?xml version="1.0" encoding="UTF-8"?>
<jsp:root
	version="2.1"
	xmlns:aui="http://liferay.com/tld/aui"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
	xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:liferay-ui="http://liferay.com/tld/ui"
	xmlns:portlet="http://xmlns.jcp.org/portlet_3_0"
>
	<jsp:directive.page contentType="text/html" pageEncoding="UTF-8" />

	<fmt:setBundle basename="content.Language" var="i18n" />

	<c:set value="${issueSearchContainerViewState}" var="searchContainerViewState" />

	<div class="container-fluid-1280">
		<c:choose>
			<c:when test="${param['mvcRenderCommandName'] == 'issue-add'}">
				<c:set value="${!currentUser.mayAddIssue()}" var="readOnly" />

				<h1><liferay-ui:message key="action.ADD_ISSUE" /></h1>
			</c:when>
			<c:when test="${param['mvcRenderCommandName'] == 'issue-edit'}">
				<c:set value="${!currentUser.mayEditIssue(issue.issueId)}" var="readOnly" />

				<h1><liferay-ui:message key="action.EDIT_ISSUE" /></h1>
			</c:when>
			<c:when test="${param['mvcRenderCommandName'] == 'issue-view'}">
				<c:set value="true" var="readOnly" />

				<h1><liferay-ui:message key="action.VIEW_ISSUE" /></h1>
			</c:when>
		</c:choose>

		<c:if test="${!readOnly}">
			<portlet:actionURL name="submitIssue" var="formActionURL">
				<portlet:param name="mvcRenderCommandName" type="render" value="issue-edit" />
				<!-- SearchContainer view state -->
				<portlet:param name="categoryId" value="${searchContainerViewState.categoryId}" />
				<portlet:param name="cur" type="render" value="${searchContainerViewState.cur}" />
				<portlet:param name="delta" type="render" value="${searchContainerViewState.delta}" />
				<portlet:param name="displayStyle" type="render" value="${searchContainerViewState.displayStyle}" />
				<portlet:param name="navigation" type="render" value="${searchContainerViewState.navigation}" />
				<portlet:param name="orderByCol" type="render" value="${param.orderByCol}" />
				<portlet:param name="orderByType" type="render" value="${param.orderByType}" />
				<portlet:param name="resetCur" type="render" value="${searchContainerViewState.resetCur}" />
			</portlet:actionURL>
		</c:if>

		<aui:form action="${formActionURL}" name="issueForm">
			<aui:fieldset-group markupView="lexicon">
				<aui:fieldset>
					<aui:input name="issueId" type="hidden" value="${issue.issueId}" />

					<aui:input disabled="true" name="key" value="${issue.key}" />

					<aui:select disabled="${readOnly}" label="status" name="status" value="">
						<aui:option label="all" value="all" />
						<c:forEach items="${sessionScope.statuses}" var="status">
							<aui:option label="${status.name}" value="${status.statusId}" />
						</c:forEach>
					</aui:select>

					<aui:input
						disabled="${readOnly}"
						localized="true"
						name="summary"
						type="text"
						value="${issue.summary}"
					>
						<aui:validator name="required" />

						<aui:validator errorMessage="error.issue-summary-format" name="custom">
							/*<![CDATA[*/
								function(val, fieldNode, ruleValue) {
								var wordExpression =
								new RegExp("^[^\\[\\]\\^$<>]*$");

								return wordExpression.test(val);
								}
							/*]]>*/
						</aui:validator>
					</aui:input>

					<c:if test="${fields.hasErrors('summary')}">
						<span class="portlet-msg-error">${fields.error('summary')}</span>
					</c:if>

					<aui:input
						disabled="${readOnly}"
						name="updatedDate"
						type="date"
						value="${issue.updatedDate}"
					>
						<aui:validator name="required" />
					</aui:input>

					<c:if test="${fields.hasErrors('updatedDate')}">
						<span class="portlet-msg-error">${fields.error('updatedDate')}</span>
					</c:if>
				</aui:fieldset>
			</aui:fieldset-group>

			<aui:button-row>
				<c:if test="${!readOnly}">
					<aui:button cssClass="btn btn-primary" type="submit" />
				</c:if>

				<c:choose>
					<c:when test="${empty param['backURL']}">
						<portlet:renderURL var="cancelURL">
							<portlet:param name="status" value="${param['']}" />
							<portlet:param name="mvcRenderCommandName" value="issues" />
							<!-- SearchContainer view state -->
							<portlet:param name="categoryId" value="${searchContainerViewState.categoryId}" />
							<portlet:param name="cur" value="${searchContainerViewState.cur}" />
							<portlet:param name="delta" value="${searchContainerViewState.delta}" />
							<portlet:param name="displayStyle" value="${searchContainerViewState.displayStyle}" />
							<portlet:param name="navigation" value="${searchContainerViewState.navigation}" />
							<portlet:param name="orderByCol" value="${param.orderByCol}" />
							<portlet:param name="orderByType" value="${param.orderByType}" />
							<portlet:param name="resetCur" value="${searchContainerViewState.resetCur}" />
						</portlet:renderURL>
					</c:when>
					<c:otherwise>
						<c:set value="${param.backURL}" var="cancelURL" />
					</c:otherwise>
				</c:choose>

				<aui:button cssClass="btn btn-secondary" onClick="${cancelURL}" type="cancel" />
			</aui:button-row>
		</aui:form>
	</div>

	<c:if test="${!readOnly}">
		<aui:script>
		/*<![CDATA[*/
			/**
			 * Handle form submit. Set editor value to the hidden field
			 * which transports the value to the backend.
			*/
			AUI().ready(function() {

				$('#${namespace}issueForm').on('submit', function() {

					var editorValue = window['${namespace}summaryEditor'].getHTML();

					if (editorValue.length == 0) {
						return false;
					}

					window['${namespace}summary'].value = editorValue;

					submitForm(document.${namespace}issueForm);
				});
			});
		/*]]>*/
		</aui:script>
	</c:if>
</jsp:root>