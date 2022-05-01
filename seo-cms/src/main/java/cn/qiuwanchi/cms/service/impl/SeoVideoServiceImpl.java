package cn.qiuwanchi.cms.service.impl;

import cn.qiuwanchi.cms.dao.SeoVideoMapper;
import cn.qiuwanchi.cms.entity.SeoVideo;
import cn.qiuwanchi.cms.service.AttachmentService;
import cn.qiuwanchi.cms.service.ISeoVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Service
public class SeoVideoServiceImpl implements ISeoVideoService {

    @Autowired
    private SeoVideoMapper seoVideoMapper;

    @Autowired
    private AttachmentService attachmentService;

    @Override
    public void save(SeoVideo seoVideo) {
        this.seoVideoMapper.insert(seoVideo);
    }

    @Transactional
    @Override
    public void delete(String id) {
        SeoVideo seoVideo = new SeoVideo();
        seoVideo.setId(id);

        SeoVideo seoVideoDb = this.seoVideoMapper.selectOne(seoVideo);
        if(Objects.nonNull(seoVideoDb) && !StringUtils.isEmpty(seoVideoDb.getAttachmentId())){
            this.attachmentService.delete(seoVideoDb.getAttachmentId());
        }
        this.seoVideoMapper.delete(seoVideo);
    }

    @Override
    public void deleteByBelongId(String belongId) {
        SeoVideo seoVideo = new SeoVideo();
        seoVideo.setBelongId(belongId);
        List<SeoVideo> seoVideoList = this.seoVideoMapper.select(seoVideo);

        for (SeoVideo t : seoVideoList){
            if(!StringUtils.isEmpty(t.getAttachmentId())){
                this.attachmentService.delete(t.getAttachmentId());
            }
        }

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
