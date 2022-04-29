package cn.itechyou.cms.service.impl;

import cn.itechyou.cms.dao.NavigationBarMapper;
import cn.itechyou.cms.entity.NavigationBar;
import cn.itechyou.cms.service.NavigationBarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NavigationBarServiceImpl implements NavigationBarService {

    @Autowired
    private NavigationBarMapper navigationBarMapper;

    @Override
    public NavigationBar queryListByBelong(String belong) {
        List<NavigationBar> list = this.navigationBarMapper.queryListByBelong(belong);

        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public void add(NavigationBar navigationBar) {
        this.navigationBarMapper.insert(navigationBar);
    }

    @Override
    public void update(NavigationBar navigationBar) {
        this.navigationBarMapper.updateByPrimaryKey(navigationBar);
    }

}
