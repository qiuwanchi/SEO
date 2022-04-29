package cn.qiuwanchi.cms.taglib;

import cn.qiuwanchi.cms.exception.CmsException;

public interface IParse {
	public String parse(String html) throws CmsException;
	
	public String parse(String html, String params) throws CmsException;
}
