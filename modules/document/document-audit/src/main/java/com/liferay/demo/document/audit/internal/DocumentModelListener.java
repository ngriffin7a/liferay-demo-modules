package com.liferay.demo.document.audit.internal;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.portal.kernel.audit.AuditException;
import com.liferay.portal.kernel.audit.AuditMessage;
import com.liferay.portal.kernel.audit.AuditRouter;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.security.audit.event.generators.constants.EventTypes;
import com.liferay.portal.security.audit.event.generators.util.Attribute;
import com.liferay.portal.security.audit.event.generators.util.AttributesBuilder;
import com.liferay.portal.security.audit.event.generators.util.AuditMessageBuilder;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.ArrayList;
import java.util.List;

@Component(immediate = true, service = ModelListener.class)
public class DocumentModelListener extends BaseModelListener<DLFileEntry> {

	@Override
	public void onAfterCreate(
		DLFileEntry dlFileEntry) throws ModelListenerException {

		List<Attribute> attributes = new ArrayList<>();
		attributes.add(new Attribute("fileName", dlFileEntry.getFileName(), ""));

		AuditMessage auditMessage =
			AuditMessageBuilder.buildAuditMessage(
				EventTypes.ADD, DLFileEntry.class.getName(),
				dlFileEntry.getClassPK(), attributes);

		if (auditMessage.getUserId() == 0L) {
			auditMessage.setUserName("Guest");
		}

		try {
			_auditRouter.route(auditMessage);
		}
		catch (AuditException auditException) {
			throw new ModelListenerException(auditException);
		}
	}

	@Override
	public void onBeforeUpdate(
		DLFileEntry newDLFileEntry) throws ModelListenerException {

		try {
			DLFileEntry oldDLFileEntry =
				_dlFileEntryLocalService.getDLFileEntry(
					newDLFileEntry.getFileEntryId());

			List<Attribute> attributes = getModifiedAttributes(
				newDLFileEntry, oldDLFileEntry);

			if (!attributes.isEmpty()) {

				AuditMessage auditMessage =
					AuditMessageBuilder.buildAuditMessage(
						EventTypes.UPDATE, DLFileEntry.class.getName(),
						newDLFileEntry.getClassPK(), attributes);
					_auditRouter.route(auditMessage);
			}
		}
		catch (Exception exception) {
			throw new ModelListenerException(exception);
		}
	}

	protected List<Attribute> getModifiedAttributes(
		DLFileEntry newDLFileEntry, DLFileEntry oldDLFileEntry) {

		AttributesBuilder attributesBuilder = new AttributesBuilder(
			newDLFileEntry, oldDLFileEntry);

		attributesBuilder.add("fileName");

		return attributesBuilder.getAttributes();
	}

	@Reference
	private DLFileEntryLocalService _dlFileEntryLocalService;

	@Reference
	private AuditRouter _auditRouter;
}
