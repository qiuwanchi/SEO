package cn.qiuwanchi.cms.service;

import cn.qiuwanchi.cms.entity.Banner;

import java.util.List;

public interface IBannerService {

    List<Banner> list(String belong);

    void save(Banner banner);

    Banner getById(String id);

    void deleteById(String id);

}
