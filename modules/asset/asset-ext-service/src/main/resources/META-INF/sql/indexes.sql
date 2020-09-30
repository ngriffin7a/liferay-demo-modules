create index IX_CA0DFF05 on ASSET_EXT_AssetEntryUserStatus (entryId, userId);
create index IX_556BC158 on ASSET_EXT_AssetEntryUserStatus (groupId);
create index IX_751E5CD1 on ASSET_EXT_AssetEntryUserStatus (userId, entryId);
create index IX_7D49906 on ASSET_EXT_AssetEntryUserStatus (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_399BA908 on ASSET_EXT_AssetEntryUserStatus (uuid_[$COLUMN_LENGTH:75$], groupId);