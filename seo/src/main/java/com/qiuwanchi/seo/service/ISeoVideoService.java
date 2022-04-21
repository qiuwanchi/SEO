package com.qiuwanchi.seo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qiuwanchi.seo.dto.SeoVideoDto;
import com.qiuwanchi.seo.entity.SeoVideo;

import java.util.List;

public interface ISeoVideoService extends IService<SeoVideo> {

    List<SeoVideoDto> getVideoListByBelongId(String belongId);
}
