package com.qiuwanchi.seo.controller;

import com.google.common.collect.Lists;
import com.qiuwanchi.seo.constant.CategoryCode;
import com.qiuwanchi.seo.constant.ThreeElementsOfColumnPageSeoEnum;
import com.qiuwanchi.seo.dto.BannerDto;
import com.qiuwanchi.seo.dto.ModuleDto;
import com.qiuwanchi.seo.dto.ProjectDto;
import com.qiuwanchi.seo.dto.SubProjectDto;
import com.qiuwanchi.seo.service.*;
import com.qiuwanchi.seo.utils.*;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Comparator;
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

    @Autowired
    private IBannerService bannerService;

    /**
     * 栏目页
     * @param model
     * @return
     */
    @GetMapping("/products.html")
    public String products(Model model){
        model.addAttribute("baseUrl", serverConfig.getUrl());

        // logo
        this.logoCommon.logo(model);

        ProjectDto seoProjectDto = this.projectService.selectSeoThreeElements(ThreeElementsOfColumnPageSeoEnum.PRODUCTS.getCode());
        model.addAttribute("seoProjectDto", seoProjectDto);

        // 2.公司产品-模块列表
        List<ModuleDto> moduleList = this.moduleService.getSimpleModuleDtoList("companyProduct-productModule");
        model.addAttribute("productModuleList", moduleList);
        int index = 0;
        ModuleDto moduleDto = moduleList.get(index);

        // 哪一个类目
        model.addAttribute("categoryIndex", index);

        // 类目下的产品列表
        model.addAttribute("projectDtoList", this.projectDtoList(moduleDto));

        // 类目的banner图
        model.addAttribute("banner", this.getFirstCategoryBanner(moduleDto));

        this.bottomManagementCommon.bottom(model);
        return "goods";
    }

    /**
     * 产品类目页
     * @param model
     * @param firstCategory
     * @return
     */
    @GetMapping("/products/{firstCategory}")
    public String productsFirstCategory(Model model, @PathVariable("firstCategory") String firstCategory){
        model.addAttribute("baseUrl", serverConfig.getUrl());

        // logo
        this.logoCommon.logo(model);

        // 2.公司产品-模块列表
        List<ModuleDto> moduleList = this.moduleService.getSimpleModuleDtoList("companyProduct-productModule");
        model.addAttribute("productModuleList", moduleList);
        int index = 0;
        for (ModuleDto moduleDto : moduleList){
            if(moduleDto.getCode().equals(firstCategory)){
                break;
            }
            index++;
        }

        ModuleDto moduleDto = moduleList.get(index);

        // 哪一个类目
        model.addAttribute("categoryIndex", index);

        // 类目下的产品列表
        model.addAttribute("projectDtoList", this.projectDtoList(moduleDto));

        // 类目的banner图
        model.addAttribute("banner", this.getFirstCategoryBanner(moduleDto));

        ProjectDto seoProjectDto = new ProjectDto();
        seoProjectDto.setTitle(moduleDto.getTitle());
        seoProjectDto.setKeywords(moduleDto.getKeywords());
        seoProjectDto.setDescription(moduleDto.getDescription());
        model.addAttribute("seoProjectDto", seoProjectDto);

        model.addAttribute("moduleDto", moduleDto);

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
        BannerDto bannerDto = this.getProjectBanner(currentProjectDto);
        SeoUtils.intBannerSeoValue(bannerDto);
        model.addAttribute("banner", bannerDto);

        // 4.系统特点
        List<ProjectDto> systemCharacteristicsProjectDtoList = this.projectService.getProjectListByModuleId(currentProjectDto.getId());
        SeoUtils.intProjectSeoValue(systemCharacteristicsProjectDtoList);
        model.addAttribute("systemCharacteristicsProjectDtoList", systemCharacteristicsProjectDtoList);

        // 5 应用场景
        List<SubProjectDto> applicationScenarioSubProjectDtoList = this.subProjectService.getProjectListByProjectId(currentProjectDto.getId());
        SeoUtils.intSubProjectSeoValue(applicationScenarioSubProjectDtoList);
        model.addAttribute("applicationScenarioSubProjectDtoList", applicationScenarioSubProjectDtoList);

        // 视频案例
        List<SubProjectDto> recommendServiceCaseVideoList = this.subProjectService.getRecommendServiceCaseSubProjectList(currentProjectDto.getModuleCode());
        SeoUtils.intSubProjectSeoValue(recommendServiceCaseVideoList);
        model.addAttribute("recommendServiceCaseVideoList", recommendServiceCaseVideoList);

        model.addAttribute("firstCategory", firstCategory);
        if(!CollectionUtils.isEmpty(recommendServiceCaseVideoList)){
            SubProjectDto subProjectDto = recommendServiceCaseVideoList.get(0);
            model.addAttribute("secondCategory", subProjectDto.getSecondCategory());
        }

        // 关键字
        List<String> keywordsList = new ArrayList<>();
        if(StringUtils.isNotBlank(currentProjectDto.getKeywords())){
            currentProjectDto.setKeywords(Utils.replaceAll(currentProjectDto.getKeywords()));
            keywordsList = Utils.toList(currentProjectDto.getKeywords());
        }
        // 常见问题
        List<ProjectDto> recommendNewsFqaProjectList = this.projectService.getRecommendNewsFqaProjectList(CategoryCode.FAQ.getCode(), keywordsList);
        model.addAttribute("recommendNewsFqaProjectList", recommendNewsFqaProjectList);

        // 科普知识
        List<ProjectDto> recommendNewsPopularScienceKnowledgeProjectList = this.projectService.getRecommendNewsFqaProjectList(CategoryCode.popular_science_knowledge.getCode(), keywordsList);
        model.addAttribute("recommendNewsPopularScienceKnowledgeProjectList", recommendNewsPopularScienceKnowledgeProjectList);

        // 最近更新
        List<ProjectDto> recentUpdatesNewsProjectList = this.projectService.getRecentUpdatesNewsProjectList();
        model.addAttribute("recentUpdatesNewsProjectList", recentUpdatesNewsProjectList);

        // 推荐产品
        List<ProjectDto> recommendProductsProjectDtoList = this.projectService.recommend("companyProduct-productModule", currentProjectDto.getId(), keywordsList);
        SeoUtils.intProjectSeoValue(recommendProductsProjectDtoList);
        model.addAttribute("recommendProductsProjectDtoList", recommendProductsProjectDtoList);

        // 底部
        this.bottomManagementCommon.bottom(model);

        return "goods_detail";
    }

    /**
     * 获取产品列表
     * @param moduleDto
     * @return
     */
    private List<ProjectDto> projectDtoList(ModuleDto moduleDto){
        List<ProjectDto> projectDtoList = null;
        projectDtoList = this.projectService.getProjectListByModuleId(moduleDto.getId());
        SeoUtils.intProjectSeoValue(projectDtoList);
        if(!CollectionUtils.isEmpty(projectDtoList)){
            projectDtoList.sort(new Comparator<ProjectDto>() {
                @Override
                public int compare(ProjectDto o1, ProjectDto o2) {
                    int a = o1.getSort().compareTo(o2.getSort());
                    if(a == 0){
                        a = o1.getUpdateTime().compareTo(o2.getUpdateTime());
                    }
                    return a;
                }
            });
        }

        if(Objects.isNull(projectDtoList)){
            projectDtoList = Lists.newArrayList();
        }

        return projectDtoList;
    }

    /**
     * 获取Module的banner图
     * @param moduleDto
     * @return
     */
    private BannerDto getFirstCategoryBanner(ModuleDto moduleDto){
        BannerDto bannerDto = this.bannerService.selectById(moduleDto.getBannerId());
        if(Objects.isNull(bannerDto)){
            return new BannerDto();
        }

        SeoUtils.intBannerSeoValue(bannerDto);
        return bannerDto;
    }

    /**
     * 获取Project的banner图
     * @param projectDto
     * @return
     */
    private BannerDto getProjectBanner(ProjectDto projectDto){
        // project没有就取类目的banner图
        BannerDto bannerDto = this.bannerService.selectById(projectDto.getBannerId());
        if(Objects.isNull(bannerDto)){
            ModuleDto moduleDto = this.moduleService.selectByModuleId(projectDto.getModuleId());
            return this.getFirstCategoryBanner(moduleDto);
        }
        return bannerDto;
    }

}
