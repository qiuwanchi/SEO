package cn.qiuwanchi.cms.service;

import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpSession;

import cn.qiuwanchi.cms.annotation.Log;
import cn.qiuwanchi.cms.common.SearchEntity;
import cn.qiuwanchi.cms.entity.SysLogger;
import cn.qiuwanchi.cms.utils.RequestEntity;

public interface LoggerService {
	void log(Method method, Log log, RequestEntity request, HttpSession session);

	void info(String module, String msg);

	void error(String module, String msg);

	int insert(SysLogger logEntity);
	
	List<SysLogger> getList(SearchEntity searchEntity);
}
