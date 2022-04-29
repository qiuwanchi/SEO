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

    private static final long PAGE_SIZE = 10;

    @GetMapping("/solutionServiceCase/search")
    public String searchSolutionServiceCase(Model model, String keyword){
        Page page = new Page();
        page.setSize(PAGE_SIZE);
        return this.search(model, keyword, page, SearchRange.SOLUTION_SERVICE_CASE);
    }

    @GetMapping("/solutionServiceCase/search-{currentPage}")
    public String searchSolutionServiceCasePage(Model model, String keyword,  String currentPage){
        Page page = new Page();
        page.setSize(PAGE_SIZE);
        try {
            page.setCurrent(Long.parseLong(currentPage));
        }catch (Exception e){

        }
        return this.search(model, keyword, page, SearchRange.SOLUTION_SERVICE_CASE);
    }

    @GetMapping("/news/search")
    public String searchNews(Model model, String keyword){
        Page page = new Page();
        page.setSize(PAGE_SIZE);
        return this.search(model, keyword, page, SearchRange.NEWS);
    }

    @GetMapping("/news/search-{currentPage}")
    public String searchNewsPage(Model model, String keyword, String currentPage){
        Page page = new Page();
        page.setSize(PAGE_SIZE);
        try {
            page.setCurrent(Long.parseLong(currentPage));
        }catch (Exception e){

        }
        return this.search(model, keyword, page, SearchRange.NEWS);
    }

    private String search(Model model, String keyword, Page page, SearchRange searchRange){
        model.addAttribute("baseUrl", serverConfig.getUrl());
        // logo
        this.logoCommon.logo(model);

        if(SearchRange.NEWS.getCode().equals(searchRange.getCode())){
            Page<ProjectDto> projectDtoPage = this.projectService.newsSearch(page, keyword);
            List<ProjectDto> newsSearchProjectDtoList = projectDtoPage.getRecords();
            SeoUtils.intProjectSeoValue(newsSearchProjectDtoList);
            model.addAttribute("newsSearchProjectDtoList", newsSearchProjectDtoList);
        } else if(SearchRange.SOLUTION_SERVICE_CASE.getCode().equals(searchRange.getCode())){
            Page<SubProjectDto> subProjectDtoPage = this.subProjectService.search(page, keyword);
            List<SubProjectDto> searchSubProjectDtoList = subProjectDtoPage.getRecords();
            SeoUtils.intSubProjectSeoValue(searchSubProjectDtoList);
            model.addAttribute("searchSubProjectDtoList", searchSubProjectDtoList);
        }

        model.addAttribute("page", page);

        String html = NewsSearchGeneratePageUtil.generatePageHtml(page);
        model.addAttribute("pageHtml", html);

        model.addAttribute("searchWords", keyword);

        // 底部
        this.bottomManagementCommon.bottom(model);
        if(SearchRange.NEWS.getCode().equals(searchRange.getCode())){
            return "news_search";
        } else if(SearchRange.SOLUTION_SERVICE_CASE.getCode().equals(searchRange.getCode())){
            return "service_case_search";
        }
        return "news_search";
    }

}
