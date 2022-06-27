package cn.qiuwanchi.cms.controller.admin;

import cn.qiuwanchi.cms.dto.ProjectDto;
import cn.qiuwanchi.cms.entity.*;
import cn.qiuwanchi.cms.security.token.TokenManager;
import cn.qiuwanchi.cms.service.*;
import cn.qiuwanchi.cms.utils.ServerConfig;
import cn.qiuwanchi.cms.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@Autowired
	private ISubProjectService subProjectService;

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

//	@GetMapping("/leftPicture")
//	public String viewPicture(Model model, String projectId) {
//		Project project = this.projectService.getById(projectId);
//
//		Attachment attachment = new Attachment();
//		SeoImage seoImage = new SeoImage();
//		if(!StringUtils.isEmpty(project.getSeoImageId())){
//			seoImage = this.seoImageService.getById(project.getSeoImageId());
//			attachment = this.attachmentService.queryAttachmentById(seoImage.getAttachmentId());
//			model.addAttribute("imageUrl", serverConfig.getUrl() + "/image/" + attachment.getFilepath().substring(9));
//		}
//		model.addAttribute("seoImage", seoImage);
//		model.addAttribute("attachment", attachment);
//		model.addAttribute("project", project);
//
//		Module module = this.moduleService.getById(project.getModuleId());
//		model.addAttribute("module", module);
//
//		return "products/leftPicture";
//	}

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

	@GetMapping("/characteristic")
	public String getById(Model model, @RequestParam("projectId") String projectId) {
		List<Project> projectList = this.projectService.getByModuleId(projectId);

		for (Project project : projectList){
			if(!StringUtils.isEmpty(project.getAttachmentId())){
				Attachment attachment = this.attachmentService.queryAttachmentById(project.getAttachmentId());
				project.setAttachment(attachment);
			}
		}

		Project project = this.projectService.getById(projectId);
		model.addAttribute("project", project);

		Module module = this.moduleService.getById(project.getModuleId());
		model.addAttribute("module", module);

		model.addAttribute("projectList", projectList);
		model.addAttribute("moduleId", projectId);
		return "products/characteristic";
	}

	/**
	 * 保存特点
	 * @param model
	 * @param param
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("/saveCharacteristic")
	public String saveCharacteristic(Model model, Project param, RedirectAttributes redirectAttributes) {
		Project project;
		if(StringUtils.isEmpty(param.getId())){
			Attachment attachment = param.getAttachment();
			attachment.setId(UUIDUtils.getPrimaryKey());
			attachment.setCode(UUIDUtils.getCharAndNumr(8));
			attachment.setCreateBy(TokenManager.getUserId());
			attachment.setCreateTime(new Date());
			attachment.setUpdateBy(TokenManager.getUserId());
			attachment.setUpdateTime(new Date());
			this.attachmentService.save(attachment);

			project = new Project();
			project.setId(UUIDUtils.getPrimaryKey());
			project.setName(param.getName());
			project.setDescribeMsg(param.getDescribeMsg());
			project.setModuleId(param.getModuleId());
			project.setAttachmentId(attachment.getId());
			if(!StringUtils.isEmpty(param.getSort())){
				project.setSort(param.getSort());
			}
			project.setTitle(param.getTitle());
			project.setKeywords(param.getKeywords());
			project.setDescription(param.getDescription());
			project.setAlt(param.getAlt());
			project.setClickUrl(param.getClickUrl());
			project.setContent(param.getContent());
			project.setCode(param.getCode());

			project.setCreateTime(new Date());
			project.setCreateBy(TokenManager.getRealName());
			project.setUpdateTime(new Date());
			project.setUpdateBy(TokenManager.getRealName());

			this.projectService.add(project);

		}else {
			project = this.projectService.getById(param.getId());

			Attachment attachment = this.attachmentService.queryAttachmentById(project.getAttachmentId());
			attachment.setFilename(param.getAttachment().getFilename());
			attachment.setFilesize(param.getAttachment().getFilesize());
			attachment.setFilepath(param.getAttachment().getFilepath());
			attachment.setFiletype(param.getAttachment().getFiletype());
			attachment.setUpdateBy(TokenManager.getUserId());
			attachment.setUpdateTime(new Date());
			this.attachmentService.update(attachment);

			if(!StringUtils.isEmpty(param.getSort())){
				project.setSort(param.getSort());
			}
			project.setName(param.getName());
			project.setDescribeMsg(param.getDescribeMsg());
			project.setAttachmentId(attachment.getId());
			project.setTitle(param.getTitle());
			project.setKeywords(param.getKeywords());
			project.setDescription(param.getDescription());
			project.setAlt(param.getAlt());
			project.setClickUrl(param.getClickUrl());
			project.setContent(param.getContent());
			project.setCode(param.getCode());
			project.setUpdateTime(new Date());
			project.setUpdateBy(TokenManager.getRealName());

			this.projectService.update(project);
		}

		redirectAttributes.addAttribute("projectId", project.getModuleId());
		return "redirect:/products/characteristic";
	}

	/**
	 * 应用场景列表
	 * @param model
	 * @param projectId
	 * @return
	 */
	@GetMapping("/applicationScenarios")
	public String applicationScenarios(Model model, String projectId) {
		List<SubProject> subProjectList = this.subProjectService.getByProjectId(projectId);
		for (SubProject subProject : subProjectList){
			Attachment attachment = this.attachmentService.queryAttachmentById(subProject.getAttachmentId());
			subProject.setAttachment(attachment);
		}
		Project project = this.projectService.getById(projectId);
		Module module = this.moduleService.getById(project.getModuleId());
		model.addAttribute("project", project);
		model.addAttribute("module", module);
		model.addAttribute("subProjectList", subProjectList);

		return "products/applicationScenariosList";
	}

	@RequestMapping("/toAdd")
	public String toAdd(Model model, String moduleId, String projectId, String id) {
		Module module = this.moduleService.getById(moduleId);
		model.addAttribute("module", module);

		Project project = this.projectService.getById(projectId);
		model.addAttribute("project", project);

		if(!StringUtils.isEmpty(id)){
			SubProject subProject = this.subProjectService.getById(id);
			if(!StringUtils.isEmpty(subProject.getAttachmentId())){
				Attachment attachment = this.attachmentService.queryAttachmentById(subProject.getAttachmentId());
				subProject.setAttachment(attachment);
			}
			model.addAttribute("subProject", subProject);

			return "products/edit";
		}
		return "products/add";
	}

	/**
	 * 保存场景
	 * @param model
	 * @param param
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("/saveCj")
	public String saveCj(Model model, SubProject param, RedirectAttributes redirectAttributes) {
		SubProject subProject;
		if(StringUtils.isEmpty(param.getId())){
			Attachment attachment = param.getAttachment();
			attachment.setId(UUIDUtils.getPrimaryKey());
			attachment.setCode(UUIDUtils.getCharAndNumr(8));
			attachment.setCreateBy(TokenManager.getUserId());
			attachment.setCreateTime(new Date());
			attachment.setUpdateBy(TokenManager.getUserId());
			attachment.setUpdateTime(new Date());
			this.attachmentService.save(attachment);

			subProject = new SubProject();
			subProject.setId(UUIDUtils.getPrimaryKey());
			subProject.setName(param.getName());
			subProject.setDescribeMsg(param.getDescribeMsg());
			subProject.setAttachmentId(attachment.getId());
			subProject.setProjectId(param.getProjectId());
			subProject.setTitle(param.getTitle());
			subProject.setKeywords(param.getKeywords());
			subProject.setDescription(param.getDescription());
			subProject.setAlt(param.getAlt());
			subProject.setClickUrl(param.getClickUrl());
			subProject.setContent(param.getContent());
			subProject.setCreateTime(new Date());
			subProject.setCreateBy(TokenManager.getRealName());
			subProject.setUpdateTime(new Date());
			subProject.setUpdateBy(TokenManager.getRealName());

			if(!StringUtils.isEmpty(param.getSort())){
				subProject.setSort(param.getSort());
			}
			this.subProjectService.add(subProject);

		}else {
			subProject = this.subProjectService.getById(param.getId());
			Attachment attachment = this.attachmentService.queryAttachmentById(subProject.getAttachmentId());
			attachment.setFiletype(param.getAttachment().getFiletype());
			attachment.setFilepath(param.getAttachment().getFilepath());
			attachment.setFilesize(param.getAttachment().getFilesize());
			attachment.setFilename(param.getAttachment().getFilename());
			attachment.setUpdateBy(TokenManager.getUserId());
			attachment.setUpdateTime(new Date());
			this.attachmentService.update(attachment);

			if(!StringUtils.isEmpty(param.getSort())){
				subProject.setSort(param.getSort());
			}
			subProject.setName(param.getName());
			subProject.setDescribeMsg(param.getDescribeMsg());
			subProject.setAttachmentId(attachment.getId());
			subProject.setTitle(param.getTitle());
			subProject.setKeywords(param.getKeywords());
			subProject.setDescription(param.getDescription());
			subProject.setAlt(param.getAlt());
			subProject.setClickUrl(param.getClickUrl());
			subProject.setContent(param.getContent());
			subProject.setUpdateTime(new Date());
			subProject.setUpdateBy(TokenManager.getRealName());

			this.subProjectService.update(subProject);
		}
		redirectAttributes.addAttribute("projectId", param.getProjectId());
		return "redirect:/products/applicationScenarios";
	}


	@GetMapping("/deleteCj")
	public String deleteCj(Model model, String id, RedirectAttributes redirectAttributes) {
		SubProject subProject = this.subProjectService.delete(id);
		redirectAttributes.addAttribute("projectId", subProject.getProjectId());
		return "redirect:/products/applicationScenarios";
	}

}
