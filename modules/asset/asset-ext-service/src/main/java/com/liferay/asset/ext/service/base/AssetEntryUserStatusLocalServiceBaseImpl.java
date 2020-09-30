/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.asset.ext.service.base;

import com.liferay.asset.ext.model.AssetEntryUserStatus;
import com.liferay.asset.ext.service.AssetEntryUserStatusLocalService;
import com.liferay.asset.ext.service.persistence.AssetEntryUserStatusPK;
import com.liferay.asset.ext.service.persistence.AssetEntryUserStatusPersistence;
import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the asset entry user status local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.asset.ext.service.impl.AssetEntryUserStatusLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.asset.ext.service.impl.AssetEntryUserStatusLocalServiceImpl
 * @generated
 */
public abstract class AssetEntryUserStatusLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AopService, AssetEntryUserStatusLocalService,
			   IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>AssetEntryUserStatusLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.asset.ext.service.AssetEntryUserStatusLocalServiceUtil</code>.
	 */

	/**
	 * Adds the asset entry user status to the database. Also notifies the appropriate model listeners.
	 *
	 * @param assetEntryUserStatus the asset entry user status
	 * @return the asset entry user status that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public AssetEntryUserStatus addAssetEntryUserStatus(
		AssetEntryUserStatus assetEntryUserStatus) {

		assetEntryUserStatus.setNew(true);

		return assetEntryUserStatusPersistence.update(assetEntryUserStatus);
	}

	/**
	 * Creates a new asset entry user status with the primary key. Does not add the asset entry user status to the database.
	 *
	 * @param assetEntryUserStatusPK the primary key for the new asset entry user status
	 * @return the new asset entry user status
	 */
	@Override
	@Transactional(enabled = false)
	public AssetEntryUserStatus createAssetEntryUserStatus(
		AssetEntryUserStatusPK assetEntryUserStatusPK) {

		return assetEntryUserStatusPersistence.create(assetEntryUserStatusPK);
	}

	/**
	 * Deletes the asset entry user status with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param assetEntryUserStatusPK the primary key of the asset entry user status
	 * @return the asset entry user status that was removed
	 * @throws PortalException if a asset entry user status with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public AssetEntryUserStatus deleteAssetEntryUserStatus(
			AssetEntryUserStatusPK assetEntryUserStatusPK)
		throws PortalException {

		return assetEntryUserStatusPersistence.remove(assetEntryUserStatusPK);
	}

	/**
	 * Deletes the asset entry user status from the database. Also notifies the appropriate model listeners.
	 *
	 * @param assetEntryUserStatus the asset entry user status
	 * @return the asset entry user status that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public AssetEntryUserStatus deleteAssetEntryUserStatus(
		AssetEntryUserStatus assetEntryUserStatus) {

		return assetEntryUserStatusPersistence.remove(assetEntryUserStatus);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			AssetEntryUserStatus.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return assetEntryUserStatusPersistence.findWithDynamicQuery(
			dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.asset.ext.model.impl.AssetEntryUserStatusModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return assetEntryUserStatusPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.asset.ext.model.impl.AssetEntryUserStatusModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return assetEntryUserStatusPersistence.findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return assetEntryUserStatusPersistence.countWithDynamicQuery(
			dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection) {

		return assetEntryUserStatusPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public AssetEntryUserStatus fetchAssetEntryUserStatus(
		AssetEntryUserStatusPK assetEntryUserStatusPK) {

		return assetEntryUserStatusPersistence.fetchByPrimaryKey(
			assetEntryUserStatusPK);
	}

	/**
	 * Returns the asset entry user status matching the UUID and group.
	 *
	 * @param uuid the asset entry user status's UUID
	 * @param groupId the primary key of the group
	 * @return the matching asset entry user status, or <code>null</code> if a matching asset entry user status could not be found
	 */
	@Override
	public AssetEntryUserStatus fetchAssetEntryUserStatusByUuidAndGroupId(
		String uuid, long groupId) {

		return assetEntryUserStatusPersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the asset entry user status with the primary key.
	 *
	 * @param assetEntryUserStatusPK the primary key of the asset entry user status
	 * @return the asset entry user status
	 * @throws PortalException if a asset entry user status with the primary key could not be found
	 */
	@Override
	public AssetEntryUserStatus getAssetEntryUserStatus(
			AssetEntryUserStatusPK assetEntryUserStatusPK)
		throws PortalException {

		return assetEntryUserStatusPersistence.findByPrimaryKey(
			assetEntryUserStatusPK);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(
			assetEntryUserStatusLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(AssetEntryUserStatus.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"primaryKey.assetEntryUserStatusId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			assetEntryUserStatusLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(
			AssetEntryUserStatus.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"primaryKey.assetEntryUserStatusId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(
			assetEntryUserStatusLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(AssetEntryUserStatus.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName(
			"primaryKey.assetEntryUserStatusId");
	}

	@Override
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		final PortletDataContext portletDataContext) {

		final ExportActionableDynamicQuery exportActionableDynamicQuery =
			new ExportActionableDynamicQuery() {

				@Override
				public long performCount() throws PortalException {
					ManifestSummary manifestSummary =
						portletDataContext.getManifestSummary();

					StagedModelType stagedModelType = getStagedModelType();

					long modelAdditionCount = super.performCount();

					manifestSummary.addModelAdditionCount(
						stagedModelType, modelAdditionCount);

					long modelDeletionCount =
						ExportImportHelperUtil.getModelDeletionCount(
							portletDataContext, stagedModelType);

					manifestSummary.addModelDeletionCount(
						stagedModelType, modelDeletionCount);

					return modelAdditionCount;
				}

			};

		initActionableDynamicQuery(exportActionableDynamicQuery);

		exportActionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {

				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					portletDataContext.addDateRangeCriteria(
						dynamicQuery, "modifiedDate");
				}

			});

		exportActionableDynamicQuery.setCompanyId(
			portletDataContext.getCompanyId());

		exportActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod
				<AssetEntryUserStatus>() {

				@Override
				public void performAction(
						AssetEntryUserStatus assetEntryUserStatus)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, assetEntryUserStatus);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(
					AssetEntryUserStatus.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return assetEntryUserStatusLocalService.deleteAssetEntryUserStatus(
			(AssetEntryUserStatus)persistedModel);
	}

	public BasePersistence<AssetEntryUserStatus> getBasePersistence() {
		return assetEntryUserStatusPersistence;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return assetEntryUserStatusPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns all the asset entry user statuses matching the UUID and company.
	 *
	 * @param uuid the UUID of the asset entry user statuses
	 * @param companyId the primary key of the company
	 * @return the matching asset entry user statuses, or an empty list if no matches were found
	 */
	@Override
	public List<AssetEntryUserStatus>
		getAssetEntryUserStatusesByUuidAndCompanyId(
			String uuid, long companyId) {

		return assetEntryUserStatusPersistence.findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of asset entry user statuses matching the UUID and company.
	 *
	 * @param uuid the UUID of the asset entry user statuses
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of asset entry user statuses
	 * @param end the upper bound of the range of asset entry user statuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching asset entry user statuses, or an empty list if no matches were found
	 */
	@Override
	public List<AssetEntryUserStatus>
		getAssetEntryUserStatusesByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			OrderByComparator<AssetEntryUserStatus> orderByComparator) {

		return assetEntryUserStatusPersistence.findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the asset entry user status matching the UUID and group.
	 *
	 * @param uuid the asset entry user status's UUID
	 * @param groupId the primary key of the group
	 * @return the matching asset entry user status
	 * @throws PortalException if a matching asset entry user status could not be found
	 */
	@Override
	public AssetEntryUserStatus getAssetEntryUserStatusByUuidAndGroupId(
			String uuid, long groupId)
		throws PortalException {

		return assetEntryUserStatusPersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the asset entry user statuses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.asset.ext.model.impl.AssetEntryUserStatusModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset entry user statuses
	 * @param end the upper bound of the range of asset entry user statuses (not inclusive)
	 * @return the range of asset entry user statuses
	 */
	@Override
	public List<AssetEntryUserStatus> getAssetEntryUserStatuses(
		int start, int end) {

		return assetEntryUserStatusPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of asset entry user statuses.
	 *
	 * @return the number of asset entry user statuses
	 */
	@Override
	public int getAssetEntryUserStatusesCount() {
		return assetEntryUserStatusPersistence.countAll();
	}

	/**
	 * Updates the asset entry user status in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param assetEntryUserStatus the asset entry user status
	 * @return the asset entry user status that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public AssetEntryUserStatus updateAssetEntryUserStatus(
		AssetEntryUserStatus assetEntryUserStatus) {

		return assetEntryUserStatusPersistence.update(assetEntryUserStatus);
	}

	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			AssetEntryUserStatusLocalService.class,
			IdentifiableOSGiService.class, PersistedModelLocalService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		assetEntryUserStatusLocalService =
			(AssetEntryUserStatusLocalService)aopProxy;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return AssetEntryUserStatusLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return AssetEntryUserStatus.class;
	}

	protected String getModelClassName() {
		return AssetEntryUserStatus.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				assetEntryUserStatusPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(
				dataSource, sql);

			sqlUpdate.update();
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
	}

	protected AssetEntryUserStatusLocalService assetEntryUserStatusLocalService;

	@Reference
	protected AssetEntryUserStatusPersistence assetEntryUserStatusPersistence;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ClassNameLocalService
		classNameLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@Reference
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

	@Reference
	protected com.liferay.asset.kernel.service.AssetEntryLocalService
		assetEntryLocalService;

	@Reference
	protected com.liferay.asset.kernel.service.AssetTagLocalService
		assetTagLocalService;

}