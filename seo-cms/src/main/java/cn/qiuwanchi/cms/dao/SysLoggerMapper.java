package cn.qiuwanchi.cms.dao;

import java.util.List;
import java.util.Map;

import cn.qiuwanchi.cms.common.BaseMapper;
import cn.qiuwanchi.cms.entity.SysLogger;

public interface SysLoggerMapper extends BaseMapper<SysLogger> {

	List<SysLogger> queryListByPage(Map<String, Object> entity);
}