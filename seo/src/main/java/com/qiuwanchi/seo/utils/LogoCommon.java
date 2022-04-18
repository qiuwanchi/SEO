package com.qiuwanchi.seo.utils;

import com.qiuwanchi.seo.dto.ModuleDto;
import com.qiuwanchi.seo.dto.ProjectDto;
import com.qiuwanchi.seo.service.IModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
public class LogoCommon {

    @Autowired
    private IModuleService moduleService;

    public void logo(Model model) {
        // 1.logo
        List<ModuleDto> logoModuleList = this.moduleService.getModuleDtoList("LOGO");
        ModuleDto logoModuleDto = logoModuleList.get(0);
        ProjectDto logoProject = CollectionUtils.isEmpty(logoModuleDto.getProjectDtoList()) ? new ProjectDto() : logoModuleDto.getProjectDtoList().get(0);
        model.addAttribute("logoProject", logoProject);
    }

}
