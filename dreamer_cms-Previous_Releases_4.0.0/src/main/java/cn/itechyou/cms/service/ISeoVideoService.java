package cn.itechyou.cms.service;

import cn.itechyou.cms.entity.SeoVideo;

import java.util.List;

public interface ISeoVideoService {

    void save(SeoVideo seoVideo);

    void delete(String id);

    SeoVideo selectById(String id);

    List<SeoVideo> selectByBelongId(String belongId);

}
