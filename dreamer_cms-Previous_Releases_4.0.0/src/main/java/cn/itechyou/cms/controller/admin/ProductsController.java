package cn.itechyou.cms.controller.admin;

import cn.itechyou.cms.dto.ProjectDto;
import cn.itechyou.cms.entity.*;
import cn.itechyou.cms.security.token.TokenManager;
import cn.itechyou.cms.service.*;
import cn.itechyou.cms.utils.ServerConfig;
import cn.itechyou.cms.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("products")
public class ProductsController {

	@Autowired
	private IModuleService moduleService;

	@Autowired
	private AttachmentService attachmentService;

	@Autowired
	private IProjectService projectService;

	@Autowired
	private IConstantDefinitionService constantDefinitionService;

	@Autowired
	private SeoImageService seoImageService;

	@Autowired
	private ServerConfig serverConfig;

	/**
	 * 列表
	 */
	@RequestMapping
	public String list(Model model) {
		String belong = "companyProduct-productModule";
		List<ProjectDto> projectList = this.projectService.getByBelong(belong);
		model.addAttribute("projectList", projectList);
		model.addAttribute("belong", belong);

		return "products/productsList";
	}

	@GetMapping("/delete")
	public String delete(Model model, String id, RedirectAttributes redirectAttributes) {
		Project project = this.projectService.getById(id);
		if(!StringUtils.isEmpty(project.getAttachmentId())){
			this.attachmentService.delete(project.getAttachmentId());
		}
		this.projectService.delete(id);

		return "redirect:/products";
	}

	@GetMapping("/leftPicture")
	public String viewPicture(Model model, String projectId) {
		Project project = this.projectService.getById(projectId);

		Attachment attachment = new Attachment();
		SeoImage seoImage = new SeoImage();
		if(!StringUtils.isEmpty(project.getSeoImageId())){
			seoImage = this.seoImageService.getById(project.getSeoImageId());
			attachment = this.attachmentService.queryAttachmentById(seoImage.getAttachmentId());
			model.addAttribute("imageUrl", serverConfig.getUrl() + "/image/" + attachment.getFilepath().substring(9));
		}
		model.addAttribute("seoImage", seoImage);
		model.addAttribute("attachment", attachment);
		model.addAttribute("project", project);

		return "products/leftPicture";
	}

	@PostMapping("/savePicture")
	public String savePicture(Model model, Project param, RedirectAttributes redirectAttributes) {
		if(StringUtils.isEmpty(param.getId())){
			return "404";
		}
		Project project = this.projectService.getById(param.getId());
		if(Objects.isNull(project)){
			return "404";
		}

		if(StringUtils.isEmpty(param.getSeoImageId())){
			Attachment attachment = param.getAttachment();
			attachment.setId(UUIDUtils.getPrimaryKey());
			attachment.setCode(UUIDUtils.getCharAndNumr(8));
			attachment.setCreateBy(TokenManager.getUserId());
			attachment.setCreateTime(new Date());
			attachment.setUpdateBy(TokenManager.getUserId());
			attachment.setUpdateTime(new Date());
			this.attachmentService.save(attachment);

			SeoImage seoImage = new SeoImage();
			seoImage.setId(UUIDUtils.getPrimaryKey());
			seoImage.setClickUrl(param.getClickUrl());
			seoImage.setName(param.getName());
			seoImage.setAlt(param.getAlt());
			seoImage.setAttachmentId(attachment.getId());
			seoImage.setCreateBy(TokenManager.getUserId());
			seoImage.setCreateTime(new Date());
			seoImage.setUpdateBy(TokenManager.getUserId());
			seoImage.setUpdateTime(new Date());
			this.seoImageService.save(seoImage);

			project.setSeoImageId(seoImage.getId());
			this.projectService.update(project);
		}else {
			SeoImage seoImage = this.seoImageService.getById(param.getSeoImageId());
			Attachment attachment = this.attachmentService.queryAttachmentById(seoImage.getAttachmentId());
			attachment.setFilename(param.getAttachment().getFilename());
			attachment.setFilepath(param.getAttachment().getFilepath());
			attachment.setFilesize(param.getAttachment().getFilesize());
			attachment.setFiletype(param.getAttachment().getFiletype());
			attachment.setUpdateBy(TokenManager.getUserId());
			attachment.setUpdateTime(new Date());
			this.attachmentService.update(attachment);

			seoImage.setClickUrl(param.getClickUrl());
			seoImage.setName(param.getName());
			seoImage.setAlt(param.getAlt());
			seoImage.setAttachmentId(attachment.getId());
			seoImage.setUpdateBy(TokenManager.getUserId());
			seoImage.setUpdateTime(new Date());
			this.seoImageService.update(seoImage);
		}
		redirectAttributes.addAttribute("projectId", project.getId());
		return "redirect:/products/leftPicture";
	}



	@GetMapping("/checkCode")
	@ResponseBody
	public boolean checkCode(@RequestParam("id") String id, @RequestParam("code") String code) {
		int count = this.moduleService.getCountByCode(id, code);
		return count > 0;
	}

	@GetMapping("/getById")
	@ResponseBody
	public Module getById(@RequestParam("moduleId") String moduleId) {
		Module module = this.moduleService.getById(moduleId);
		if(!StringUtils.isEmpty(module.getAttachmentId())){
			Attachment attachment = this.attachmentService.queryAttachmentById(module.getAttachmentId());
			module.setAttachment(attachment);
		}
		return module;
	}

}
