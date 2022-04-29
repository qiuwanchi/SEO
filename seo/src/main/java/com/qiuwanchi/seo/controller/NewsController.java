package com.qiuwanchi.seo.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qiuwanchi.seo.dto.*;
import com.qiuwanchi.seo.entity.Project;
import com.qiuwanchi.seo.service.*;
import com.qiuwanchi.seo.utils.*;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 新闻资讯
 */
@Log4j2
@Controller
public class NewsController {

    @Autowired
    private FileConfiguration fileConfiguration;

    @Autowired
    private IAttachmentService attachmentService;

    @Autowired
    private IModuleService moduleService;

    @Autowired
    private IProjectService projectService;

    @Autowired
    private ISubProjectService subProjectService;

    @Autowired
    private ServerConfig serverConfig;

    @Autowired
    private BottomManagementCommon bottomManagementCommon;

    @Autowired
    private LogoCommon logoCommon;

    @Autowired
    private ISeoVideoService seoVideoService;

    @Autowired
    private IBannerService bannerService;

    @Autowired
    private VideoCommon videoCommon;


    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM,dd");

    // 默认每页条数
    private static final long PAGE_SIZE = 6;

    @GetMapping("/news.html")
    public String newsIndex(Model model) {
        Page page = new Page();
        page.setSize(PAGE_SIZE);
        String returnStr = this.news(model, page, null);
        this.generatePageHtml(model, page, null);
        return returnStr;
    }

    @GetMapping("/news/page_{current}.html")
    public String newsIndexPage(Model model, @PathVariable("current") long current) {
        Page page = new Page();
        page.setCurrent(current);
        page.setSize(PAGE_SIZE);
        String returnStr = this.news(model, page, null);
        this.generatePageHtml(model, page, null);
        return returnStr;
    }

    @GetMapping("/news/{firstCategory}")
    public String newsFirstCategory(Model model, @PathVariable("firstCategory") String firstCategory) {
        Page page = new Page();
        page.setSize(PAGE_SIZE);
        String returnStr = this.news(model, page, firstCategory);
        this.generatePageHtml(model, page, null);
        return returnStr;
    }

    @GetMapping("/news/{firstCategory}/page_{current}.html")
    public String newsFirstCategoryPage(Model model, @PathVariable("firstCategory") String firstCategory, @PathVariable("current") long current) {
        Page page = new Page();
        page.setCurrent(current);
        page.setSize(PAGE_SIZE);
        String returnStr = this.news(model, page, firstCategory);
        this.generatePageHtml(model, page, null);
        return returnStr;
    }

    private void generatePageHtml(Model model, Page page, String firstCategory){
        String html = NewsGeneratePageUtil.generatePageHtml(page, firstCategory);
        model.addAttribute("pageHtml", html);
    }

    private String news(Model model, Page page, String firstCategory) {
        model.addAttribute("baseUrl", serverConfig.getUrl());
        // 1.logo
        this.logoCommon.logo(model);

        // 2.新闻资讯类目列表
        List<ModuleDto> newsModuleList = this.moduleService.getSimpleModuleDtoList("News");
        model.addAttribute("newsModuleList", newsModuleList);

        // 点击的类目序号
        int clickCategorySort = this.getClickCategorySort(firstCategory, newsModuleList);

        // 类目编码
        model.addAttribute("firstCategory", firstCategory);

        // 点击的序号
        model.addAttribute("clickCategorySort", clickCategorySort);

        ModuleDto newsModule = newsModuleList.get(clickCategorySort);
        newsModule.setUrl(UrlAssemblyUtils.getImageUrl(newsModule.getFilePath()));
        // 所点击的类目对象
        model.addAttribute("newsModule", newsModule);

        // banner图
        BannerDto bannerDto = this.getBanner(newsModule.getBannerId());
        model.addAttribute("bannerDto", bannerDto);

        // 分页查询的此类目下的项目
        Page<ProjectDto> projectDtoPage = this.projectService.getProjectPageListByModuleId(page, newsModule.getId(), Project.UPDATE_TIME, "desc");

        List<ProjectDto> newsProjectList = projectDtoPage.getRecords();
        for (ProjectDto projectDto : newsProjectList) {
            String str = sdf.format(projectDto.getCreateTime());
            String[] arrStr = str.split(",");
            projectDto.setYears(arrStr[0]);
            projectDto.setDay(arrStr[1]);
            projectDto.setMonthDay(arrStr[0].split("-")[1] + "-" + projectDto.getDay());
            projectDto.setUrl(UrlAssemblyUtils.getImageUrl(projectDto.getFilePath()));
        }

        model.addAttribute("page", page);

        model.addAttribute("newsProjectList", newsProjectList);
        this.bottomManagementCommon.bottom(model);
        return "news";
    }

