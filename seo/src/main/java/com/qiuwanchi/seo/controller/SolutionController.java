package com.qiuwanchi.seo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qiuwanchi.seo.dto.ModuleDto;
import com.qiuwanchi.seo.dto.ProjectDto;
import com.qiuwanchi.seo.entity.Project;
import com.qiuwanchi.seo.service.IAttachmentService;
import com.qiuwanchi.seo.service.IBannerService;
import com.qiuwanchi.seo.service.IModuleService;
import com.qiuwanchi.seo.service.IProjectService;
import com.qiuwanchi.seo.utils.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Log4j2
@Controller
public class SolutionController {

    @Autowired
    private FileConfiguration fileConfiguration;

    @Autowired
    private IAttachmentService attachmentService;

    @Autowired
    private ServerConfig serverConfig;

    @Autowired
    private IModuleService moduleService;

    @Autowired
    private IProjectService projectService;

    @Autowired
    private BottomManagementCommon bottomManagementCommon;

    @Autowired
    private LogoCommon logoCommon;

    @Autowired
    private IBannerService bannerService;

    private static final long PAGE_SIZE = 8;

    @GetMapping("/solution.html")
    public String solution(Model model){
        Page page = new Page();
        page.setSize(PAGE_SIZE);
        return this.solution(model, page);
    }

    @GetMapping("/solution/page_{current}.html")
    public String serviceCasePage(Model model, @PathVariable("current") long current){
        Page page = new Page();
        page.setSize(PAGE_SIZE);
        page.setCurrent(current);
        return this.solution(model, page);
    }

    private String solution(Model model, Page page){
        model.addAttribute("baseUrl", serverConfig.getUrl());
        // 1.logo
        this.logoCommon.logo(model);
        // 底部
        this.bottomManagementCommon.bottom(model);

        // 解决方案
        List<ModuleDto> solutionCaseModuleList = this.moduleService.getModuleDtoList("SolutionCase");
        ModuleDto solutionCaseModuleDto = solutionCaseModuleList.get(0);
        Page<ProjectDto> projectDtoPage = this.projectService.getProjectPageListByModuleId(page, solutionCaseModuleDto.getId(), Project.SORT, "asc");
        List<ProjectDto> solutionCaseProjectDtoList = projectDtoPage.getRecords();
        SeoUtils.intProjectSeoValue(solutionCaseProjectDtoList);
        model.addAttribute("solutionCaseProjectDtoList", solutionCaseProjectDtoList);

        // 解决方案的banner
        List<ModuleDto> bannerModuleDtoList = this.moduleService.getModuleDtoList("SolutionCase-Banner");
        ModuleDto bannerModuleDto = bannerModuleDtoList.get(0);
        List<ProjectDto> projectDtoList = bannerModuleDto.getProjectDtoList();
        ProjectDto bannerProjectDto;
        if(!CollectionUtils.isEmpty(projectDtoList)){
            bannerProjectDto = projectDtoList.get(0);
        }else {
            bannerProjectDto = new ProjectDto();
        }
        SeoUtils.intProjectSeoValue(bannerProjectDto);
        model.addAttribute("bannerProjectDto", bannerProjectDto);

        model.addAttribute("page", page);

        String html = SolutionGeneratePageUtil.generatePageHtml(page);
        model.addAttribute("pageHtml", html);

        return "solution";
    }

}
