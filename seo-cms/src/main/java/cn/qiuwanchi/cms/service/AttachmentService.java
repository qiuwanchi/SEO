package cn.qiuwanchi.cms.service;

import cn.qiuwanchi.cms.common.SearchEntity;
import cn.qiuwanchi.cms.entity.Attachment;
import com.github.pagehelper.PageInfo;

import java.util.List;

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

	List<Attachment> selectAll();
}
