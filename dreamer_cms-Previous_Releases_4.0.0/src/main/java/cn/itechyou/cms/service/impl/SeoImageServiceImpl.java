package cn.itechyou.cms.service.impl;

import cn.itechyou.cms.dao.SeoImageMapper;
import cn.itechyou.cms.entity.SeoImage;
import cn.itechyou.cms.service.SeoImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeoImageServiceImpl implements SeoImageService {

    @Autowired
    private SeoImageMapper seoImageMapper;


    @Override
    public SeoImage getById(String id) {
        return this.seoImageMapper.selectByPrimaryKey(id);
    }

    @Override
    public void save(SeoImage seoImage) {
        this.seoImageMapper.insert(seoImage);
    }

    @Override
    public void update(SeoImage seoImage) {
        this.seoImageMapper.updateByPrimaryKey(seoImage);
    }
}