    private BannerDto getBanner(String bannerId){
        BannerDto bannerDto = null;
        if(StringUtils.isNotBlank(bannerId)){
            bannerDto = this.bannerService.selectById(bannerId);
        }
        if(Objects.isNull(bannerDto)){
            bannerDto = new BannerDto();
        }
        SeoUtils.intBannerSeoValue(bannerDto);
        return bannerDto;
    }

    /**
     * 获取点击的类目序号
     * @param firstCategory
     * @param newsModuleList
     * @return
     */
    private int getClickCategorySort(String firstCategory, List<ModuleDto> newsModuleList) {
        int clickCategorySort = 0;
        if (StringUtils.isNotBlank(firstCategory)) {
            for (int i = 0; i < newsModuleList.size(); i++) {
                ModuleDto moduleDto = newsModuleList.get(i);
                if (firstCategory.equals(moduleDto.getCode())) {
                    clickCategorySort = i;
                    break;
                }
            }
        }
        return clickCategorySort;
    }


    @GetMapping("/news/{firstCategory}/{number}.html")
    public String detail(Model model, @PathVariable("firstCategory") String firstCategory, @PathVariable("number") int number){
        model.addAttribute("baseUrl", serverConfig.getUrl());

        // 1.logo
        this.logoCommon.logo(model);

        ProjectDto currentProjectDto = this.projectService.selectByNumber(number);

        String str = sdf.format(currentProjectDto.getCreateTime());
        String[] arrStr = str.split(",");
        currentProjectDto.setYears(arrStr[0]);
        currentProjectDto.setDay(arrStr[1]);
        currentProjectDto.setCreateBy(Objects.isNull(currentProjectDto.getCreateBy()) ? "admin" : currentProjectDto.getCreateBy());

        if(StringUtils.isBlank(currentProjectDto.getContent())){
            currentProjectDto.setContent(StringUtils.EMPTY);
        }else{
            currentProjectDto.setContent(Utils.htmlDecode(currentProjectDto.getContent()));
        }

        List<ProjectDto> list =  new ArrayList();
        list.add(currentProjectDto);
        this.intProjectSeoValue(list);
        model.addAttribute("newsProject", currentProjectDto);

        // 视频
        this.videoCommon.video(model, currentProjectDto.getId());

        List<String> keywordsList = new ArrayList<>();
        if(StringUtils.isNotBlank(currentProjectDto.getKeywords())){
            currentProjectDto.setKeywords(Utils.replaceAll(currentProjectDto.getKeywords()));
            keywordsList = Utils.toList(currentProjectDto.getKeywords());
        }
        // 关键字
        model.addAttribute("keywordsList", keywordsList);

        // 相关推荐
        List<ProjectDto> recommendProjectDtoList = this.projectService.recommend("News", currentProjectDto.getId(), keywordsList);
        model.addAttribute("recommendProjectDtoList", recommendProjectDtoList);

        // 上一篇
        ProjectDto preProject = this.projectService.getPreProject(currentProjectDto.getModuleId(), currentProjectDto.getId(), currentProjectDto.getSort());
        model.addAttribute("preProject", preProject);

        // 下一篇
        ProjectDto nextProject = this.projectService.getNextProject(currentProjectDto.getModuleId(), currentProjectDto.getId(), currentProjectDto.getSort());
        model.addAttribute("nextProject", nextProject);

        // 热门回答
        List<ProjectDto> hotAnswerProjectDtoList = this.projectService.getHotAnswer(currentProjectDto.getModuleCode());
        model.addAttribute("hotAnswerProjectDtoList", hotAnswerProjectDtoList);

        model.addAttribute("keywordsDtoList", KeywordsUtils.getHotLabel());

        this.bottomManagementCommon.bottom(model);
        return "news_detail";
    }

    private void intProjectSeoValue(List<ProjectDto> projectDtoList){
        for (ProjectDto projectDto : projectDtoList){
            if(StringUtils.isNotBlank(projectDto.getFilePath())){
                projectDto.setUrl(UrlAssemblyUtils.getImageUrl(projectDto.getFilePath()));
            }

            if(StringUtils.isBlank(projectDto.getTitle())){
                projectDto.setTitle(projectDto.getName());
            }

            if(StringUtils.isBlank(projectDto.getAlt())){
                projectDto.setAlt(projectDto.getName());
            }

            if(StringUtils.isNotBlank(projectDto.getClickUrl())){
                if(!Utils.isStartsWith(projectDto.getClickUrl(), Utils.HTTP)){
                    projectDto.setClickUrl(Utils.HTTP + projectDto.getClickUrl());
                }
            }
        }
    }

}
