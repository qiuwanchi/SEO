package com.qiuwanchi.seo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qiuwanchi.seo.constant.SearchRange;
import com.qiuwanchi.seo.dto.ProjectDto;
import com.qiuwanchi.seo.dto.SubProjectDto;
import com.qiuwanchi.seo.service.*;
import com.qiuwanchi.seo.utils.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Log4j2
@Controller
public class SearchController {

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

    @Autowired
    private IBannerService bannerService;

    @Autowired
    private IProjectService projectService;

    @Autowired
    private ISubProjectService subProjectService;

    private static final long PAGE_SIZE = 8;

    @GetMapping("/solutionServiceCase/search")
    public String searchSolutionServiceCase(Model model, String keyword){
        Page page = new Page();
        page.setSize(PAGE_SIZE);
        return this.searchServiceCase(model, keyword, page);
    }

    @GetMapping("/solutionServiceCase/search-{currentPage}")
    public String searchSolutionServiceCasePage(Model model, String keyword,  @PathVariable("currentPage") String currentPage){
        Page page = new Page();
        page.setSize(PAGE_SIZE);
        try {
            page.setCurrent(Long.parseLong(currentPage));
        }catch (Exception e){

        }
        return this.searchServiceCase(model, keyword, page);
    }

    @GetMapping("/news/search")
    public String searchNews(Model model, String keyword){
        Page page = new Page();
        page.setSize(PAGE_SIZE);
        return this.searchNews(model, keyword, page);
    }

    @GetMapping("/news/search-{currentPage}")
    public String searchNewsPage(Model model, String keyword, @PathVariable("currentPage")String currentPage){
        Page page = new Page();
        page.setSize(PAGE_SIZE);
        try {
            page.setCurrent(Long.parseLong(currentPage));
        }catch (Exception e){

        }
        return this.searchNews(model, keyword, page);
    }

    @GetMapping("/search")
    public String search(Model model, String keyword){
        Page page = new Page();
        page.setSize(PAGE_SIZE);
        return this.searchAll(model, keyword, page);
    }

    @GetMapping("/search-{currentPage}")
    public String searchPage(Model model, String keyword, @PathVariable("currentPage")String currentPage){
        Page page = new Page();
        page.setSize(PAGE_SIZE);
        try {
            page.setCurrent(Long.parseLong(currentPage));
        }catch (Exception e){

        }
        return this.searchAll(model, keyword, page);
    }

    private String searchNews(Model model, String keyword, Page page){
        model.addAttribute("baseUrl", serverConfig.getUrl());
        // logo
        this.logoCommon.logo(model);

        Page<ProjectDto> projectDtoPage = this.projectService.newsSearch(page, keyword);
        List<ProjectDto> newsSearchProjectDtoList = projectDtoPage.getRecords();
        SeoUtils.intProjectSeoValue(newsSearchProjectDtoList);
        model.addAttribute("newsSearchProjectDtoList", newsSearchProjectDtoList);

        model.addAttribute("page", page);

        String html = NewsSearchGeneratePageUtil.generatePageHtml(page, keyword);
        model.addAttribute("pageHtml", html);

        model.addAttribute("searchWords", keyword);

        // 底部
        this.bottomManagementCommon.bottom(model);
        return "news_search";
    }

    private String searchServiceCase(Model model, String keyword, Page page){
        model.addAttribute("baseUrl", serverConfig.getUrl());
        // logo
        this.logoCommon.logo(model);

        Page<SubProjectDto> subProjectDtoPage = this.subProjectService.search(page, keyword);
        List<SubProjectDto> searchSubProjectDtoList = subProjectDtoPage.getRecords();
        SeoUtils.intSubProjectSeoValue(searchSubProjectDtoList);
        model.addAttribute("searchSubProjectDtoList", searchSubProjectDtoList);

        model.addAttribute("page", page);

        String html = SolutionSearchGeneratePageUtil.generatePageHtml(page, keyword);
        model.addAttribute("pageHtml", html);

        model.addAttribute("searchWords", keyword);
        // 底部
        this.bottomManagementCommon.bottom(model);
        return "service_case_search";
    }

    private String searchAll(Model model, String keyword, Page page){
        model.addAttribute("baseUrl", serverConfig.getUrl());
        // logo
        this.logoCommon.logo(model);

        Page<SubProjectDto> projectDtoPage = this.subProjectService.searchAll(page, keyword);
        List<SubProjectDto> searchProjectDtoList = projectDtoPage.getRecords();
        SeoUtils.intSubProjectSeoValue(searchProjectDtoList);
        model.addAttribute("searchSubProjectDtoList", searchProjectDtoList);

        model.addAttribute("page", page);

        String html = AllSearchGeneratePageUtil.generatePageHtml(page, keyword);
        model.addAttribute("pageHtml", html);

        model.addAttribute("searchWords", keyword);

        // 底部
        this.bottomManagementCommon.bottom(model);
        return "service_case_search";
    }

}
