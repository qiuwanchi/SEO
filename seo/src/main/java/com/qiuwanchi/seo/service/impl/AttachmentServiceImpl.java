package com.qiuwanchi.seo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiuwanchi.seo.entity.Attachment;
import com.qiuwanchi.seo.mapper.AttachmentMapper;
import com.qiuwanchi.seo.service.IAttachmentService;
import org.springframework.stereotype.Service;

@Service
public class AttachmentServiceImpl extends ServiceImpl<AttachmentMapper, Attachment> implements IAttachmentService {
    @Override
    public Attachment getByFileId(String fileId) {
        QueryWrapper<Attachment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Attachment.FILE_ID, fileId);
        return this.getOne(queryWrapper);
    }
}
