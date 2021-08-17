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

	<portlet:actionURL name="sendEmails" var="sendEmailsURL" />

	<clay:container-fluid>
		<clay:sheet>
			<aui:form action="${sendEmailsURL}">
					<liferay-frontend:fieldset-group>
						<liferay-frontend:fieldset label="from">
							<aui:input label="from-address" name="fromAddress" required="true" value="${fromAddress}" />

							<aui:input label="from-name" name="fromName" required="true" value="${fromName}" />
						</liferay-frontend:fieldset>

						<liferay-frontend:fieldset label="to">
							<aui:select label="segment" name="segmentsEntryId" value="">
								<aui:option label="-- Select --" value="0" />
								<c:forEach items="${segmentsEntries}" var="segmentsEntry">
									<aui:option label="${segmentsEntry.name}" value="${segmentsEntry.segmentsEntryId}" />
								</c:forEach>
							</aui:select>
							<aui:select label="user-group" name="userGroupId" value="">
								<aui:option label="-- Select --" value="0" />
								<c:forEach items="${userGroups}" var="userGroup">
									<aui:option label="${userGroup.name}" value="${userGroup.userGroupId}" />
								</c:forEach>
							</aui:select>
							<aui:input label="email-addresses" name="emailAddresses" required="false" value="" />
						</liferay-frontend:fieldset>
					</liferay-frontend:fieldset-group>

				<liferay-frontend:fieldset label="message">
					<div class="panel-body">
						<aui:select label="collection" name="assetListEntryId" value="">
							<aui:option label="-- Select --" value="0" />
							<c:forEach items="${assetListEntries}" var="assetListEntry">
								<aui:option label="${groups[assetListEntry.groupId]} > ${assetListEntry.title}" value="${assetListEntry.assetListEntryId}" />
							</c:forEach>
						</aui:select>
						<aui:select label="protocol" name="protocol" value="">
							<aui:option label="send-via-email" value="send-via-email" />
							<aui:option label="send-via-sms" value="send-via-sms" />
						</aui:select>
					</div>

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
				</liferay-frontend:fieldset>

				<liferay-frontend:fieldset label="delivery-date">
					<liferay-ui:input-date
						dayParam="sendTimeDay"
						dayValue="${sendCalendar.get(5)}"
						disabled="false"
						firstDayOfWeek="${sendCalendar.getFirstDayOfWeek() - 1}"
						monthParam="sendTimeMonth"
						monthValue="${sendCalendar.get(2)}"
						name="sendDate"
						yearParam="sendTimeYear"
						yearValue="${sendCalendar.get(1)}"
					/>

					<liferay-ui:input-time
						amPmParam="sendTimeAmPm"
						amPmValue="${sendCalendar.get(9)}"
						dateParam="sendDateTime"
						dateValue="${sendCalendar.getTime()}"
						disabled="false"
						hourParam="sendTimeHour"
						hourValue="${sendCalendar.get(10)}"
						minuteParam="sendTimeMinute"
						minuteValue="${sendCalendar.get(12)}"
						name="sendTime"
					/>
				</liferay-frontend:fieldset>

				<hr />

				<aui:button-row>
					<aui:button type="submit" value="send-now" />
					<aui:button type="submit" value="schedule" />
				</aui:button-row>
			</aui:form>
		</clay:sheet>
	</clay:container-fluid>
</jsp:root>