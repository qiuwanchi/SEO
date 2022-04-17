package cn.itechyou.cms.controller.admin;

import cn.itechyou.cms.entity.Attachment;
import cn.itechyou.cms.entity.Module;
import cn.itechyou.cms.entity.Project;
import cn.itechyou.cms.entity.SubProject;
import cn.itechyou.cms.security.token.TokenManager;
import cn.itechyou.cms.service.AttachmentService;
import cn.itechyou.cms.service.IModuleService;
import cn.itechyou.cms.service.IProjectService;
import cn.itechyou.cms.service.ISubProjectService;
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

		return "serviceCase/subProject/list";
	}

	@PostMapping("/toAdd")
	public String toAdd(Model model, String projectId, String id) {
		Project project = this.projectService.getById(projectId);
		model.addAttribute("project", project);

		Module module = this.moduleService.getById(project.getModuleId());
		model.addAttribute("module", module);

		if(!StringUtils.isEmpty(id)){
			SubProject subProject = this.subProjectService.getById(id);
			if(!StringUtils.isEmpty(subProject.getAttachmentId())){
				Attachment attachment = this.attachmentService.queryAttachmentById(subProject.getAttachmentId());
				subProject.setAttachment(attachment);
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
			subProject.setDescribe(param.getDescribe());
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
			subProject.setDescribe(param.getDescribe());
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
}
