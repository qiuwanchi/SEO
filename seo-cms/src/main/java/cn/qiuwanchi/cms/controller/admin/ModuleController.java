package cn.qiuwanchi.cms.controller.admin;

import cn.qiuwanchi.cms.common.Constant;
import cn.qiuwanchi.cms.entity.Attachment;
import cn.qiuwanchi.cms.entity.Banner;
import cn.qiuwanchi.cms.entity.ConstantDefinition;
import cn.qiuwanchi.cms.entity.Module;
import cn.qiuwanchi.cms.security.token.TokenManager;
import cn.qiuwanchi.cms.service.*;
import cn.qiuwanchi.cms.utils.FileConfiguration;
import cn.qiuwanchi.cms.utils.FileUtils;
import cn.qiuwanchi.cms.utils.ServerConfig;
import cn.qiuwanchi.cms.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

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

	@Autowired
	private IBannerService bannerService;

	@Autowired
	private ServerConfig serverConfig;

	@Autowired
	private FileConfiguration fileConfiguration;

	@RequestMapping("/toViewBanner")
	public String toViewBanner(Model model, String moduleId) {
		Module module = this.moduleService.getById(moduleId);
		Attachment bannerAttachment = new Attachment();
		Banner banner = new Banner();
		if (!StringUtils.isEmpty(module.getBannerId())){
			banner = this.bannerService.getById(module.getBannerId());
			bannerAttachment = this.attachmentService.queryAttachmentById(banner.getAttachmentId());
			banner.setAttachment(bannerAttachment);
			model.addAttribute("imageUrl", serverConfig.getUrl() + "/image/" + bannerAttachment.getFilepath().substring(9));
		}
		model.addAttribute("bannerAttachment", bannerAttachment);
		model.addAttribute("module", module);
		model.addAttribute("banner", banner);
		model.addAttribute("belong", module.getBelong());

		model.addAttribute("isAdd", this.isAdd(module.getBelong()));
		model.addAttribute("isDelete", this.isDelete(module.getBelong()));

		ConstantDefinition constantDefinition = this.constantDefinitionService.getByCode(module.getBelong());
		model.addAttribute("constantDefinition", constantDefinition);

		return "firstPage/module/viewBanner";
	}

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

		model.addAttribute("isAdd", this.isAdd(belong));
		model.addAttribute("isDelete", this.isDelete(belong));

		ConstantDefinition constantDefinition = this.constantDefinitionService.getByCode(belong);
		model.addAttribute("constantDefinition", constantDefinition);

		return "firstPage/module/moduleList";
	}

	private boolean isAdd(String belong){
		if("ContactUs".equalsIgnoreCase(belong)){
			return false;
		}

		return true;
	}

	private boolean isDelete(String belong){
		if("ContactUs".equalsIgnoreCase(belong)){
			return false;
		}

		return true;
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
			module.setCode(param.getCode());
			module.setDescribeMsg(param.getDescribeMsg());
			module.setCreateTime(new Date());
			module.setUpdateTime(new Date());
			module.setCreateBy(TokenManager.getRealName());
			module.setUpdateBy(TokenManager.getRealName());
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
				attachment.setCreateTime(new Date());
				attachment.setUpdateTime(new Date());
				attachment.setCreateBy(TokenManager.getRealName());
				attachment.setUpdateBy(TokenManager.getRealName());

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
			module.setUpdateBy(TokenManager.getRealName());
			module.setCode(param.getCode());
			module.setDescribeMsg(param.getDescribeMsg());
			this.moduleService.update(module);
		}
		redirectAttributes.addAttribute("belong", module.getBelong());
		return "redirect:/firstPage/module";
	}

	@GetMapping("/delete")
	public String delete(Model model, String id, RedirectAttributes redirectAttributes) {
		Module module = this.moduleService.deleteById(id);
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

	@GetMapping("/homePageDisplay")
	public String homePageDisplay(@RequestParam("moduleId") String moduleId, String flag, RedirectAttributes redirectAttributes) {
		Module module = this.moduleService.getById(moduleId);
		module.setHomePageDisplay(flag);
		this.moduleService.update(module);
		redirectAttributes.addAttribute("belong", module.getBelong());
		return "redirect:/firstPage/module";
	}

	@ResponseBody
	@GetMapping("/copyImage")
	public List<Map<String ,String >> copyImage() {
		List<Attachment> attachmentList = this.attachmentService.selectAll();

		List<Map<String ,String >> list = new LinkedList<>();
		for (Attachment attachment : attachmentList){
			File file1 = new File(fileConfiguration.getResourceDir() + File.separator +  "uploads" + File.separator + attachment.getFilepath());
			if(!file1.exists()){
				System.out.println("文件=" + file1.getAbsolutePath() + "不存在不需要复制!");
				continue;
			}
			String name = attachment.getFilepath().substring(9);

			String type = "image";
			if(attachment.getFiletype().contains("video")){
				type = "video";
			}

			File file2 = new File(Constant.NGINX_DIR + File.separator + type + File.separator + name);

			if(!file2.exists()){
				try {
					file2.createNewFile();
					System.out.println("文件=" + file2.getAbsolutePath() + "不存在，现在已经创建好了!");
				}catch (Exception e){
					System.out.println("创建文件失败,文件=" + file2.getAbsolutePath());
				}
			}

			FileUtils.copy(file1, file2);

			Map<String,String> map = new TreeMap<>();
			map.put("src",file1.getAbsolutePath());
			map.put("dest",file2.getAbsolutePath());

			list.add(map);

		}
		// "/usr/local/nginx/html/"

		// /srv/seo/imageVideo/
		return list;
	}


}
