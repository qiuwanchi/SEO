package com.qiuwanchi.seo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qiuwanchi.seo.dto.BannerDto;
import com.qiuwanchi.seo.entity.Banner;

import java.util.List;

public interface IBannerService extends IService<Banner> {

    List<BannerDto> getBannerList(String belong);
}
