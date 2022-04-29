package com.qiuwanchi.seo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qiuwanchi.seo.dto.ProjectDto;
import com.qiuwanchi.seo.service.IAttachmentService;
import com.qiuwanchi.seo.service.IBannerService;
import com.qiuwanchi.seo.service.IModuleService;
import com.qiuwanchi.seo.service.IProjectService;
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

    private static final long PAGE_SIZE = 10;


    @GetMapping("/news/search")
    public String search(Model model, String keyword, String currentPage){
        Page page = new Page();
        page.setSize(PAGE_SIZE);
        return this.search(model, keyword, page);
    }

    @GetMapping("/news/search-{currentPage}")
    public String searchPage(Model model, String keyword, String currentPage){
        Page page = new Page();
        page.setSize(PAGE_SIZE);
        try {
            page.setCurrent(Long.parseLong(currentPage));
        }catch (Exception e){

        }
        return this.search(model, keyword, page);
    }

    private String search(Model model, String keyword, Page page){
        model.addAttribute("baseUrl", serverConfig.getUrl());
        // logo
        this.logoCommon.logo(model);
        Page<ProjectDto> projectDtoPage = this.projectService.newsSearch(page, keyword);
        List<ProjectDto> newsSearchProjectDtoList = projectDtoPage.getRecords();

        SeoUtils.intProjectSeoValue(newsSearchProjectDtoList);
        model.addAttribute("newsSearchProjectDtoList", newsSearchProjectDtoList);

        model.addAttribute("page", page);

        String html = NewsSearchGeneratePageUtil.generatePageHtml(page);
        model.addAttribute("pageHtml", html);

        model.addAttribute("searchWords", keyword);

        // 底部
        this.bottomManagementCommon.bottom(model);
        return "news_search";
    }

}
