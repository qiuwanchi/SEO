package cn.qiuwanchi.cms.dao;

import java.util.List;
import java.util.Map;

import cn.qiuwanchi.cms.common.BaseMapper;
import cn.qiuwanchi.cms.entity.Permission;

/**
 * PermissionMapper继承基类
 */
public interface PermissionMapper extends BaseMapper<Permission> {

	List<Permission> queryListByPage(Map<String, Object> entity);
}