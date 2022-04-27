package com.qiuwanchi.seo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qiuwanchi.seo.dto.BannerDto;
import com.qiuwanchi.seo.dto.ModuleDto;
import com.qiuwanchi.seo.entity.Module;

import java.util.List;

public interface IModuleService extends IService<Module> {

    List<ModuleDto> getModuleDtoList(String homePageDisplay,String belong);

    List<ModuleDto> getModuleDtoList(String belong);

    /**
     * 获取首页置顶的相关数据(模块与项目)
     * @param homePageDisplay
     * @param belong
     * @param size
     * @return
     */
    List<ModuleDto> getIndexTopModuleDtoList(String homePageDisplay, String belong, Integer size);

    /**
     * 获取简单的模块列表(只包含基本信息)
     * @param belong
     * @return
     */
    List<ModuleDto> getSimpleModuleDtoList(String belong);

    ModuleDto selectByModuleId(String moduleId);
}
