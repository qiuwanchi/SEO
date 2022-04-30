package cn.qiuwanchi.cms.service;

import cn.qiuwanchi.cms.entity.SeoVideo;

import java.util.List;

public interface ISeoVideoService {

    void save(SeoVideo seoVideo);

    void delete(String id);

    SeoVideo selectById(String id);

    List<SeoVideo> selectByBelongId(String belongId);

}