package cn.qiuwanchi.cms.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import cn.qiuwanchi.cms.common.SearchEntity;
import cn.qiuwanchi.cms.entity.Menu;

public interface MenuService {

	PageInfo<Menu> queryListByPage(SearchEntity params);

	int add(Menu menu);

	Menu queryMenuById(String id);

	int update(Menu menu);

	int delete(String id);

	List<Menu> queryAll();

	List<Menu> queryListByUserId(String userId);

}
