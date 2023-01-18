package com.liferay.demo.fragment.widget.web.portlet;

import com.liferay.demo.fragment.widget.web.constants.FragmentWidgetWebPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author ngriffin
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=FragmentWidgetWeb",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/views/fragment.jspx",
		"javax.portlet.name=" + FragmentWidgetWebPortletKeys.FRAGMENTWIDGETWEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class FragmentWidgetWebPortlet extends MVCPortlet {
}