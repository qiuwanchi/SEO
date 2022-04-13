package com.qiuwanchi.seo.controller;

import com.qiuwanchi.seo.dto.ModuleDto;
import com.qiuwanchi.seo.dto.ProjectDto;
import com.qiuwanchi.seo.service.IAttachmentService;
import com.qiuwanchi.seo.service.IBannerService;
import com.qiuwanchi.seo.service.IModuleService;
import com.qiuwanchi.seo.service.IProjectService;
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

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Controller
public class ServiceCaseController {

    @Autowired
    private FileConfiguration fileConfiguration;

    @Autowired
    private IAttachmentService attachmentService;

    @Autowired
    private ServerConfig serverConfig;

    @Autowired
    private IBannerService bannerService;

    @Autowired
    private IModuleService moduleService;

    @Autowired
    private IProjectService projectService;

    @GetMapping("/serviceCase")
    public String contactUs(Model model){
        // 1.baseUrl
        model.addAttribute("baseUrl", serverConfig.getUrl());

        //
        List<ModuleDto> serviceCaseModuleList = this.getModuleDtoList("ServiceCase");
        model.addAttribute("serviceCaseModuleList", serviceCaseModuleList);

        ModuleDto moduleDto = serviceCaseModuleList.get(0);
        List<ProjectDto> projectDtoList = this.projectService.getProjectListByModuleId(moduleDto.getId());
        for (ProjectDto projectDto : projectDtoList){
            projectDto.setUrl(UrlAssemblyUtils.getImageUrl(projectDto.getFilePath()));
        }
        moduleDto.setProjectDtoList(projectDtoList);

        model.addAttribute("serviceCaseModule", moduleDto);

        return "serviceCase";
    }

    private List<ModuleDto> getModuleDtoList(String code){
        List<ModuleDto> moduleList = this.moduleService.getModuleList(code);
        for (ModuleDto moduleDto : moduleList){
            if(StringUtils.isNotBlank(moduleDto.getFilePath())){
                moduleDto.setUrl(UrlAssemblyUtils.getImageUrl(moduleDto.getFilePath()));
            }
        }
        return  moduleList;
    }

    private void intiProject(ModuleDto moduleDto){
        List<ProjectDto> projectDtoList = this.projectService.getProjectListByModuleId(moduleDto.getId());
        for (ProjectDto projectDto : projectDtoList){
            projectDto.setUrl(UrlAssemblyUtils.getImageUrl(projectDto.getFilePath()));
        }
        moduleDto.setProjectDtoList(projectDtoList);
    }
}
