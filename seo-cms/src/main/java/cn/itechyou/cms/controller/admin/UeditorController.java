package cn.itechyou.cms.controller.admin;

import cn.itechyou.cms.SeoCmsApplication;
import cn.itechyou.cms.service.AttachmentService;
import cn.itechyou.cms.service.IBannerService;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@Controller
@RequestMapping("ueditor")
public class UeditorController {

	@Autowired
	private IBannerService bannerService;

	@Autowired
	private AttachmentService attachmentService;

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

		if("uploadimage".equals(action)){
			UploadController uploadController = SeoCmsApplication.ac.getBean(UploadController.class);
			MultipartFile file = ((StandardMultipartHttpServletRequest) request).getFile("upfile");

			return uploadController.ueditorUpload(file);
		}


		return null;

	}

	private JSONObject getConfig(){
		String str = "";
		try {

			File file = ResourceUtils.getFile("classpath:config.json");
			InputStream inputStream = new FileInputStream(file);

			str = IOUtils.toString(inputStream, "UTF-8");

		}catch (Exception e){

			java.lang.System.out.println(e.toString());
		}

		java.lang.System.out.println(str);
		JSONObject jsonObject = JSONObject.parseObject(str);
		return jsonObject;
	}

}
