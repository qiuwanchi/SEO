package cn.itechyou.cms.service.impl;

import cn.itechyou.cms.dao.ConstantDefinitionMapper;
import cn.itechyou.cms.entity.ConstantDefinition;
import cn.itechyou.cms.service.IConstantDefinitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConstantDefinitionServiceImpl implements IConstantDefinitionService {

    @Autowired
    private ConstantDefinitionMapper constantDefinitionMapper;

    @Override
    public ConstantDefinition getByCode(String code) {
        ConstantDefinition constantDefinition = new ConstantDefinition();
        constantDefinition.setCode(code);
        return this.constantDefinitionMapper.selectOne(constantDefinition);
    }
}
