package cn.itechyou.cms.service;

import cn.itechyou.cms.entity.ConstantDefinition;

public interface IConstantDefinitionService {

    ConstantDefinition getByCode(String code);
}
