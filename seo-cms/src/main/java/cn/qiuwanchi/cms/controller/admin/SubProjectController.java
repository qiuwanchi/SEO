package cn.qiuwanchi.cms.controller.admin;

import cn.qiuwanchi.cms.entity.*;
import cn.qiuwanchi.cms.security.token.TokenManager;
import cn.qiuwanchi.cms.service.*;
import cn.qiuwanchi.cms.utils.ServerConfig;
import cn.qiuwanchi.cms.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/subProject")
public class SubProjectController {

	@Autowired
	private AttachmentService attachmentService;

	@Autowired
	private IProjectService projectService;

	@Autowired
	private ISubProjectService subProjectService;

	@Autowired
	private IModuleService moduleService;

	@Autowired
	private ISeoVideoService seoVideoService;

	@Autowired
	private ServerConfig serverConfig;

	@Autowired
	private IConstantDefinitionService constantDefinitionService;

	@Autowired
	private IBannerService bannerService;

	/**
	 * 列表
	 */
	@RequestMapping
	public String list(Model model, String projectId) {
		Project project = this.projectService.getById(projectId);
		project.setAttachment(this.attachmentService.queryAttachmentById(project.getAttachmentId()));

		List<SubProject> subProjectList = this.subProjectService.getByProjectId(projectId);
		for (SubProject subProject : subProjectList){
			subProject.setAttachment(this.attachmentService.queryAttachmentById(subProject.getAttachmentId()));
		}
		model.addAttribute("subProjectList", subProjectList);
		model.addAttribute("project", project);

		Module module = this.moduleService.getById(project.getModuleId());
		model.addAttribute("module", module);

		ConstantDefinition constantDefinition = this.constantDefinitionService.getByCode(module.getBelong());
		model.addAttribute("constantDefinition", constantDefinition);

		return "serviceCase/subProject/subProjectList";
	}

	@PostMapping("/toAdd")
	public String toAdd(Model model, String projectId, String id) {
		Project project = this.projectService.getById(projectId);
		model.addAttribute("project", project);

		Module module = this.moduleService.getById(project.getModuleId());
		model.addAttribute("module", module);

		ConstantDefinition constantDefinition = this.constantDefinitionService.getByCode(module.getBelong());
		model.addAttribute("constantDefinition", constantDefinition);

		if(!StringUtils.isEmpty(id)){
			SubProject subProject = this.subProjectService.getById(id);
			if(!StringUtils.isEmpty(subProject.getAttachmentId())){
				Attachment attachment = this.attachmentService.queryAttachmentById(subProject.getAttachmentId());
				subProject.setAttachment(attachment);
				model.addAttribute("imageUrl", serverConfig.getUrl() + "/image/" + attachment.getFilepath().substring(9));
			}

			model.addAttribute("subProject", subProject);

			return "serviceCase/subProject/edit";
		}
		return"serviceCase/subProject/add";
	}

