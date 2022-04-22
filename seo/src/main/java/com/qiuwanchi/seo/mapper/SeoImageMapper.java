package com.qiuwanchi.seo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qiuwanchi.seo.dto.SeoImageDto;
import com.qiuwanchi.seo.entity.SeoImage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SeoImageMapper extends BaseMapper<SeoImage> {
    SeoImageDto selectAllById(@Param("seoImageId")String seoImageId);
}
