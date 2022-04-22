package cn.itechyou.cms.service;

import cn.itechyou.cms.entity.SeoImage;

public interface SeoImageService {

    SeoImage getById(String id);

    void save(SeoImage seoImage);

    void update(SeoImage seoImage);
}
