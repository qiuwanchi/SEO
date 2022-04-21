package com.qiuwanchi.seo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qiuwanchi.seo.dto.SeoVideoDto;
import com.qiuwanchi.seo.entity.SeoVideo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SeoVideoMapper extends BaseMapper<SeoVideo> {

    List<SeoVideoDto> getVideoListByBelongId(@Param("belongId")String belongId);

}
