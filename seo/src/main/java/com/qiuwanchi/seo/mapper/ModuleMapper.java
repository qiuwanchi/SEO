package com.qiuwanchi.seo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qiuwanchi.seo.dto.ModuleDto;
import com.qiuwanchi.seo.entity.Module;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ModuleMapper extends BaseMapper<Module> {

    List<ModuleDto> getModuleList(@Param("belong")String belong);

}
