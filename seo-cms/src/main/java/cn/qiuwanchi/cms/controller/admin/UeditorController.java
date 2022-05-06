package cn.qiuwanchi.cms.controller.admin;

import cn.qiuwanchi.cms.SeoCmsApplication;
import cn.qiuwanchi.cms.service.AttachmentService;
import cn.qiuwanchi.cms.service.IBannerService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("ueditor")
public class UeditorController {

	@Autowired
	private IBannerService bannerService;

	@Autowired
	private AttachmentService attachmentService;

	private static JSONObject jsonObj = null;

	/**
	 * 列表
	 */
	@RequestMapping
	public String list() {

		return "firstPage/ueditorDemo";
	}


	@RequestMapping("/upload")
	@ResponseBody
	public JSONObject ueditor(HttpServletRequest request, HttpServletResponse response) {

		String action = request.getParameter("action");

		if("config".equals(action)){
			return getConfig();
		}

		if("uploadimage".equals(action) || "uploadvideo".equals(action)){
			UploadController uploadController = SeoCmsApplication.ac.getBean(UploadController.class);
			MultipartFile file = ((StandardMultipartHttpServletRequest) request).getFile("upfile");

			return uploadController.ueditorUpload(file);
		}


		return null;

	}

	private JSONObject getConfig(){
		if(Objects.nonNull(jsonObj)){
			return jsonObj;
		}
		String str = "";
		try {
			InputStream inputStream = this.getClass().getResourceAsStream("/config.json");
			str = IOUtils.toString(inputStream, "UTF-8");
		}catch (Exception e){
			log.error("读config.json取文件失败",e);
			return null;
		}

		JSONObject jsonObject = JSONObject.parseObject(str);
		jsonObj = jsonObject;
		return jsonObject;
	}

}
