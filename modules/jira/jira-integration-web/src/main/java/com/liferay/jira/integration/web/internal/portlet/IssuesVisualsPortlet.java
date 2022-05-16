package com.liferay.jira.integration.web.internal.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.jira.integration.JiraIssue;
import com.liferay.jira.integration.JiraIssueService;
import com.liferay.jira.integration.JiraToken;
import com.liferay.jira.integration.JiraTokenService;
import com.liferay.jira.integration.web.internal.constants.PortletKeys;
import com.liferay.jira.integration.web.internal.el.CurrentUser;

import java.io.IOException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.RenderParameters;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Wes Kempa
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=Jira Integration",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.footer-portlet-javascript=https://cdn.fusioncharts.com/fusioncharts/latest/fusioncharts.js",
		"com.liferay.portlet.footer-portlet-javascript=https://cdn.fusioncharts.com/fusioncharts/latest/themes/fusioncharts.theme.fusion.js",
		"com.liferay.portlet.footer-portlet-javascript=/javascript/dashboard.js",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Issues Visual",
		"javax.portlet.init-param.template-path=/views/",
		"javax.portlet.init-param.view-template=/views/dashboard.jsp",
		"javax.portlet.name=" + PortletKeys.ISSUES_VISUAL,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"javax.portlet.version=3.0"
	},
	service = Portlet.class
)
public class IssuesVisualsPortlet extends MVCPortlet {

}