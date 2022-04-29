package com.qiuwanchi.seo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.qiuwanchi.seo.constant.CategoryCode;
import com.qiuwanchi.seo.dto.BannerDto;
import com.qiuwanchi.seo.dto.ModuleDto;
import com.qiuwanchi.seo.dto.ProjectDto;
import com.qiuwanchi.seo.entity.Project;
import com.qiuwanchi.seo.service.IAttachmentService;
import com.qiuwanchi.seo.service.IBannerService;
import com.qiuwanchi.seo.service.IModuleService;
import com.qiuwanchi.seo.service.IProjectService;
import com.qiuwanchi.seo.utils.*;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Objects;

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
    public String solutionPage(Model model, @PathVariable("current") long current){
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
        ProjectDto bannerProjectDto = this.getSolutionBanner();
        model.addAttribute("bannerProjectDto", bannerProjectDto);

        model.addAttribute("page", page);

        String html = SolutionGeneratePageUtil.generatePageHtml(page);
        model.addAttribute("pageHtml", html);

        return "solution";
    }

    private ProjectDto getSolutionBanner(){
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

        return bannerProjectDto;
    }

    @GetMapping("/solution/{number}.html")
    public String solutionDetail(Model model, @PathVariable("number") int number){
        model.addAttribute("baseUrl", serverConfig.getUrl());
        // 1.logo
        this.logoCommon.logo(model);

        ProjectDto currentProjectDto = this.projectService.selectByNumber(number);
        SeoUtils.intProjectSeoValue(currentProjectDto);
        currentProjectDto.setCreateBy(Objects.isNull(currentProjectDto.getCreateBy()) ? "admin" : currentProjectDto.getCreateBy());
        currentProjectDto.setKeywords(Utils.replaceAll(currentProjectDto.getKeywords()));
        model.addAttribute("currentProjectDto", currentProjectDto);

        BannerDto bannerDto;
        if(StringUtils.isNotBlank(currentProjectDto.getBannerId())){
            bannerDto = this.bannerService.selectById(currentProjectDto.getBannerId());
            SeoUtils.intBannerSeoValue(bannerDto);
        } else {
            ProjectDto bannerProjectDto = this.getSolutionBanner();
            bannerDto = new BannerDto();
            BeanUtils.copyProperties(bannerProjectDto, bannerDto);
        }
        model.addAttribute("banner", bannerDto);

        // 关键字
        List<String> keywordsList = Utils.toList(currentProjectDto.getKeywords());
        model.addAttribute("keywordsList", keywordsList);

        // 相关推荐(在服务案例所有中随机取6条) TODO
        model.addAttribute("recommendSubProjectDtoList", Lists.newArrayList());

        // 常见问题(热门回答)
        List<ProjectDto> recommendNewsFqaProjectList = this.projectService.getRecommendNewsFqaProjectList(CategoryCode.FAQ.getCode(), keywordsList);
        model.addAttribute("recommendNewsFqaProjectList", recommendNewsFqaProjectList);

        // 上一篇
        ProjectDto preProjectDto = this.projectService.getPreProject(currentProjectDto.getModuleId(), currentProjectDto.getId(), currentProjectDto.getSort());
        model.addAttribute("preProjectDto", preProjectDto);

        // 下一篇
        ProjectDto nextProjectDto = this.projectService.getNextProject(currentProjectDto.getModuleId(), currentProjectDto.getId(), currentProjectDto.getSort());
        model.addAttribute("nextProjectDto", nextProjectDto);

        // 热门标签
        model.addAttribute("keywordsDtoList", KeywordsUtils.getHotLabel());

        // 底部
        this.bottomManagementCommon.bottom(model);
        return "solution_detail";
    }

}
