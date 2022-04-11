package com.qiuwanchi.seo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qiuwanchi.seo.entity.Attachment;

public interface IAttachmentService extends IService<Attachment> {
    Attachment getByFileId(String fileId);
}
