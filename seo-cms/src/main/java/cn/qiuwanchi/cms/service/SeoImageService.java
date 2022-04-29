package cn.qiuwanchi.cms.service;

import cn.qiuwanchi.cms.entity.SeoImage;

public interface SeoImageService {

    SeoImage getById(String id);

    void save(SeoImage seoImage);

    void update(SeoImage seoImage);
}
