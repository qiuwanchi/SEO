package com.qiuwanchi.seo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiuwanchi.seo.dto.BannerDto;
import com.qiuwanchi.seo.entity.Banner;
import com.qiuwanchi.seo.mapper.BannerMapper;
import com.qiuwanchi.seo.service.IBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements IBannerService {

    @Autowired
    private BannerMapper bannerMapper;

    @Override
    public List<BannerDto> getBannerList(String belong) {
        return this.bannerMapper.getBannerList(belong);
    }
}
