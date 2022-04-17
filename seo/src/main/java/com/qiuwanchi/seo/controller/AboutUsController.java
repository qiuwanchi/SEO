package com.qiuwanchi.seo.controller;

import com.qiuwanchi.seo.dto.ModuleDto;
import com.qiuwanchi.seo.dto.ProjectDto;
import com.qiuwanchi.seo.service.IAttachmentService;
import com.qiuwanchi.seo.service.IModuleService;
import com.qiuwanchi.seo.utils.BottomManagementCommon;
import com.qiuwanchi.seo.utils.FileConfiguration;
import com.qiuwanchi.seo.utils.ServerConfig;
import com.qiuwanchi.seo.utils.Utils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Log4j2
@Controller
public class AboutUsController {

    @Autowired
    private FileConfiguration fileConfiguration;

    @Autowired
    private IAttachmentService attachmentService;

    @Autowired
    private ServerConfig serverConfig;

    @Autowired
    private IModuleService moduleService;

    @Autowired
    private BottomManagementCommon bottomManagementCommon;

    @GetMapping("/aboutUs.html")
    public String contactUs(Model model){
        model.addAttribute("baseUrl", serverConfig.getUrl());

        // 1.logo
        List<ModuleDto> logoModuleList = this.moduleService.getModuleDtoList("LOGO");
        ModuleDto logoModuleDto = logoModuleList.get(0);
        ProjectDto logoProject = CollectionUtils.isEmpty(logoModuleDto.getProjectDtoList()) ? new ProjectDto() : logoModuleDto.getProjectDtoList().get(0);
        model.addAttribute("logoProject", logoProject);

        // 2.公司介绍
        List<ModuleDto> companyIntroductionModuleList = this.moduleService.getModuleDtoList("CompanyIntroduction");
        ModuleDto companyIntroductionModuleDto = companyIntroductionModuleList.get(0);
        List<ProjectDto> companyIntroductionProjectDtoList = companyIntroductionModuleDto.getProjectDtoList();
        ProjectDto companyIntroductionProjectDto = companyIntroductionProjectDtoList.get(0);
        model.addAttribute("companyIntroductionProjectDto", companyIntroductionProjectDto);

        // 3.一站式服务
        List<ModuleDto> oneStopServiceModuleList = this.moduleService.getModuleDtoList("OneStopService");
        ModuleDto oneStopServiceModuleDto = oneStopServiceModuleList.get(0);
        List<ProjectDto> oneStopServiceProjectDtoList = oneStopServiceModuleDto.getProjectDtoList();
        model.addAttribute("oneStopServiceProjectDtoList", oneStopServiceProjectDtoList);

        // 4.业务范围
        List<ModuleDto> businessScopeModuleList = this.moduleService.getModuleDtoList("BusinessScope");
        ModuleDto businessScopeModuleDto = businessScopeModuleList.get(0);
        List<ProjectDto> businessScopeProjectDtoList = businessScopeModuleDto.getProjectDtoList();
        for (ProjectDto projectDto : businessScopeProjectDtoList){
            if(StringUtils.isNotBlank(projectDto.getContent())){
                projectDto.setContent(Utils.htmlDecode(projectDto.getContent()));
            }
        }

        model.addAttribute("businessScopeProjectDtoList", businessScopeProjectDtoList);

        // 5.我们的优势
        List<ModuleDto> companyAdvantageModuleList = this.moduleService.getModuleDtoList("CompanyAdvantage");
        if(!CollectionUtils.isEmpty(companyAdvantageModuleList)){
            List<ProjectDto> companyAdvantageProjectDtoList = companyAdvantageModuleList.get(0).getProjectDtoList();
            model.addAttribute("companyAdvantageProjectDtoList", companyAdvantageProjectDtoList);
        }

        // 6.荣誉资质
        List<ModuleDto> honoraryQualificationModuleList = this.moduleService.getModuleDtoList("HonoraryQualification");
        ModuleDto honoraryQualificationModuleDto = honoraryQualificationModuleList.get(0);
        List<ProjectDto> honoraryQualificationProjectDtoList = honoraryQualificationModuleDto.getProjectDtoList();
        model.addAttribute("honoraryQualificationProjectDtoList", honoraryQualificationProjectDtoList);

        Map<String, List<ProjectDto>> map = new LinkedHashMap<>();
        int i = 1;
        for (ProjectDto projectDto : honoraryQualificationProjectDtoList){
            List<ProjectDto> list = map.get(String.valueOf(i));
            if(CollectionUtils.isEmpty(list)){
                list = new ArrayList<>();
            }
            list.add(projectDto);
            map.put(String.valueOf(i), list);

            if(list.size() == 5){
                i++;
            }
        }

        model.addAttribute("honoraryQualificationMap", map);

        this.bottomManagementCommon.bottom(model);

        return "about";
    }

}
