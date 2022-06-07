package cn.qiuwanchi.cms.controller.admin;

import cn.qiuwanchi.cms.common.BaseController;
import cn.qiuwanchi.cms.common.ResponseResult;
import cn.qiuwanchi.cms.common.StateCodeEnum;
import cn.qiuwanchi.cms.entity.Attachment;
import cn.qiuwanchi.cms.entity.System;
import cn.qiuwanchi.cms.security.token.TokenManager;
import cn.qiuwanchi.cms.service.AttachmentService;
import cn.qiuwanchi.cms.service.SystemService;
import cn.qiuwanchi.cms.utils.DateUtils;
import cn.qiuwanchi.cms.utils.FileConfiguration;
import cn.qiuwanchi.cms.utils.UUIDUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Date;

/**
 * 上传控制器
 * @author Wangjn
 *
 */
@Slf4j
@Controller
@RequestMapping("/upload")
public class UploadController extends BaseController{
	
	@Autowired
	private FileConfiguration fileConfiguration;
	@Autowired
	private SystemService systemService;
	@Autowired
	private AttachmentService attachmentService;

	/**
	 *   文件上传
	 * @param file
	 */
	@RequestMapping("/uploadFile")
	public void upload(@RequestParam("file") MultipartFile file) {
		ResponseResult respResult = null;
		JSONObject result = new JSONObject();
		try {
			if(file.isEmpty()) {
				return;
			}
			String rootPath = fileConfiguration.getResourceDir();
			String currentDate = DateUtils.getCurrentDate("yyyyMMdd");
			System system = systemService.getSystem();
			String uploadDir = system.getUploaddir();

			File directory  = new File(rootPath + File.separator + uploadDir + File.separator + currentDate);
			if(!directory.exists()){
				directory.mkdirs();
			}

			String fileExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			String newFileName = UUIDUtils.getPrimaryKey() + fileExtension;

			// 目录绝对路径
			String dirAbsolutePath = directory.getAbsolutePath();

			File uploadFile = new File(dirAbsolutePath + File.separator + newFileName);
			file.transferTo(uploadFile);

			result.put("filepath", currentDate + File.separator + newFileName);
			result.put("name", newFileName);
			result.put("originalFilename", file.getOriginalFilename());
			result.put("filesize", file.getSize());
			result.put("filetype", file.getContentType());
			result.put("url", system.getWebsite() + "/" + uploadDir + "/" + currentDate + "/" + newFileName);
			log.info("uploadFile 上传信息result：{}", JSON.toJSONString(result));
			respResult = ResponseResult.Factory.newInstance(Boolean.TRUE,
					StateCodeEnum.HTTP_SUCCESS.getCode(), result,
					StateCodeEnum.HTTP_SUCCESS.getDescription());
		} catch (Exception e) {
			respResult = ResponseResult.Factory.newInstance(Boolean.TRUE,
					StateCodeEnum.HTTP_ERROR.getCode(), null,
					StateCodeEnum.HTTP_ERROR.getDescription());
			e.printStackTrace();
		}

		log.info("uploadFile 上传信息respResult：{}", JSON.toJSONString(respResult));
		this.outJson(respResult);
	}

	public JSONObject ueditorUpload(MultipartFile file){
		JSONObject result = new JSONObject();
		try {
			String rootPath = fileConfiguration.getResourceDir();
			String currentDate = DateUtils.getCurrentDate("yyyyMMdd");
			System system = systemService.getSystem();
			String uploadDir = system.getUploaddir();
			File directory  = new File(rootPath + File.separator + uploadDir + File.separator + currentDate);
			if(!directory.exists()){
				directory.mkdirs();
			}

			String fileExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			String newFileName = UUIDUtils.getPrimaryKey() + fileExtension;

			// 目录绝对路径
			String dirAbsolutePath = directory.getAbsolutePath();

			File uploadFile = new File(dirAbsolutePath + File.separator + newFileName);
			file.transferTo(uploadFile);
			result.put("state","SUCCESS");
			result.put("filepath", currentDate+File.separator + newFileName);
			result.put("name", newFileName);
			result.put("originalFilename", file.getOriginalFilename());
			result.put("original", file.getOriginalFilename());
			result.put("filesize", file.getSize());
			result.put("filetype", file.getContentType());

			String fileType;
			if(file.getContentType().startsWith("image")){
				fileType = "image";
			} else {
				fileType = "video";
			}
			result.put("url", system.getWebsite() + File.separator + fileType + File.separator + newFileName);

			log.info("ueditorUpload 上传信息result：{}", JSON.toJSONString(result));

			Attachment attachment = new Attachment();
			attachment.setFilename(file.getOriginalFilename());
			attachment.setFilepath(result.getString("filepath"));
			attachment.setFilesize((int)file.getSize());
			attachment.setFiletype(result.getString("filetype"));
			attachment.setId(UUIDUtils.getPrimaryKey());
			attachment.setCode(UUIDUtils.getCharAndNumr(8));
			attachment.setCreateBy(TokenManager.getUserId());
			attachment.setCreateTime(new Date());
			attachment.setUpdateBy(TokenManager.getUserId());
			attachment.setUpdateTime(new Date());
			this.attachmentService.save(attachment);

		} catch (Exception e) {
			log.info("ueditorUpload 上传文件失败!", e);
		}
		return result;
	}
	
	@RequestMapping("uploadMarkDown")
	@ResponseBody
	public void editormdPic(@RequestParam(value = "editormd-image-file", required = true) MultipartFile file, HttpServletRequest request,HttpServletResponse response) throws Exception{
		JSONObject result = new JSONObject();
		try {
			if(file.isEmpty()) {
				return;
			}
			String rootPath = fileConfiguration.getResourceDir();
			String currentDate = DateUtils.getCurrentDate("yyyyMMdd");
			System system = systemService.getSystem();
			String uploadDir = system.getUploaddir();
			File directory  = new File(rootPath + File.separator + uploadDir + File.separator + currentDate); 
			if(!directory.exists()){
				directory.mkdirs();
			}
			String newFileName = UUIDUtils.getPrimaryKey() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			String absolutePath = directory.getAbsolutePath(); //获取绝对路径
			File uploadpath = new File(absolutePath + File.separator + newFileName);
			file.transferTo(uploadpath);
			result.put("message", "上传成功");
			result.put("success", 1);
			result.put("url", system.getWebsite() + File.separator + uploadDir + File.separator + currentDate + File.separator + newFileName);
		} catch (Exception e) {
			result.put("message", "上传失败");
			result.put("success", 0);
			result.put("url", "");
			e.printStackTrace();
		}
		this.outJson(result);
	}

}
