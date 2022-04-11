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
@RequestMapping("firstPage/banner")
public class BannerController {

	@Autowired
	private BannerService bannerService;

	@Autowired
	private AttachmentService attachmentService;

	/**
	 * 列表
	 */
	@RequestMapping
	public String list(Model model, String belong) {
		List<Banner> bannerList = this.bannerService.list(belong);
		for (Banner banner : bannerList){
			Attachment attachment = attachmentService.queryAttachmentById(banner.getSystemAttachmentId());
			banner.setAttachment(attachment);
		}
		model.addAttribute("bannerList", bannerList);
		model.addAttribute("belong", belong);
		return "firstPage/banner/list";
	}

	@PostMapping("/save")
	public String save(Model model, Banner param, RedirectAttributes redirectAttributes) {
		Attachment attachment = param.getAttachment();
		if(StringUtils.isEmpty(param.getId())){
			attachment.setId(UUIDUtils.getPrimaryKey());
			attachment.setCode(UUIDUtils.getCharAndNumr(8));
			attachment.setCreateBy(TokenManager.getUserId());
			attachment.setCreateTime(new Date());
			attachment.setUpdateBy(TokenManager.getUserId());
			attachment.setUpdateTime(new Date());
			this.attachmentService.save(attachment);
			param.setSystemAttachmentId(attachment.getId());
			this.bannerService.save(param);

		}else {
			Banner banner = this.bannerService.getById(param.getId());
			banner.setName(param.getName());
			attachment.setId(UUIDUtils.getPrimaryKey());
			attachment.setCode(UUIDUtils.getCharAndNumr(8));
			attachment.setCreateBy(TokenManager.getUserId());
			attachment.setCreateTime(new Date());
			attachment.setUpdateBy(TokenManager.getUserId());
			attachment.setUpdateTime(new Date());
			this.attachmentService.save(attachment);

			this.attachmentService.delete(banner.getSystemAttachmentId());
			banner.setSystemAttachmentId(attachment.getId());

			this.bannerService.save(banner);
		}
		redirectAttributes.addAttribute("belong", param.getBelong());
		return "redirect:/firstPage/banner";
	}

	@GetMapping("/detail")
	public String detail(Model model, String id) {
		Banner banner = this.bannerService.getById(id);
		Attachment attachment = attachmentService.queryAttachmentById(banner.getSystemAttachmentId());
		banner.setAttachment(attachment);
		model.addAttribute("banner", banner);
		return "firstPage/banner/edit";
	}

	@GetMapping("/delete")
	public String delete(Model model, String id, RedirectAttributes redirectAttributes) {
		Banner banner = this.bannerService.getById(id);
		this.attachmentService.delete(banner.getSystemAttachmentId());
		this.bannerService.deleteById(banner.getId());
		redirectAttributes.addAttribute("belong", banner.getBelong());
		return "redirect:/firstPage/banner";
	}

	
}
