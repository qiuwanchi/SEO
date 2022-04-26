package cn.itechyou.cms.service.impl;

import cn.itechyou.cms.dao.BannerMapper;
import cn.itechyou.cms.entity.Banner;
import cn.itechyou.cms.service.IBannerService;
import cn.itechyou.cms.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class BannerServiceImpl implements IBannerService {

    @Autowired
    private BannerMapper bannerMapper;

    @Override
    public List<Banner> list(String belong) {
        Banner banner = new Banner();
        List<Banner> list = this.bannerMapper.select(banner);

        Collections.sort(list, new Comparator<Banner>() {
            @Override
            public int compare(Banner o1, Banner o2) {
                return o2.getUpdateTime().compareTo(o1.getUpdateTime());
            }
        });

        return list;
    }

    @Override
    public void save(Banner banner) {
        if(StringUtils.isEmpty(banner.getId())){
            banner.setId(UUIDUtils.getPrimaryKey());
            this.bannerMapper.insert(banner);
        }else {
            this.bannerMapper.updateByPrimaryKey(banner);
        }

    }

    @Override
    public Banner getById(String id) {
        return this.bannerMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteById(String id) {
        this.bannerMapper.deleteByPrimaryKey(id);
    }

}
