package cn.itechyou.cms.controller.admin;

import cn.itechyou.cms.entity.Attachment;
import cn.itechyou.cms.entity.Banner;
import cn.itechyou.cms.security.token.TokenManager;
import cn.itechyou.cms.service.AttachmentService;
import cn.itechyou.cms.service.BannerService;
import cn.itechyou.cms.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("ueditor")
public class UeditorController {

	@Autowired
	private BannerService bannerService;

	@Autowired
	private AttachmentService attachmentService;

	/**
	 * 列表
	 */
	@RequestMapping
	public String list() {

		return "firstPage/ueditorDemo";
	}



	
}
