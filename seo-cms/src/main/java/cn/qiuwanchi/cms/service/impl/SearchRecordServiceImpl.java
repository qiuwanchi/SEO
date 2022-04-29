package cn.qiuwanchi.cms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qiuwanchi.cms.dao.SearchRecordMapper;
import cn.qiuwanchi.cms.entity.SearchRecord;
import cn.qiuwanchi.cms.service.SearchRecordService;

@Service
public class SearchRecordServiceImpl implements SearchRecordService {
	
	@Autowired
	private SearchRecordMapper searchRecordMapper;

	@Override
	public int add(SearchRecord sr) {
		return searchRecordMapper.insert(sr);
	}

}
