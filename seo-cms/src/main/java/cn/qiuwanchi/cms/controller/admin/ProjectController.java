package cn.qiuwanchi.cms.controller.admin;

import cn.qiuwanchi.cms.entity.*;
import cn.qiuwanchi.cms.security.token.TokenManager;
import cn.qiuwanchi.cms.service.*;
import cn.qiuwanchi.cms.utils.ServerConfig;
import cn.qiuwanchi.cms.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("firstPage/module/project")
public class ProjectController {

	@Autowired
	private IModuleService moduleService;

	@Autowired
	private AttachmentService attachmentService;

	@Autowired
	private IProjectService projectService;

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
	public String list(Model model, String moduleId) {
		Module module = this.moduleService.getById(moduleId);
		List<Project> projectList = this.projectService.getByModuleId(moduleId);
		for (Project project : projectList){
			project.setAttachment(this.attachmentService.queryAttachmentById(project.getAttachmentId()));
		}
		model.addAttribute("projectList", projectList);
		model.addAttribute("module", module);

		model.addAttribute("isAdd", this.isAdd(module));
		model.addAttribute("isDelete", this.isDelete(module));

		ConstantDefinition constantDefinition = this.constantDefinitionService.getByCode(module.getBelong());
		model.addAttribute("constantDefinition", constantDefinition);

		// 关于我们-公司介绍/公司产品/业务范围/公司新闻/热门回答
		if("CompanyIntroduction".equals(module.getBelong()) ||
				"companyProduct-productModule".equals(module.getBelong()) ||
				"BusinessScope".equals(module.getBelong()) ||
				"News".equals(module.getBelong()) ||
				"hostAnswer".equals(module.getBelong()) ||
				"SolutionCase".equals(module.getBelong())){

			boolean isFirstCategory = this.idFirstCategory(module.getBelong());
			model.addAttribute("isFirstCategory", isFirstCategory);

			return "firstPage/module/project/projectContentList";
		}

		if("ServiceCase".equals(module.getBelong())){
			return "serviceCase/serviceCaseList";
		}

		// 首页公司产品-更多按钮
		if("FirstPage-CompanyProduct-more".equalsIgnoreCase(module.getBelong()) || "FirstPage-ServiceCase-more".equalsIgnoreCase(module.getBelong())){
			model.addAttribute("isMore", !CollectionUtils.isEmpty(projectList));
		}else {
			model.addAttribute("isMore", false);
		}

		return "firstPage/module/project/projectList";
	}

	private boolean idFirstCategory(String belong) {
		return "CompanyIntroduction".equalsIgnoreCase(belong) || "BusinessScope".equalsIgnoreCase(belong);
	}

	private boolean isAdd(Module module){
		if("LOGO".equalsIgnoreCase(module.getBelong()) || "ThreeElementsOfColumnPageSeo".equals(module.getBelong())){
			return false;
		}
		return true;
	}

	private boolean isDelete(Module module){
		if("LOGO".equalsIgnoreCase(module.getBelong()) || "ThreeElementsOfColumnPageSeo".equals(module.getBelong())){
			return false;
		}

		return true;
	}

	@RequestMapping("/toAdd")
	public String toAdd(Model model, String moduleId, String id, String fromType) {
		Module module = this.moduleService.getById(moduleId);
		model.addAttribute("module", module);
		ConstantDefinition constantDefinition = this.constantDefinitionService.getByCode(module.getBelong());
		model.addAttribute("constantDefinition", constantDefinition);
		boolean isFirstCategory = this.idFirstCategory(module.getBelong());
		model.addAttribute("isFirstCategory", isFirstCategory);
		model.addAttribute("fromType", fromType);

		if(!StringUtils.isEmpty(id)){
			Project project = this.projectService.getById(id);
			if(!StringUtils.isEmpty(project.getAttachmentId())){
				Attachment attachment = this.attachmentService.queryAttachmentById(project.getAttachmentId());
				project.setAttachment(attachment);
				model.addAttribute("imageUrl", serverConfig.getUrl() + "/image/" + attachment.getFilepath().substring(9));
			}

			model.addAttribute("project", project);

			return "firstPage/module/project/edit";
		}

		return "firstPage/module/project/add";
	}


