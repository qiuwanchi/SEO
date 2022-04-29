package cn.qiuwanchi.cms.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import cn.qiuwanchi.cms.common.SearchEntity;
import cn.qiuwanchi.cms.entity.Permission;

public interface PermissionService {

	PageInfo<Permission> queryListByPage(SearchEntity params);

	int add(Permission permission);

	Permission queryMenuById(String id);

	int update(Permission permission);

	int delete(String id);

	List<Permission> queryAll();

}
