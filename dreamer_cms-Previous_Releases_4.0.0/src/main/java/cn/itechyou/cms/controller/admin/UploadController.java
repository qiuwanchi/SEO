package cn.itechyou.cms.controller.admin;

import cn.itechyou.cms.common.BaseController;
import cn.itechyou.cms.common.ExceptionEnum;
import cn.itechyou.cms.common.ResponseResult;
import cn.itechyou.cms.common.StateCodeEnum;
import cn.itechyou.cms.entity.Attachment;
import cn.itechyou.cms.entity.System;
import cn.itechyou.cms.exception.AdminGeneralException;
import cn.itechyou.cms.security.token.TokenManager;
import cn.itechyou.cms.service.AttachmentService;
import cn.itechyou.cms.service.SystemService;
import cn.itechyou.cms.utils.DateUtils;
import cn.itechyou.cms.utils.FileConfiguration;
import cn.itechyou.cms.utils.UUIDUtils;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

/**
 * 上传控制器
 * @author Wangjn
 *
 */
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
			String newFileName = UUIDUtils.getPrimaryKey() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			String absolutePath = directory.getAbsolutePath(); //获取绝对路径
			File uploadpath = new File(absolutePath + File.separator + newFileName);
			file.transferTo(uploadpath);
			result.put("filepath", currentDate+File.separator + newFileName);
			result.put("name", newFileName);
			result.put("originalFilename", file.getOriginalFilename());
			result.put("filesize", file.getSize());
			result.put("filetype", file.getContentType());
			result.put("url", system.getWebsite() + "/" + uploadDir + "/" + currentDate + "/" + newFileName);
			respResult = ResponseResult.Factory.newInstance(Boolean.TRUE,
					StateCodeEnum.HTTP_SUCCESS.getCode(), result,
					StateCodeEnum.HTTP_SUCCESS.getDescription());
		} catch (Exception e) {
			respResult = ResponseResult.Factory.newInstance(Boolean.TRUE,
					StateCodeEnum.HTTP_ERROR.getCode(), null,
					StateCodeEnum.HTTP_ERROR.getDescription());
			e.printStackTrace();
		}
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
			String newFileName = UUIDUtils.getPrimaryKey() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			String absolutePath = directory.getAbsolutePath(); //获取绝对路径
			File uploadpath = new File(absolutePath + File.separator + newFileName);
			file.transferTo(uploadpath);
			result.put("state","SUCCESS");
			result.put("filepath", currentDate+File.separator + newFileName);
			result.put("name", newFileName);
			result.put("originalFilename", file.getOriginalFilename());
			result.put("original", file.getOriginalFilename());
			result.put("filesize", file.getSize());
			result.put("filetype", file.getContentType());
			result.put("url", system.getWebsite() + "/image/" + newFileName);

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
			e.printStackTrace();
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
