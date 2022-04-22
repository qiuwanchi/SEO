package com.qiuwanchi.seo.controller;

import com.qiuwanchi.seo.dto.ModuleDto;
import com.qiuwanchi.seo.dto.ProjectDto;
import com.qiuwanchi.seo.dto.SeoImageDto;
import com.qiuwanchi.seo.dto.SubProjectDto;
import com.qiuwanchi.seo.service.*;
import com.qiuwanchi.seo.utils.*;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Objects;

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

    @Autowired
    private LogoCommon logoCommon;

    @Autowired
    private ISeoImageService seoImageService;

    @Autowired
    private ISubProjectService subProjectService;

    @GetMapping("/products.html")
    public String products(Model model){
        model.addAttribute("baseUrl", serverConfig.getUrl());

        // logo
        this.logoCommon.logo(model);

        // 2.公司产品-模块列表
        List<ModuleDto> moduleList = this.moduleService.getModuleDtoList("companyProduct-productModule");
        model.addAttribute("productModuleList", moduleList);

        this.bottomManagementCommon.bottom(model);

        return "goods";
    }

    /**
     * 公司产品详情页
     * @param model
     * @param firstCategory
     * @param number
     * @return
     */
    @GetMapping("/products/{firstCategory}/{number}.html")
    public String productsDetail(Model model, @PathVariable("firstCategory") String firstCategory, @PathVariable("number") int number){
        model.addAttribute("baseUrl", serverConfig.getUrl());

        // 1.logo
        this.logoCommon.logo(model);

        // 2.当前项目
        ProjectDto currentProjectDto = this.projectService.selectByNumber(number);
        SeoUtils.intProjectSeoValue(currentProjectDto);
        model.addAttribute("currentProjectDto", currentProjectDto);

        // 3.banner图片
        SeoImageDto seoImageDto = this.seoImageService.selectById(currentProjectDto.getSeoImageId());
        if(Objects.isNull(seoImageDto)){
            seoImageDto = new SeoImageDto();
            ModuleDto moduleDto = this.moduleService.selectByModuleId(currentProjectDto.getModuleId());
            seoImageDto.setUrl(UrlAssemblyUtils.getImageUrl(moduleDto.getFilePath()));
        }else {
            seoImageDto.setUrl(UrlAssemblyUtils.getImageUrl(seoImageDto.getFilePath()));
        }
        model.addAttribute("seoImageDto", seoImageDto);

        // 4.系统特点
        List<ProjectDto> systemCharacteristicsProjectDtoList = this.projectService.getProjectListByModuleId(currentProjectDto.getId());
        SeoUtils.intProjectSeoValue(systemCharacteristicsProjectDtoList);
        model.addAttribute("systemCharacteristicsProjectDtoList", systemCharacteristicsProjectDtoList);

        // 5 应用场景
        List<SubProjectDto> applicationScenarioSubProjectDtoList = this.subProjectService.getProjectListByProjectId(currentProjectDto.getId());
        SeoUtils.intSubProjectSeoValue(applicationScenarioSubProjectDtoList);
        model.addAttribute("applicationScenarioSubProjectDtoList", applicationScenarioSubProjectDtoList);

        // 底部
        this.bottomManagementCommon.bottom(model);

        return "goods_detail";
    }

}
