package com.qiuwanchi.seo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qiuwanchi.seo.dto.BannerDto;
import com.qiuwanchi.seo.entity.Banner;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BannerMapper extends BaseMapper<Banner> {

    /**
     *
     * @param belong
     * @return
     */

    List<BannerDto> getBannerList(@Param("belong") String belong);
}
