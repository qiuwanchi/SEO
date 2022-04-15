package com.qiuwanchi.seo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qiuwanchi.seo.dto.BannerDto;
import com.qiuwanchi.seo.dto.ModuleDto;
import com.qiuwanchi.seo.entity.Module;

import java.util.List;

public interface IModuleService extends IService<Module> {

    List<ModuleDto> getModuleDtoList(String belong);
}
