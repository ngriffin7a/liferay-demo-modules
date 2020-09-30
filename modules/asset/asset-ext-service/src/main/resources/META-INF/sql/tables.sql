create table ASSET_EXT_AssetEntryUserStatus (
	uuid_ VARCHAR(75) null,
	assetEntryUserStatusId LONG not null,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	entryId LONG not null,
	acknowledged BOOLEAN,
	acknowledgementDate DATE null,
	downloaded BOOLEAN,
	downloadDate DATE null,
	read_ BOOLEAN,
	readDate DATE null,
	primary key (assetEntryUserStatusId, entryId)
);