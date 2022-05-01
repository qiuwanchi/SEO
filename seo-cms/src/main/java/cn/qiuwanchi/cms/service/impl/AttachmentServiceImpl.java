package cn.qiuwanchi.cms.service.impl;

import cn.qiuwanchi.cms.common.SearchEntity;
import cn.qiuwanchi.cms.dao.AttachmentMapper;
import cn.qiuwanchi.cms.entity.Attachment;
import cn.qiuwanchi.cms.service.AttachmentService;
import cn.qiuwanchi.cms.utils.FileConfiguration;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.List;
import java.util.Objects;

/**
 * 附件管理
 * @author Wangjn
 *
 */
@Service
public class AttachmentServiceImpl implements AttachmentService {

	@Autowired
	private FileConfiguration fileConfiguration;

	@Autowired
	private AttachmentMapper attachmentMapper;

	@Override
	public PageInfo<Attachment> queryListByPage(SearchEntity params) {
		if(params.getPageNum() == null || params.getPageNum() == 0) {
			params.setPageNum(1);
		}
		if(params.getPageSize() == null || params.getPageSize() == 0) {
			params.setPageSize(10);
		}
		PageHelper.startPage(params.getPageNum(), params.getPageSize());
		List<Attachment> list = attachmentMapper.queryListByPage(params.getEntity());
		PageInfo<Attachment> page = new PageInfo<Attachment>(list);
		return page;
	}

	@Override
	public int save(Attachment attachment) {
		if(!StringUtils.isEmpty(attachment.getFilepath())){
			String fileId = attachment.getFilepath();
			fileId = fileId.substring(9);
			fileId = fileId.substring(0, fileId.indexOf("."));
			attachment.setFileId(fileId);
		}
		return attachmentMapper.insertSelective(attachment);
	}

	@Transactional
	@Override
	public int delete(String id) {
		Attachment attachment = this.attachmentMapper.selectByPrimaryKey(id);
		if(Objects.nonNull(attachment)){
			String filePath = fileConfiguration.getResourceDir() + "uploads/" + attachment.getFilepath();
			File file = new File(filePath);
			// 如果文件存在就删掉
			if(file.exists()){
				file.delete();
			}
		}
		return attachmentMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Attachment queryAttachmentById(String id) {
		return attachmentMapper.selectByPrimaryKey(id);
	}

	@Override
	public Attachment queryAttachmentByCode(String key) {
		return attachmentMapper.selectByCode(key);
	}

	@Override
	public Attachment getByFileId(String fileId) {
		Attachment attachment = new Attachment();
		attachment.setFileId(fileId);
		return this.attachmentMapper.selectOne(attachment);
	}

	@Override
	public void update(Attachment attachment) {
		if(!StringUtils.isEmpty(attachment.getFilepath())){
			String fileId = attachment.getFilepath();
			fileId = fileId.substring(9);
			fileId = fileId.substring(0, fileId.indexOf("."));
			attachment.setFileId(fileId);
		}
		this.attachmentMapper.updateByPrimaryKey(attachment);
	}
}
