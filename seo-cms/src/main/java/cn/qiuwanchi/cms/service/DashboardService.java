package cn.qiuwanchi.cms.service;

import java.util.Map;

public interface DashboardService {

	Map<String,Integer> statistics();

	String selectDBVersion();

}
