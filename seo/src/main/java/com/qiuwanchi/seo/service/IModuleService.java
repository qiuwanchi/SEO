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
     * 获取置顶首页模块，及其下的项目(取size条数)
     * @param homePageDisplay 是否查询首页置顶的数据
     * @param belong 属于哪个模块
     * @param size 各类目下查询数据的条数
     * @return
     */
    List<ModuleDto> getIndexModuleDtoList(String homePageDisplay, String belong, Integer size);

    List<ModuleDto> getSimpleModuleDtoList(String belong);

    ModuleDto selectByModuleId(String moduleId);
}
