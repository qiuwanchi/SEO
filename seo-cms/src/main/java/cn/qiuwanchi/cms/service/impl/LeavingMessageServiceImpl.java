package cn.qiuwanchi.cms.service.impl;

import cn.qiuwanchi.cms.dao.LeavingMessageMapper;
import cn.qiuwanchi.cms.entity.LeavingMessage;
import cn.qiuwanchi.cms.service.ILeavingMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeavingMessageServiceImpl implements ILeavingMessageService {

    @Autowired
    private LeavingMessageMapper leavingMessageMapper;


    @Override
    public List<LeavingMessage> getList() {
        return this.leavingMessageMapper.listByPage();
    }
}
