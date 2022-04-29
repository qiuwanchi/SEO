package cn.qiuwanchi.cms.dao;

import java.util.List;
import java.util.Map;

import cn.qiuwanchi.cms.common.BaseMapper;
import cn.qiuwanchi.cms.entity.Role;
import cn.qiuwanchi.cms.vo.PermissionVo;

/**
 * RoleMapper继承基类
 */
public interface RoleMapper extends BaseMapper<Role> {

	List<Role> queryListByPage(Map<String, Object> entity);

	List<PermissionVo> selectPermissionsByRoleId(String roleId);

	List<String> selectRoleCodesByUserId(String userId);

	List<String> selectPermissionCodesByUserId(String userId);

	List<String> selectAllPermissionCodes();
}