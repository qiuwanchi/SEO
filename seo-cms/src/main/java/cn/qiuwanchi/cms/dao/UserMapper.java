package cn.qiuwanchi.cms.dao;

import java.util.List;
import java.util.Map;

import cn.qiuwanchi.cms.common.BaseMapper;
import cn.qiuwanchi.cms.entity.User;

public interface UserMapper extends BaseMapper<User> {

    List<User> listByPage(Map<String, Object> entity);

	User getByUserName(String username);
}