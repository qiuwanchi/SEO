package com.qiuwanchi.seo.controller;

import com.google.common.collect.Lists;
import com.qiuwanchi.seo.dto.ModuleDto;
import com.qiuwanchi.seo.dto.ProjectDto;
import com.qiuwanchi.seo.service.IAttachmentService;
import com.qiuwanchi.seo.service.IModuleService;
import com.qiuwanchi.seo.utils.*;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private LogoCommon logoCommon;

    @GetMapping("/aboutUs.html")
    public String contactUs(Model model){
        model.addAttribute("baseUrl", serverConfig.getUrl());

        // logo
        this.logoCommon.logo(model);

        // 2.关于我们-banner图
        model.addAttribute("aboutUsBannerProjectDto", this.getAboutUsBannerProjectDto());

        // 2.公司介绍
        model.addAttribute("companyIntroductionProjectDto", this.getCompanyIntroductionProjectDto());

        // 3.一站式服务
        model.addAttribute("oneStopServiceProjectDtoList", this.getOneStopServiceProjectDtoList());

        // 4.业务范围
        model.addAttribute("businessScopeProjectDtoList", this.getBusinessScopeProjectDtoList());

        // 5.我们的优势
        model.addAttribute("companyAdvantageProjectDtoList", this.getCompanyAdvantageProjectDtoList());

        // 6.荣誉资质
        List<ProjectDto> honoraryQualificationProjectDtoList = this.getHonoraryQualificationProjectDtoList();
        model.addAttribute("honoraryQualificationProjectDtoList", honoraryQualificationProjectDtoList);

        model.addAttribute("honoraryQualificationMap", this.getHonoraryQualificationMap(honoraryQualificationProjectDtoList));

        this.bottomManagementCommon.bottom(model);
        return "about";
    }

    /**
     * 关于我们-banner图
     * @return
     */
    private ProjectDto getAboutUsBannerProjectDto(){
        List<ModuleDto> aboutUsBannerModuleList = this.moduleService.getModuleDtoList("AboutUs_Banner");
        ModuleDto  aboutUsBannerModuleDto = aboutUsBannerModuleList.get(0);
        List<ProjectDto> aboutUsBannerProjectDtoList = aboutUsBannerModuleDto.getProjectDtoList();
        if(CollectionUtils.isEmpty(aboutUsBannerProjectDtoList)){
            return new ProjectDto();
        }
        return aboutUsBannerProjectDtoList.get(0);
    }

    /**
     * 获取关于我们-公司介绍
     * @return
     */
    private ProjectDto getCompanyIntroductionProjectDto(){
        List<ModuleDto> companyIntroductionModuleList = this.moduleService.getModuleDtoList("CompanyIntroduction");
        ModuleDto companyIntroductionModuleDto = companyIntroductionModuleList.get(0);
        List<ProjectDto> companyIntroductionProjectDtoList = companyIntroductionModuleDto.getProjectDtoList();
        if(CollectionUtils.isEmpty(companyIntroductionProjectDtoList)){
            return new ProjectDto();
        }

        ProjectDto companyIntroductionProjectDto = companyIntroductionProjectDtoList.get(0);
        companyIntroductionProjectDto.setModuleName(companyIntroductionModuleDto.getName());
        companyIntroductionProjectDto.setModuleCode(companyIntroductionModuleDto.getCode());

        if(StringUtils.isBlank(companyIntroductionProjectDto.getContent())){
            companyIntroductionProjectDto.setContent(StringUtils.EMPTY);
        }else{
            companyIntroductionProjectDto.setContent(Utils.htmlDecode(companyIntroductionProjectDto.getContent()));
        }
        return companyIntroductionProjectDto;
    }

    /**
     * 获取关于我们-一站式服务
     * @return
     */
    private List<ProjectDto> getOneStopServiceProjectDtoList(){
        List<ModuleDto> oneStopServiceModuleList = this.moduleService.getModuleDtoList("OneStopService");
        ModuleDto oneStopServiceModuleDto = oneStopServiceModuleList.get(0);
        if(CollectionUtils.isEmpty(oneStopServiceModuleDto.getProjectDtoList())){
            return Lists.newArrayList();
        }
        return oneStopServiceModuleDto.getProjectDtoList();
    }

    /**
     * 关于我们-业务范围
     * @return
     */
    private List<ProjectDto> getBusinessScopeProjectDtoList(){
        List<ModuleDto> businessScopeModuleList = this.moduleService.getModuleDtoList("BusinessScope");
        ModuleDto businessScopeModuleDto = businessScopeModuleList.get(0);
        List<ProjectDto> businessScopeProjectDtoList = businessScopeModuleDto.getProjectDtoList();
        if(CollectionUtils.isEmpty(businessScopeProjectDtoList)){
            return Lists.newArrayList();
        }

        for (ProjectDto projectDto : businessScopeProjectDtoList){
            if(StringUtils.isNotBlank(projectDto.getContent())){
                projectDto.setContent(Utils.htmlDecode(projectDto.getContent()));
            }
        }
        return businessScopeProjectDtoList;
    }

    /**
     * 关于我们-公司优势
     * @return
     */
    private List<ProjectDto> getCompanyAdvantageProjectDtoList(){
        List<ModuleDto> companyAdvantageModuleList = this.moduleService.getModuleDtoList("CompanyAdvantage");
        if(CollectionUtils.isEmpty(companyAdvantageModuleList)){
            return Lists.newArrayList();
        }
        return companyAdvantageModuleList.get(0).getProjectDtoList();
    }

    /**
     * 关于我们-荣誉资质
     * @return
     */
    private List<ProjectDto> getHonoraryQualificationProjectDtoList(){
        List<ModuleDto> honoraryQualificationModuleList = this.moduleService.getModuleDtoList("HonoraryQualification");
        ModuleDto honoraryQualificationModuleDto = honoraryQualificationModuleList.get(0);
        List<ProjectDto> honoraryQualificationProjectDtoList = honoraryQualificationModuleDto.getProjectDtoList();
        return honoraryQualificationProjectDtoList;
    }

    /**
     * 荣誉资质分屏
     * @param honoraryQualificationProjectDtoList
     * @return
     */
    private Map<String, List<ProjectDto>> getHonoraryQualificationMap(List<ProjectDto> honoraryQualificationProjectDtoList){
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
        return map;
    }

}
