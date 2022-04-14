package cn.itechyou.cms.controller.admin;

import cn.itechyou.cms.entity.Attachment;
import cn.itechyou.cms.entity.Module;
import cn.itechyou.cms.entity.Project;
import cn.itechyou.cms.security.token.TokenManager;
import cn.itechyou.cms.service.AttachmentService;
import cn.itechyou.cms.service.IModuleService;
import cn.itechyou.cms.service.IProjectService;
import cn.itechyou.cms.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

	/**
	 * 列表
	 */
	@RequestMapping
	public String list(Model model, String moduleId) {
		Module module = this.moduleService.getById(moduleId);
		List<Project> projectList = this.projectService.getByModuleId(moduleId);
		for (Project project : projectList){
			project.setAttachment(this.attachmentService.queryAttachmentById(project.getSystemAttachmentId()));
		}
		model.addAttribute("projectList", projectList);
		model.addAttribute("module", module);
		return "firstPage/module/project/list";
	}

	@PostMapping("/save")
	public String save(Model model, Project param, RedirectAttributes redirectAttributes) {
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
			project.setDescribe(param.getDescribe());
			project.setSystemAttachmentId(attachment.getId());
			project.setModuleId(param.getModuleId());
			project.setTitle(param.getTitle());
			project.setKeywords(param.getKeywords());
			project.setDescription(param.getDescription());
			project.setAlt(param.getAlt());
			project.setClickUrl(param.getClickUrl());

			if(!StringUtils.isEmpty(param.getSort())){
				project.setSort(param.getSort());
			}
			this.projectService.add(project);

		}else {
			project = this.projectService.getById(param.getId());

			Attachment attachment = param.getAttachment();
			attachment.setId(UUIDUtils.getPrimaryKey());
			attachment.setCode(UUIDUtils.getCharAndNumr(8));
			attachment.setCreateBy(TokenManager.getUserId());
			attachment.setCreateTime(new Date());
			attachment.setUpdateBy(TokenManager.getUserId());
			attachment.setUpdateTime(new Date());
			this.attachmentService.save(attachment);
			this.attachmentService.delete(project.getSystemAttachmentId());

			if(!StringUtils.isEmpty(param.getSort())){
				project.setSort(param.getSort());
			}
			project.setName(param.getName());
			project.setDescribe(param.getDescribe());
			project.setSystemAttachmentId(attachment.getId());
			project.setTitle(param.getTitle());
			project.setKeywords(param.getKeywords());
			project.setDescription(param.getDescription());
			project.setAlt(param.getAlt());
			project.setClickUrl(param.getClickUrl());

			this.projectService.update(project);
		}
		redirectAttributes.addAttribute("moduleId", project.getModuleId());
		return "redirect:/firstPage/module/project";
	}

	@GetMapping("/delete")
	public String delete(Model model, String id, RedirectAttributes redirectAttributes) {
		Project project = this.projectService.getById(id);
		this.attachmentService.delete(project.getSystemAttachmentId());
		this.projectService.delete(id);
		redirectAttributes.addAttribute("moduleId", project.getModuleId());
		return "redirect:/firstPage/module/project";
	}

	@GetMapping("/getById")
	@ResponseBody
	public Project getById(@RequestParam("projectId") String projectId) {
		Project project = this.projectService.getById(projectId);
		if(!StringUtils.isEmpty(project.getSystemAttachmentId())){
			Attachment attachment = this.attachmentService.queryAttachmentById(project.getSystemAttachmentId());
			project.setAttachment(attachment);
		}
		return project;
	}
}
