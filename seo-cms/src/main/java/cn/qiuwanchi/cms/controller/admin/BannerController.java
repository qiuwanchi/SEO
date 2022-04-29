package cn.qiuwanchi.cms.controller.admin;

import cn.qiuwanchi.cms.entity.*;
import cn.qiuwanchi.cms.security.token.TokenManager;
import cn.qiuwanchi.cms.service.*;
import cn.qiuwanchi.cms.utils.UUIDUtils;
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
	private IBannerService bannerService;

	@Autowired
	private IModuleService moduleService;

	@Autowired
	private AttachmentService attachmentService;

	@Autowired
	private IProjectService projectService;

	@Autowired
	private ISubProjectService subProjectService;

	/**
	 * 列表
	 */
	@RequestMapping
	public String list(Model model, String belong) {
		List<Banner> bannerList = this.bannerService.list(belong);
		for (Banner banner : bannerList){
			Attachment attachment = attachmentService.queryAttachmentById(banner.getAttachmentId());
			banner.setAttachment(attachment);
		}
		model.addAttribute("bannerList", bannerList);
		model.addAttribute("belong", belong);
		return "firstPage/banner/list";
	}

	@PostMapping("/saveModuleBanner")
	public String saveModuleBanner(Model model, Banner param, String moduleId, RedirectAttributes redirectAttributes) {
		Module module = this.moduleService.getById(moduleId);
		if(StringUtils.isEmpty(param.getId())){
			Attachment attachment = param.getAttachment();
			attachment.setId(UUIDUtils.getPrimaryKey());
			attachment.setCode(UUIDUtils.getCharAndNumr(8));
			attachment.setCreateBy(TokenManager.getUserId());
			attachment.setCreateTime(new Date());
			attachment.setUpdateBy(TokenManager.getUserId());
			attachment.setUpdateTime(new Date());
			this.attachmentService.save(attachment);
			param.setAttachmentId(attachment.getId());

			Banner banner = new Banner();
			banner.setName(param.getName());
			banner.setClickUrl(param.getClickUrl());
			banner.setAlt(param.getAlt());
			banner.setAttachmentId(attachment.getId());

			this.bannerService.save(banner);

			module.setBannerId(banner.getId());
			this.moduleService.update(module);

		}else {
			Banner banner = this.bannerService.getById(param.getId());
			banner.setName(param.getName());
			banner.setClickUrl(param.getClickUrl());
			banner.setAlt(param.getAlt());
			banner.setUpdateTime(new Date());
			banner.setUpdateBy(TokenManager.getUserId());

			Attachment attachment = this.attachmentService.queryAttachmentById(banner.getAttachmentId());
			attachment.setFilesize(param.getAttachment().getFilesize());
			attachment.setFilepath(param.getAttachment().getFilepath());
			attachment.setFiletype(param.getAttachment().getFiletype());
			attachment.setFilename(param.getAttachment().getFilename());
			attachment.setUpdateTime(new Date());
			attachment.setUpdateBy(TokenManager.getUserId());

			this.attachmentService.update(attachment);
			this.bannerService.save(banner);
		}

		redirectAttributes.addAttribute("belong", module.getBelong());
		return "redirect:/firstPage/module";
	}

	@PostMapping("/saveProjectBanner")
	public String saveProjectBanner(Model model, Banner param, String projectId, RedirectAttributes redirectAttributes) {
		Project project = this.projectService.getById(projectId);
		if(StringUtils.isEmpty(param.getId())){
			Attachment attachment = param.getAttachment();
			attachment.setId(UUIDUtils.getPrimaryKey());
			attachment.setCode(UUIDUtils.getCharAndNumr(8));
			attachment.setCreateBy(TokenManager.getUserId());
			attachment.setCreateTime(new Date());
			attachment.setUpdateBy(TokenManager.getUserId());
			attachment.setUpdateTime(new Date());
			this.attachmentService.save(attachment);
			param.setAttachmentId(attachment.getId());

			Banner banner = new Banner();
			banner.setName(param.getName());
			banner.setClickUrl(param.getClickUrl());
			banner.setAlt(param.getAlt());
			banner.setAttachmentId(attachment.getId());

			this.bannerService.save(banner);

			project.setBannerId(banner.getId());
			this.projectService.update(project);

		}else {
			Banner banner = this.bannerService.getById(param.getId());
			banner.setName(param.getName());
			banner.setClickUrl(param.getClickUrl());
			banner.setAlt(param.getAlt());
			banner.setUpdateTime(new Date());
			banner.setUpdateBy(TokenManager.getUserId());

			Attachment attachment = this.attachmentService.queryAttachmentById(banner.getAttachmentId());
			attachment.setFilesize(param.getAttachment().getFilesize());
			attachment.setFilepath(param.getAttachment().getFilepath());
			attachment.setFiletype(param.getAttachment().getFiletype());
			attachment.setFilename(param.getAttachment().getFilename());
			attachment.setUpdateTime(new Date());
			attachment.setUpdateBy(TokenManager.getUserId());

			this.attachmentService.update(attachment);
			this.bannerService.save(banner);
		}

		redirectAttributes.addAttribute("moduleId", project.getModuleId());
		return "redirect:/firstPage/module/project";
	}

	@PostMapping("/saveSubProjectBanner")
	public String saveSubProjectBanner(Model model, Banner param, String subProjectId, RedirectAttributes redirectAttributes) {
		SubProject subProject = this.subProjectService.getById(subProjectId);
		if(StringUtils.isEmpty(param.getId())){
			Attachment attachment = param.getAttachment();
			attachment.setId(UUIDUtils.getPrimaryKey());
			attachment.setCode(UUIDUtils.getCharAndNumr(8));
			attachment.setCreateBy(TokenManager.getUserId());
			attachment.setCreateTime(new Date());
			attachment.setUpdateBy(TokenManager.getUserId());
			attachment.setUpdateTime(new Date());
			this.attachmentService.save(attachment);
			param.setAttachmentId(attachment.getId());

			Banner banner = new Banner();
			banner.setName(param.getName());
			banner.setClickUrl(param.getClickUrl());
			banner.setAlt(param.getAlt());
			banner.setAttachmentId(attachment.getId());

			this.bannerService.save(banner);

			subProject.setBannerId(banner.getId());
			this.subProjectService.update(subProject);

		}else {
			Banner banner = this.bannerService.getById(param.getId());
			banner.setName(param.getName());
			banner.setClickUrl(param.getClickUrl());
			banner.setAlt(param.getAlt());
			banner.setUpdateTime(new Date());
			banner.setUpdateBy(TokenManager.getUserId());

			Attachment attachment = this.attachmentService.queryAttachmentById(banner.getAttachmentId());
			attachment.setFilesize(param.getAttachment().getFilesize());
			attachment.setFilepath(param.getAttachment().getFilepath());
			attachment.setFiletype(param.getAttachment().getFiletype());
			attachment.setFilename(param.getAttachment().getFilename());
			attachment.setUpdateTime(new Date());
			attachment.setUpdateBy(TokenManager.getUserId());

			this.attachmentService.update(attachment);
			this.bannerService.save(banner);
		}

		redirectAttributes.addAttribute("projectId", subProject.getProjectId());
		return "redirect:/subProject";
	}

	@GetMapping("/detail")
	public String detail(Model model, String id) {
		Banner banner = this.bannerService.getById(id);
		Attachment attachment = attachmentService.queryAttachmentById(banner.getAttachmentId());
		banner.setAttachment(attachment);
		model.addAttribute("banner", banner);
		return "firstPage/banner/edit";
	}

	@GetMapping("/delete")
	public String delete(Model model, String id, RedirectAttributes redirectAttributes) {
		Banner banner = this.bannerService.getById(id);
		this.attachmentService.delete(banner.getAttachmentId());
		this.bannerService.deleteById(banner.getId());
		return "redirect:/firstPage/banner";
	}

	
}
