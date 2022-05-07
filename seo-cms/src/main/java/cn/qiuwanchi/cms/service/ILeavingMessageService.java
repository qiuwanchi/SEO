package cn.qiuwanchi.cms.service;

import cn.qiuwanchi.cms.entity.LeavingMessage;

import java.util.List;

public interface ILeavingMessageService {

    List<LeavingMessage> getList();

    void deleteById(String id);
}
