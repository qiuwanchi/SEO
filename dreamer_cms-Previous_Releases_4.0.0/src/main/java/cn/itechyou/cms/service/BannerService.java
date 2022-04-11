package cn.itechyou.cms.service;

import cn.itechyou.cms.entity.Banner;

import java.util.List;

public interface BannerService {

    List<Banner> list(String belong);

    void save(Banner banner);

    Banner getById(String id);

    void deleteById(String id);

}
