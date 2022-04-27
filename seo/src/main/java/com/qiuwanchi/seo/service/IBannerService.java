package com.qiuwanchi.seo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qiuwanchi.seo.dto.BannerDto;
import com.qiuwanchi.seo.entity.Banner;

public interface IBannerService extends IService<Banner> {

    BannerDto selectById(String bannerId);
}
