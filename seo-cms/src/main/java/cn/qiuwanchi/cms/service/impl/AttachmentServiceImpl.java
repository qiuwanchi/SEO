package cn.qiuwanchi.cms.service.impl;

import cn.qiuwanchi.cms.common.Constant;
import cn.qiuwanchi.cms.common.SearchEntity;
import cn.qiuwanchi.cms.dao.AttachmentMapper;
import cn.qiuwanchi.cms.entity.Attachment;
import cn.qiuwanchi.cms.entity.System;
import cn.qiuwanchi.cms.service.AttachmentService;
import cn.qiuwanchi.cms.service.SystemService;
import cn.qiuwanchi.cms.utils.FileConfiguration;
import cn.qiuwanchi.cms.utils.FileUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
@Slf4j
@Service
public class AttachmentServiceImpl implements AttachmentService {

	@Autowired
	private FileConfiguration fileConfiguration;

	@Autowired
	private AttachmentMapper attachmentMapper;

	@Autowired
	private SystemService systemService;

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
			this.copyFile(attachment);
		}
		return attachmentMapper.insertSelective(attachment);
	}

	private void copyFile(Attachment attachment){
		System system = systemService.getSystem();
		String uploadDir = system.getUploaddir();
		File file = new File(fileConfiguration.getResourceDir() + File.separator + uploadDir + File.separator + attachment.getFilepath());

		if(file.exists()){
			String fileType;
			if(attachment.getFiletype().startsWith("image")){
				fileType = "image";
			} else {
				fileType = "video";
			}

			File nginxFile = new File(Constant.NGINX_DIR + File.separator + fileType + File.separator + attachment.getFilepath().substring(9));
			if(!nginxFile.exists()){
				try {
					nginxFile.createNewFile();
				}catch (Exception e){
					log.error("创建文件失败!文件路径:{}", nginxFile.getAbsoluteFile(), e);
				}
				FileUtils.copy(file, nginxFile);
			}

		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int delete(String id) {
		Attachment attachment = this.attachmentMapper.selectByPrimaryKey(id);
		if(Objects.nonNull(attachment)){
			System system = systemService.getSystem();
			String uploadDir = system.getUploaddir();

			String filePath = fileConfiguration.getResourceDir() + File.separator + uploadDir + File.separator + attachment.getFilepath();
			File file = new File(filePath);
			// 如果文件存在就删掉
			if(file.exists()){
				file.delete();
			}

			String fileType;
			if(attachment.getFiletype().startsWith("image")){
				fileType = "image";
			} else {
				fileType = "video";
			}

			// 删除Nginx下的文件
			File nginxFile = new File(Constant.NGINX_DIR + File.separator + fileType + File.separator + attachment.getFilepath().substring(9));
			if(nginxFile.exists()){
				nginxFile.delete();
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

			this.copyFile(attachment);
		}
		this.attachmentMapper.updateByPrimaryKey(attachment);
	}

	@Override
	public List<Attachment> selectAll() {
		return this.attachmentMapper.selectAll();
	}
}
