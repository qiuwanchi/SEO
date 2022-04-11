package com.qiuwanchi.seo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiuwanchi.seo.entity.LeavingMessage;
import com.qiuwanchi.seo.mapper.LeavingMessageMapper;
import com.qiuwanchi.seo.service.ILeavingMessageService;
import org.springframework.stereotype.Service;

@Service
public class LeavingMessageServiceImpl extends ServiceImpl<LeavingMessageMapper, LeavingMessage> implements ILeavingMessageService {

}
