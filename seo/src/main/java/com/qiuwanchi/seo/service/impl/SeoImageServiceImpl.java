package com.qiuwanchi.seo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiuwanchi.seo.dto.SeoImageDto;
import com.qiuwanchi.seo.entity.SeoImage;
import com.qiuwanchi.seo.mapper.SeoImageMapper;
import com.qiuwanchi.seo.service.ISeoImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeoImageServiceImpl extends ServiceImpl<SeoImageMapper, SeoImage> implements ISeoImageService {

    @Autowired
    private SeoImageMapper seoImageMapper;
    @Override
    public SeoImageDto selectById(String seoImageId) {
        return this.seoImageMapper.selectAllById(seoImageId);
    }
}
