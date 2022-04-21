package com.qiuwanchi.seo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiuwanchi.seo.dto.SeoVideoDto;
import com.qiuwanchi.seo.entity.SeoVideo;
import com.qiuwanchi.seo.mapper.SeoVideoMapper;
import com.qiuwanchi.seo.service.ISeoVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeoVideoServiceImpl extends ServiceImpl<SeoVideoMapper, SeoVideo> implements ISeoVideoService {

    @Autowired
    private SeoVideoMapper seoVideoMapper;

    @Override
    public List<SeoVideoDto> getVideoListByBelongId(String belongId) {
        return this.seoVideoMapper.getVideoListByBelongId(belongId);
    }
}
