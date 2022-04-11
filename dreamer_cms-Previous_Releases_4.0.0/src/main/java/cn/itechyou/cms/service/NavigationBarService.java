package cn.itechyou.cms.service;

import cn.itechyou.cms.entity.NavigationBar;

public interface NavigationBarService {

    NavigationBar queryListByBelong(String belong);

    void add(NavigationBar navigationBar);

    void update(NavigationBar navigationBar);
}
