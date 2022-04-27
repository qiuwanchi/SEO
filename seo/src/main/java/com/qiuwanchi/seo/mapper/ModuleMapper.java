package com.qiuwanchi.seo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qiuwanchi.seo.dto.ModuleDto;
import com.qiuwanchi.seo.entity.Module;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ModuleMapper extends BaseMapper<Module> {

    /**
     * 查询是否置顶的模块列表
     * @param homePageDisplay 是否置顶
     * @param belong 哪个模块
     * @return
     */
    List<ModuleDto> getModuleList(@Param("homePageDisplay")String homePageDisplay, @Param("belong")String belong);

    ModuleDto selectByModuleId(@Param("moduleId")String moduleId);

}
