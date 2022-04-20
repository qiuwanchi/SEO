package cn.itechyou.cms.controller.admin;

import cn.itechyou.cms.entity.Attachment;
import cn.itechyou.cms.entity.ConstantDefinition;
import cn.itechyou.cms.entity.Module;
import cn.itechyou.cms.entity.Project;
import cn.itechyou.cms.security.token.TokenManager;
import cn.itechyou.cms.service.AttachmentService;
import cn.itechyou.cms.service.IConstantDefinitionService;
import cn.itechyou.cms.service.IModuleService;
import cn.itechyou.cms.service.IProjectService;
import cn.itechyou.cms.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("firstPage/module")
public class ModuleController {

	@Autowired
	private IModuleService moduleService;

	@Autowired
	private AttachmentService attachmentService;

	@Autowired
	private IProjectService projectService;

	@Autowired
	private IConstantDefinitionService constantDefinitionService;

	/**
	 * 列表
	 */
	@RequestMapping
	public String list(Model model, String belong) {
		List<Module> moduleList = this.moduleService.list(belong);
		Attachment attachment = new Attachment();
		for (Module module : moduleList){
			if (StringUtils.isEmpty(module.getAttachmentId())){
				module.setAttachment(attachment);
			}else {
				Attachment tempAttachment = this.attachmentService.queryAttachmentById(module.getAttachmentId());

				if(Objects.isNull(tempAttachment)){
					module.setAttachment(attachment);
				}else {
					module.setAttachment(tempAttachment);
				}
			}
		}
		model.addAttribute("moduleList", moduleList);
		model.addAttribute("belong", belong);

		ConstantDefinition constantDefinition = this.constantDefinitionService.getByCode(belong);
		model.addAttribute("constantDefinition", constantDefinition);


		return "firstPage/module/list";
	}

	/**
	 * 保存模块
	 * @param model
	 * @param param
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("/save")
	public String save(Model model, Module param, RedirectAttributes redirectAttributes) {
		Module module;
		if(StringUtils.isEmpty(param.getId())){
			module = new Module();
			Attachment attachment = param.getAttachment();
			// 有上传附件
			if(Objects.nonNull(attachment) && !StringUtils.isEmpty(attachment.getFilepath())){
				attachment.setId(UUIDUtils.getPrimaryKey());
				attachment.setCode(UUIDUtils.getCharAndNumr(8));
				attachment.setCreateBy(TokenManager.getUserId());
				attachment.setCreateTime(new Date());
				attachment.setUpdateBy(TokenManager.getUserId());
				attachment.setUpdateTime(new Date());
				this.attachmentService.save(attachment);
				module.setAttachmentId(attachment.getId());
			}

			module.setId(UUIDUtils.getPrimaryKey());
			module.setBelong(param.getBelong());
			module.setName(param.getName());
			module.setSort(param.getSort());
			module.setTitle(param.getTitle());
			module.setKeywords(param.getKeywords());
			module.setDescription(param.getDescription());
			module.setAlt(param.getAlt());
			module.setClickUrl(param.getClickUrl());
			module.setCreateBy(TokenManager.getUserId());
			module.setUpdateBy(TokenManager.getUserId());
			module.setCode(param.getCode());
			module.setDescribeMsg(param.getDescribeMsg());
			this.moduleService.add(module);
		}else {
			module = this.moduleService.getById(param.getId());
			Attachment attachment = param.getAttachment();

			// 先删除附件
			if(!StringUtils.isEmpty(module.getAttachmentId())){
				this.attachmentService.delete(module.getAttachmentId());
			}
			// 再添加附件
			if(Objects.nonNull(attachment) && !StringUtils.isEmpty(attachment.getFilepath())){
				attachment.setId(UUIDUtils.getPrimaryKey());
				attachment.setCode(UUIDUtils.getCharAndNumr(8));
				attachment.setCreateBy(TokenManager.getUserId());
				attachment.setCreateTime(new Date());
				attachment.setUpdateBy(TokenManager.getUserId());
				attachment.setUpdateTime(new Date());
				this.attachmentService.save(attachment);
				module.setAttachmentId(attachment.getId());
			}else {
				module.setAttachmentId(null);
			}

			module.setTitle(param.getTitle());
			module.setKeywords(param.getKeywords());
			module.setDescription(param.getDescription());
			module.setAlt(param.getAlt());
			module.setClickUrl(param.getClickUrl());
			module.setSort(param.getSort());
			module.setName(param.getName());
			module.setUpdateBy(TokenManager.getUserId());
			module.setCode(param.getCode());
			module.setDescribeMsg(param.getDescribeMsg());
			this.moduleService.update(module);
		}
		redirectAttributes.addAttribute("belong", module.getBelong());
		return "redirect:/firstPage/module";
	}

	@GetMapping("/delete")
	public String delete(Model model, String id, RedirectAttributes redirectAttributes) {
		Module module = this.moduleService.getById(id);
		List<Project> projectList = this.projectService.getByModuleId(id);

		for (Project project : projectList){
			this.attachmentService.delete(project.getAttachmentId());
		}

		this.projectService.deleteByModuleId(id);
		this.moduleService.deleteById(id);

		redirectAttributes.addAttribute("belong", module.getBelong());
		return "redirect:/firstPage/module";
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
