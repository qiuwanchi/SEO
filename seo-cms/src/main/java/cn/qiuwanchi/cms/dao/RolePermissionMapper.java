package cn.qiuwanchi.cms.dao;

import java.util.List;

import cn.qiuwanchi.cms.common.BaseMapper;
import cn.qiuwanchi.cms.entity.RolePermission;

/**
 * RolePermissionMapper继承基类
 */
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

	int insertBatchList(List<RolePermission> list);
}