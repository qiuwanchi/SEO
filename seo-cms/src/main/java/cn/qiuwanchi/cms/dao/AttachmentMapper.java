package cn.qiuwanchi.cms.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.qiuwanchi.cms.common.BaseMapper;
import cn.qiuwanchi.cms.entity.Attachment;

public interface AttachmentMapper extends BaseMapper<Attachment> {
    
    List<Attachment> queryListByPage(Map<String, Object> entity);

	Attachment selectByCode(@Param("code") String key);
}