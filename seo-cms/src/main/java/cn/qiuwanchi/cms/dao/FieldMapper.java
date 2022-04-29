package cn.qiuwanchi.cms.dao;

import java.util.List;

import cn.qiuwanchi.cms.common.BaseMapper;
import cn.qiuwanchi.cms.entity.Field;

public interface FieldMapper extends BaseMapper<Field> {

	List<Field> queryFieldByFormId(String id);
}