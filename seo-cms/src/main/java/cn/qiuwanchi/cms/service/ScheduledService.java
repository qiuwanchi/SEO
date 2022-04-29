package cn.qiuwanchi.cms.service;

import com.github.pagehelper.PageInfo;

import cn.qiuwanchi.cms.common.SearchEntity;
import cn.qiuwanchi.cms.entity.Scheduled;

public interface ScheduledService {

	PageInfo<Scheduled> queryListByPage(SearchEntity params);

	int add(Scheduled scheduled);
	
	int update(Scheduled scheduled);

	Scheduled queryScheduledById(String id);

	int delete(String id);

}
