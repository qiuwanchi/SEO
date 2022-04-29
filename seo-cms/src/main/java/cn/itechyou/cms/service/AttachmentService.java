package cn.itechyou.cms.service;

import cn.itechyou.cms.common.SearchEntity;
import cn.itechyou.cms.entity.Attachment;
import com.github.pagehelper.PageInfo;

/**
 * 附件管理
 * @author Wangjn
 *
 */
public interface AttachmentService {

	PageInfo<Attachment> queryListByPage(SearchEntity params);

	int save(Attachment attachment);

	int delete(String id);

	Attachment queryAttachmentById(String id);

	Attachment queryAttachmentByCode(String key);

    Attachment getByFileId(String fileId);

    void update(Attachment attachment);
}
