package cn.qiuwanchi.cms.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.qiuwanchi.cms.common.BaseMapper;
import cn.qiuwanchi.cms.entity.Variable;

public interface VariableMapper extends BaseMapper<Variable> {

	List<Variable> queryListByPage(Map<String, Object> entity);

	Variable queryVariableByName(@Param("name") String name);
}