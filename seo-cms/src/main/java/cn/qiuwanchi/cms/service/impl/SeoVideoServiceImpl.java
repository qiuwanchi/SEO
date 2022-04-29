package cn.qiuwanchi.cms.service.impl;

import cn.qiuwanchi.cms.dao.SeoVideoMapper;
import cn.qiuwanchi.cms.entity.SeoVideo;
import cn.qiuwanchi.cms.service.ISeoVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeoVideoServiceImpl implements ISeoVideoService {

    @Autowired
    private SeoVideoMapper seoVideoMapper;

    @Override
    public void save(SeoVideo seoVideo) {
        this.seoVideoMapper.insert(seoVideo);
    }

    @Override
    public void delete(String id) {
        SeoVideo seoVideo = new SeoVideo();
        seoVideo.setId(id);
        this.seoVideoMapper.delete(seoVideo);
    }

    @Override
    public SeoVideo selectById(String id) {
        SeoVideo seoVideo = new SeoVideo();
        seoVideo.setId(id);
        return this.seoVideoMapper.selectOne(seoVideo);
    }

    @Override
    public List<SeoVideo> selectByBelongId(String belongId) {
        SeoVideo seoVideo = new SeoVideo();
        seoVideo.setBelongId(belongId);
        return this.seoVideoMapper.select(seoVideo);
    }
}
