package com.qiuwanchi.seo.controller;

import com.qiuwanchi.seo.dto.ModuleDto;
import com.qiuwanchi.seo.dto.ProjectDto;
import com.qiuwanchi.seo.service.IAttachmentService;
import com.qiuwanchi.seo.service.IModuleService;
import com.qiuwanchi.seo.service.IProjectService;
import com.qiuwanchi.seo.utils.BottomManagementCommon;
import com.qiuwanchi.seo.utils.FileConfiguration;
import com.qiuwanchi.seo.utils.ServerConfig;
import com.qiuwanchi.seo.utils.UrlAssemblyUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 公司产品
 */
@Log4j2
@Controller
public class ProductController {

    @Autowired
    private FileConfiguration fileConfiguration;

    @Autowired
    private IAttachmentService attachmentService;

    @Autowired
    private IModuleService moduleService;

    @Autowired
    private IProjectService projectService;

    @Autowired
    private ServerConfig serverConfig;

    @Autowired
    private BottomManagementCommon bottomManagementCommon;

    @GetMapping("/products.html")
    public String products(Model model){
        model.addAttribute("baseUrl", serverConfig.getUrl());

        // 1.logo
        List<ModuleDto> logoModuleList = this.moduleService.getModuleDtoList("LOGO");
        ModuleDto logoModuleDto = logoModuleList.get(0);
        ProjectDto logoProject = CollectionUtils.isEmpty(logoModuleDto.getProjectDtoList()) ? new ProjectDto() : logoModuleDto.getProjectDtoList().get(0);
        model.addAttribute("logoProject", logoProject);

        // 2.公司产品-模块列表
        List<ModuleDto> moduleList = this.moduleService.getModuleDtoList("companyProduct-productModule");
        model.addAttribute("productModuleList", moduleList);

        this.bottomManagementCommon.bottom(model);

        return "goods";
    }

    private List<ModuleDto> getModuleDtoList(String code){
        List<ModuleDto> moduleList = this.moduleService.getModuleDtoList(code);
        for (ModuleDto moduleDto : moduleList){
            if(StringUtils.isNotBlank(moduleDto.getFilePath())){
                moduleDto.setUrl(UrlAssemblyUtils.getImageUrl(moduleDto.getFilePath()));
            }

            if(StringUtils.isBlank(moduleDto.getTitle())){
                moduleDto.setTitle(moduleDto.getName());
            }
            if(StringUtils.isBlank(moduleDto.getAlt())){
                moduleDto.setAlt(moduleDto.getName());
            }
        }
        return  moduleList;
    }

}
