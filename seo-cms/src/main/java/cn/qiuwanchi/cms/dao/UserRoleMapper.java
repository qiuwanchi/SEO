package cn.qiuwanchi.cms.dao;

import java.util.List;

import cn.qiuwanchi.cms.common.BaseMapper;
import cn.qiuwanchi.cms.entity.UserRole;

/**
 * UserRoleMapper继承基类
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {
	int insertBatchList(List<UserRole> userRoles);
}