	@PostMapping("/save")
	public String save(Model model, Project param, String fromType, RedirectAttributes redirectAttributes) {
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
			project.setAttachmentId(attachment.getId());
			project.setModuleId(param.getModuleId());
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

			if(!StringUtils.isEmpty(param.getSort())){
				project.setSort(param.getSort());
			}
			this.projectService.add(project);

		}else {
			project = this.projectService.getById(param.getId());
			Attachment attachment = this.attachmentService.queryAttachmentById(project.getAttachmentId());

			attachment.setFilename(param.getAttachment().getFilename());
			attachment.setFilepath(param.getAttachment().getFilepath());
			attachment.setFilesize(param.getAttachment().getFilesize());
			attachment.setFiletype(param.getAttachment().getFiletype());

			attachment.setUpdateBy(TokenManager.getUserId());
			attachment.setUpdateTime(new Date());
			this.attachmentService.update(attachment);

			if(!StringUtils.isEmpty(param.getSort())){
				project.setSort(param.getSort());
			}
			project.setClickUrl(param.getClickUrl());
			project.setName(param.getName());
			project.setDescribeMsg(param.getDescribeMsg());

			project.setTitle(param.getTitle());
			project.setKeywords(param.getKeywords());
			project.setDescription(param.getDescription());
			project.setAlt(param.getAlt());
			project.setContent(param.getContent());
			project.setUpdateTime(new Date());
			project.setUpdateBy(TokenManager.getRealName());

			this.projectService.update(project);
		}

		if("detail".equalsIgnoreCase(fromType)){
			return "redirect:/products";
		}

		redirectAttributes.addAttribute("moduleId", project.getModuleId());
		return "redirect:/firstPage/module/project";
	}

	@GetMapping("/delete")
	public String delete(Model model, String id, RedirectAttributes redirectAttributes) {
		Project project = this.projectService.delete(id);
		redirectAttributes.addAttribute("moduleId", project.getModuleId());
		return "redirect:/firstPage/module/project";
	}

	/**
	 * 异步删除
	 * @param id
	 * @return
	 */
	@GetMapping("/asynchronousDelete")
	@ResponseBody
	public boolean asynchronousDelete(String id) {
		this.projectService.delete(id);
		return true;
	}

	@GetMapping("/getById")
	@ResponseBody
	public Project getById(@RequestParam("projectId") String projectId) {
		Project project = this.projectService.getById(projectId);
		if(!StringUtils.isEmpty(project.getAttachmentId())){
			Attachment attachment = this.attachmentService.queryAttachmentById(project.getAttachmentId());
			project.setAttachment(attachment);
		}
		return project;
	}

	@GetMapping("/checkCode")
	@ResponseBody
	public boolean checkCode(@RequestParam("moduleId") String moduleId, @RequestParam("id") String id, @RequestParam("code") String code) {

		int count = this.projectService.getCountByCode(moduleId, id, code);
		return count > 0;
	}

	@GetMapping("/homePageDisplay")
	public String homePageDisplay(@RequestParam("projectId") String projectId, String flag, RedirectAttributes redirectAttributes) {
		Project project = this.projectService.getById(projectId);
		project.setHomePageDisplay(flag);
		this.projectService.update(project);
		redirectAttributes.addAttribute("moduleId", project.getModuleId());
		return "redirect:/firstPage/module/project";
	}

	@GetMapping("/toViewBanner")
	public String toViewBanner(Model model, @RequestParam("projectId") String projectId) {
		Project project = this.projectService.getById(projectId);
		Module module = this.moduleService.getById(project.getModuleId());
		Attachment bannerAttachment = new Attachment();
		Banner banner = new Banner();
		if (!StringUtils.isEmpty(project.getBannerId())){
			banner = this.bannerService.getById(project.getBannerId());
			bannerAttachment = this.attachmentService.queryAttachmentById(banner.getAttachmentId());
			banner.setAttachment(bannerAttachment);
			model.addAttribute("imageUrl", serverConfig.getUrl() + "/image/" + bannerAttachment.getFilepath().substring(9));
		}

		model.addAttribute("bannerAttachment", bannerAttachment);
		model.addAttribute("project", project);
		model.addAttribute("banner", banner);
		model.addAttribute("belong", module.getBelong());
		model.addAttribute("module", module);

		ConstantDefinition constantDefinition = this.constantDefinitionService.getByCode(module.getBelong());
		model.addAttribute("constantDefinition", constantDefinition);

		return "firstPage/module/project/viewBanner";
	}
}
