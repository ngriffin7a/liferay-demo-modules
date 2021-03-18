package com.liferay.demo.document.audit.internal;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.petra.string.CharPool;
import com.liferay.portal.kernel.audit.AuditException;
import com.liferay.portal.kernel.audit.AuditMessage;
import com.liferay.portal.kernel.audit.AuditRouter;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.BaseFilter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.security.audit.event.generators.util.Attribute;
import com.liferay.portal.security.audit.event.generators.util.AuditMessageBuilder;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.ArrayList;
import java.util.List;

@Component(
	immediate = true,
	property = {
		"servlet-context-name=",
		"servlet-filter-name=Custom Filter",
		"url-pattern=/documents/*"
	},
	service = Filter.class
)
public class DocumentServletFilter extends BaseFilter {

	@Override
	protected void processFilter(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain)
		throws Exception {

		String path = HttpUtil.fixPath(httpServletRequest.getPathInfo());

		String[] pathArray = StringUtil.split(path, CharPool.SLASH);

		List<Attribute> attributes = new ArrayList<>();

		for (int i = 0; i < pathArray.length; i++) {
			attributes.add(new Attribute("pathEntry" + i, pathArray[i], ""));
		}

		AuditMessage auditMessage =
			AuditMessageBuilder.buildAuditMessage(
				"DOWNLOAD", DLFileEntry.class.getName(), 0, attributes);

		if (auditMessage.getUserId() == 0L) {
			auditMessage.setUserName("Guest");
		}

		try {
			_auditRouter.route(auditMessage);
		}
		catch (AuditException auditException) {
			throw new ModelListenerException(auditException);
		}

		_log.debug(auditMessage.getMessage());

		super.processFilter(httpServletRequest, httpServletResponse, filterChain);
	}

	@Override
	protected Log getLog() {
		return _log;
	}

	private static final Log _log = LogFactoryUtil.getLog(DocumentServletFilter.class);

	@Reference
	private AuditRouter _auditRouter;
}
