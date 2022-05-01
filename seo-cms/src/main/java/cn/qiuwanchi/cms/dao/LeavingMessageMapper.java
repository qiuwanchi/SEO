package cn.qiuwanchi.cms.dao;

import cn.qiuwanchi.cms.common.BaseMapper;
import cn.qiuwanchi.cms.entity.LeavingMessage;

import java.util.List;

public interface LeavingMessageMapper extends BaseMapper<LeavingMessage> {

    List<LeavingMessage> listByPage();

}