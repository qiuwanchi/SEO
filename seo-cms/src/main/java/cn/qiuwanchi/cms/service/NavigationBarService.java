package cn.qiuwanchi.cms.service;

import cn.qiuwanchi.cms.entity.NavigationBar;

public interface NavigationBarService {

    NavigationBar queryListByBelong(String belong);

    void add(NavigationBar navigationBar);

    void update(NavigationBar navigationBar);
}