	@PostMapping("/save")
	public String save(Model model, SubProject param, RedirectAttributes redirectAttributes) {
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

			if(!StringUtils.isEmpty(param.getSort())){
				subProject.setSort(param.getSort());
			}
			this.subProjectService.add(subProject);

		}else {
			subProject = this.subProjectService.getById(param.getId());

			Attachment attachment = param.getAttachment();
			attachment.setId(UUIDUtils.getPrimaryKey());
			attachment.setCode(UUIDUtils.getCharAndNumr(8));
			attachment.setCreateBy(TokenManager.getUserId());
			attachment.setCreateTime(new Date());
			attachment.setUpdateBy(TokenManager.getUserId());
			attachment.setUpdateTime(new Date());
			this.attachmentService.save(attachment);
			this.attachmentService.delete(subProject.getAttachmentId());

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

			this.subProjectService.update(subProject);
		}
		redirectAttributes.addAttribute("projectId", subProject.getProjectId());
		return "redirect:/subProject";
	}

	@GetMapping("/delete")
	public String delete(Model model, String id, RedirectAttributes redirectAttributes) {
		SubProject subProject = this.subProjectService.getById(id);
		this.attachmentService.delete(subProject.getAttachmentId());
		this.subProjectService.delete(id);
		redirectAttributes.addAttribute("projectId", subProject.getProjectId());
		return "redirect:/firstPage/module/project";
	}

	@GetMapping("/getById")
	@ResponseBody
	public SubProject getById(@RequestParam("projectId") String projectId) {
		SubProject subProject = this.subProjectService.getById(projectId);
		if(!StringUtils.isEmpty(subProject.getAttachmentId())){
			Attachment attachment = this.attachmentService.queryAttachmentById(subProject.getAttachmentId());
			subProject.setAttachment(attachment);
		}
		return subProject;
	}

	/**
	 * 视频列表
	 * @param model
	 * @param belongId
	 * @return
	 */
	@GetMapping("/videoList")
	public String videoList(Model model, @RequestParam("fromType") String fromType, @RequestParam("belongId") String belongId) {
		model.addAttribute("fromType", fromType);
		ConstantDefinition constantDefinition = null;
		Module module = null;
		Project project = null;
		if("project".equalsIgnoreCase(fromType)){
			project = this.projectService.getById(belongId);
			module = this.moduleService.getById(project.getModuleId());
			constantDefinition = this.constantDefinitionService.getByCode(module.getBelong());
		}else {
			SubProject subProject = this.subProjectService.getById(belongId);
			project = this.projectService.getById(subProject.getProjectId());
			module = this.moduleService.getById(project.getModuleId());
			constantDefinition = this.constantDefinitionService.getByCode(module.getBelong());
			model.addAttribute("subProject", subProject);
		}
		model.addAttribute("constantDefinition", constantDefinition);
		model.addAttribute("module", module);
		model.addAttribute("project", project);

		List<SeoVideo> seoVideoList = this.seoVideoService.selectByBelongId(belongId);
		int i = 0;
		for (SeoVideo seoVideo : seoVideoList){
			seoVideo.setAttachment(this.attachmentService.queryAttachmentById(seoVideo.getAttachmentId()));
			seoVideo.setSort(i++);
		}
		model.addAttribute("baseUrl", serverConfig.getUrl());
		model.addAttribute("seoVideoList", seoVideoList);
		model.addAttribute("belongId", belongId);

		return "firstPage/videoList";
	}

	/**
	 * 保存视频
	 * @param model
	 * @param param
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("/videoSave")
	public String videoSave(Model model, @RequestParam("fromType") String fromType, SeoVideo param, RedirectAttributes redirectAttributes) {
		Attachment attachment = param.getAttachment();
		attachment.setId(UUIDUtils.getPrimaryKey());
		attachment.setCode(UUIDUtils.getCharAndNumr(8));
		attachment.setCreateBy(TokenManager.getUserId());
		attachment.setCreateTime(new Date());
		attachment.setUpdateBy(TokenManager.getUserId());
		attachment.setUpdateTime(new Date());
		this.attachmentService.save(attachment);

		SeoVideo seoVideo = new SeoVideo();
		seoVideo.setBelongId(param.getBelongId());
		seoVideo.setId(UUIDUtils.getPrimaryKey());
		seoVideo.setCreateBy(TokenManager.getUserId());
		seoVideo.setCreateTime(new Date());
		seoVideo.setUpdateBy(TokenManager.getUserId());
		seoVideo.setUpdateTime(new Date());
		seoVideo.setAttachmentId(attachment.getId());

		this.seoVideoService.save(seoVideo);

		redirectAttributes.addAttribute("fromType", fromType);
		redirectAttributes.addAttribute("belongId", seoVideo.getBelongId());

		return "redirect:/subProject/videoList";
	}

	@GetMapping("/toViewBanner")
	public String toViewBanner(Model model, @RequestParam("subProjectId") String subProjectId) {
		SubProject subProject = this.subProjectService.getById(subProjectId);
		Project project = this.projectService.getById(subProject.getProjectId());
		Module module = this.moduleService.getById(project.getModuleId());
		Attachment bannerAttachment = new Attachment();
		Banner banner = new Banner();
		if (!StringUtils.isEmpty(subProject.getBannerId())){
			banner = this.bannerService.getById(subProject.getBannerId());
			bannerAttachment = this.attachmentService.queryAttachmentById(banner.getAttachmentId());
			banner.setAttachment(bannerAttachment);
			model.addAttribute("imageUrl", serverConfig.getUrl() + "/image/" + bannerAttachment.getFilepath().substring(9));
		}

		model.addAttribute("subProject", subProject);

		model.addAttribute("bannerAttachment", bannerAttachment);
		model.addAttribute("project", project);
		model.addAttribute("banner", banner);
		model.addAttribute("belong", module.getBelong());
		model.addAttribute("module", module);

		ConstantDefinition constantDefinition = this.constantDefinitionService.getByCode(module.getBelong());
		model.addAttribute("constantDefinition", constantDefinition);

		return "serviceCase/subProject/viewBanner";
	}

	@GetMapping("/homePageDisplay")
	public String homePageDisplay(@RequestParam("subProjectId") String subProjectId, String flag, RedirectAttributes redirectAttributes) {
		SubProject subProject = this.subProjectService.getById(subProjectId);
		subProject.setHomePageDisplay(flag);
		this.subProjectService.update(subProject);
		redirectAttributes.addAttribute("projectId", subProject.getProjectId());
		return "redirect:/subProject";
	}
}
