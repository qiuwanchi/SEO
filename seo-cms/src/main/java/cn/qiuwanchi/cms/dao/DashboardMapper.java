package cn.qiuwanchi.cms.dao;

import java.util.Map;

public interface DashboardMapper {

	Map<String, Integer> statistics();

	String selectDBVersion();

}
