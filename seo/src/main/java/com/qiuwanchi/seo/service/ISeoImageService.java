package com.qiuwanchi.seo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qiuwanchi.seo.dto.SeoImageDto;
import com.qiuwanchi.seo.entity.SeoImage;

public interface ISeoImageService extends IService<SeoImage> {

    SeoImageDto selectById(String seoImageId);
}
