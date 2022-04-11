package com.qiuwanchi.seo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiuwanchi.seo.dto.ModuleDto;
import com.qiuwanchi.seo.entity.Module;
import com.qiuwanchi.seo.mapper.ModuleMapper;
import com.qiuwanchi.seo.service.IBannerService;
import com.qiuwanchi.seo.service.IModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleServiceImpl extends ServiceImpl<ModuleMapper, Module> implements IModuleService {

    @Autowired
    private ModuleMapper moduleMapper;

    @Override
    public List<ModuleDto> getModuleList(String belong) {
        return this.moduleMapper.getModuleList(belong);
    }
}
