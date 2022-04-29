package cn.qiuwanchi.cms.service;

import com.github.pagehelper.PageInfo;

import cn.qiuwanchi.cms.common.SearchEntity;
import cn.qiuwanchi.cms.entity.Variable;

public interface VariableService {

	PageInfo<Variable> queryListByPage(SearchEntity params);

	void add(Variable variable);

	Variable queryVariableId(String id);

	void updateVariable(Variable variable);

	void deleteVariable(String id);

	Variable queryVariableByName(String name);

}
