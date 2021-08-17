package com.liferay.demo.notification.sender.web.internal.portlet;

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.application.list.constants.PanelCategoryKeys;
import com.liferay.demo.notification.sender.web.NotificationSenderWebPortletKeys;
import com.liferay.portal.kernel.model.Portlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true,
	property = {
		"panel.app.order:Integer=400",
		"panel.category.key=" + PanelCategoryKeys.SITE_ADMINISTRATION_MEMBERS
	},
	service = PanelApp.class
)
public class SegmentNotifierPanelApp extends BasePanelApp {

	@Override
	public String getPortletId() {
		return NotificationSenderWebPortletKeys.NOTIFICATION_SENDER_WEB;
	}

	@Override
	@Reference(target = "(javax.portlet.name=" + NotificationSenderWebPortletKeys.NOTIFICATION_SENDER_WEB + ")", unbind="-")
	public void setPortlet(Portlet portlet) {
		super.setPortlet(portlet);
	}
}
