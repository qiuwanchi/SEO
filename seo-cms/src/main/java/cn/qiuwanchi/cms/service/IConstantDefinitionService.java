package cn.qiuwanchi.cms.service;

import cn.qiuwanchi.cms.entity.ConstantDefinition;

public interface IConstantDefinitionService {

    ConstantDefinition getByCode(String code);
}
