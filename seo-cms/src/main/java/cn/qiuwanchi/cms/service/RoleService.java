package cn.qiuwanchi.cms.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import cn.qiuwanchi.cms.common.SearchEntity;
import cn.qiuwanchi.cms.entity.Role;
import cn.qiuwanchi.cms.entity.RolePermission;
import cn.qiuwanchi.cms.vo.PermissionVo;

public interface RoleService {

	PageInfo<Role> queryListByPage(SearchEntity params);

	int add(Role role);

	Role queryRoleById(String id);

	int update(Role role);

	int delete(String id);

	List<Role> queryAll();

	List<PermissionVo> queryPermissionsByRoleId(String id);

	int grant(String roleId, List<RolePermission> list);

	List<String> queryRoleCodesByUserId(String userId);

	List<String> queryPermissionCodesByUserId(String userId);

	List<String> queryAllPermissionCodes();

}
