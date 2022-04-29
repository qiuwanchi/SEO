package com.qiuwanchi.seo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qiuwanchi.seo.constant.CategoryCode;
import com.qiuwanchi.seo.dto.BannerDto;
import com.qiuwanchi.seo.dto.ModuleDto;
import com.qiuwanchi.seo.dto.ProjectDto;
import com.qiuwanchi.seo.dto.SubProjectDto;
import com.qiuwanchi.seo.entity.Attachment;
import com.qiuwanchi.seo.entity.Module;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Log4j2
@Controller
public class ServiceCaseController {

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
    private ISubProjectService subProjectService;

    @Autowired
    private BottomManagementCommon bottomManagementCommon;

    @Autowired
    private IBannerService bannerService;

    @Autowired
    private LogoCommon logoCommon;

    private static final long PAGE_SIZE = 8;

    /**
     * 服务案例栏目
     * @param model
     * @return
     */
    @GetMapping("/serviceCase.html")
    public String serviceCase(Model model){
        Page page = new Page();
        page.setSize(PAGE_SIZE);
        String returnStr = serviceCase(model, page, null, null);
        this.generatePageHtml(model, page, null, null);
        return returnStr;
    }

    /**
     * 服务案例分页查询
     * @param model
     * @param current (index_1)第几页
     * @return
     */
    @GetMapping("/serviceCase/page_{current}.html")
    public String serviceCasePage(Model model, @PathVariable("current") long current){
        Page page = new Page();
        page.setSize(PAGE_SIZE);
        page.setCurrent(current);
        String returnStr = serviceCase(model, page, null, null);
        this.generatePageHtml(model, page, null, null);
        return returnStr;
    }

    /**
     * 服务案例一级类目分页查询
     * @param model
     * @return
     */
    @GetMapping("/serviceCase/{firstCategory}")
    public String serviceCaseFirstCategoryPage0(Model model, @PathVariable("firstCategory") String firstCategory){
        Page page = new Page();
        page.setSize(PAGE_SIZE);
        String returnStr = serviceCase(model, page, firstCategory, null);
        this.generatePageHtml(model, page, firstCategory, null);
        return returnStr;
    }

    /**
     * 服务案例一级类目分页查询
     * @param model
     * @param current
     * @return
     */
    @GetMapping("/serviceCase/{firstCategory}/page_{current}.html")
    public String serviceCaseFirstCategoryPage(Model model, @PathVariable("firstCategory") String firstCategory, @PathVariable("current") long current){
        Page page = new Page();
        page.setSize(PAGE_SIZE);
        page.setCurrent(current);
        String returnStr = serviceCase(model, page, firstCategory, null);
        this.generatePageHtml(model, page, firstCategory, null);
        return returnStr;
    }

    /**
     * 二级类目
     * @param model
     * @param firstCategory
     * @param secondCategory
     * @return
     */
    @GetMapping("/serviceCase/{firstCategory}/{secondCategory}")
    public String serviceCaseSecondCategoryPage0(Model model, @PathVariable("firstCategory") String firstCategory, @PathVariable("secondCategory") String secondCategory){
        Page page = new Page();
        page.setSize(PAGE_SIZE);
        String returnStr = serviceCase(model, page, firstCategory, secondCategory);
        this.generatePageHtml(model, page, firstCategory, secondCategory);
        return returnStr;
    }

    /**
     * 二级类目分页
     * @param model
     * @param firstCategory
     * @param secondCategory
     * @param current
     * @return
     */
    @GetMapping("/serviceCase/{firstCategory}/{secondCategory}/page_{current}.html")
    public String serviceCaseSecondCategoryPage(Model model, @PathVariable("firstCategory") String firstCategory, @PathVariable("secondCategory") String secondCategory,@PathVariable("current") long current){
        Page page = new Page();
        page.setSize(PAGE_SIZE);
        page.setCurrent(current);
        String returnStr = serviceCase(model, page, firstCategory, secondCategory);
        this.generatePageHtml(model, page, firstCategory, secondCategory);
        return returnStr;
    }


    /**
     * 服务案例-详情
     * @param model
     * @param number
     * @return
     */
    @GetMapping("/serviceCase/{firstCategory}/{secondCategory}/{number}.html")
    public String serviceCaseDetail(Model model, @PathVariable("firstCategory") String firstCategory, @PathVariable("secondCategory") String secondCategory, @PathVariable("number") int number){
        model.addAttribute("baseUrl", serverConfig.getUrl());
        // logo
        this.logoCommon.logo(model);

        // 当前详情对象
        SubProjectDto currentSubProjectDto = this.subProjectService.getByNumber(number);
        currentSubProjectDto.setCreateBy(Objects.isNull(currentSubProjectDto.getCreateBy()) ? "admin" : currentSubProjectDto.getCreateBy());
        SeoUtils.intSubProjectSeoValue(currentSubProjectDto);
        currentSubProjectDto.setKeywords(Utils.replaceAll(currentSubProjectDto.getKeywords()));
        model.addAttribute("currentSubProject", currentSubProjectDto);

        // 二级类目
        Project project = this.projectService.getById(currentSubProjectDto.getProjectId());
        model.addAttribute("project", project);

        // 一级类目
        Module module = this.moduleService.getById(project.getModuleId());
        model.addAttribute("module", module);

        // 详情的banner图
        model.addAttribute("banner", this.getDetailBannerDto(module, project, currentSubProjectDto));

        // 关键字
        List<String> keywordsList = Utils.toList(currentSubProjectDto.getKeywords());
        model.addAttribute("keywordsList", keywordsList);

        // 相关推荐(在服务案例所有中随机取6条)
        List<SubProjectDto> recommendSubProjectDtoList = this.subProjectService.recommend(currentSubProjectDto.getId(), keywordsList);
        model.addAttribute("recommendSubProjectDtoList", recommendSubProjectDtoList);

        // 常见问题(热门回答)
        List<ProjectDto> recommendNewsFqaProjectList = this.projectService.getRecommendNewsFqaProjectList(CategoryCode.FAQ.getCode(), keywordsList);
        model.addAttribute("recommendNewsFqaProjectList", recommendNewsFqaProjectList);

        // 上一篇
        SubProjectDto preSubProjectDto = this.subProjectService.getPreSubProject(currentSubProjectDto.getProjectId(), currentSubProjectDto.getId(), currentSubProjectDto.getSort());
        model.addAttribute("preSubProjectDto", preSubProjectDto);

        // 下一篇
        SubProjectDto nextSubProjectDto = this.subProjectService.getNextSubProject(currentSubProjectDto.getProjectId(), currentSubProjectDto.getId(), currentSubProjectDto.getSort());
        model.addAttribute("nextSubProjectDto", nextSubProjectDto);

        // 热门标签
        model.addAttribute("keywordsDtoList", KeywordsUtils.getHotLabel());

        // 底部
        this.bottomManagementCommon.bottom(model);
        return "service_case_detail";
    }

    /**
     * 获取详情的banner图
     * @param module
     * @param project
     * @param currentSubProjectDto
     * @return
     */
    private BannerDto getDetailBannerDto(Module module, Project project, SubProjectDto currentSubProjectDto) {
        if(StringUtils.isNotBlank(currentSubProjectDto.getBannerId())){
            return this.bannerService.selectById(currentSubProjectDto.getBannerId());
        } else if(StringUtils.isNotBlank(project.getBannerId())){
            return this.bannerService.selectById(project.getBannerId());
        } else {
            return this.bannerService.selectById(module.getBannerId());
        }
    }

    private String serviceCase(Model model, Page page, String firstCategory, String secondCategory) {
        // 1.baseUrl
        model.addAttribute("baseUrl", serverConfig.getUrl());
        // logo
        this.logoCommon.logo(model);

        // 服务案例
        List<ModuleDto> serviceCaseModuleList = this.moduleService.getModuleDtoList("ServiceCase");
        model.addAttribute("serviceCaseModuleList", serviceCaseModuleList);

        // banner
        BannerDto bannerDto = this.getServiceCaseBanner(firstCategory, secondCategory, serviceCaseModuleList);
        SeoUtils.intBannerSeoValue(bannerDto);
        model.addAttribute("bannerDto", bannerDto);

        Page<SubProjectDto> subProjectDtoPage = this.subProjectService.getPageList(page, firstCategory, secondCategory);
        List<SubProjectDto> subProjectDtoList = subProjectDtoPage.getRecords();
        for (SubProjectDto subProjectDto : subProjectDtoList) {
            subProjectDto.setUrl(UrlAssemblyUtils.getImageUrl(subProjectDto.getFilePath()));
        }

        model.addAttribute("subProjectList", subProjectDtoList);
        model.addAttribute("page", page);

        int index = 0;
        if(StringUtils.isNotBlank(firstCategory)){
            for (ModuleDto moduleDto : serviceCaseModuleList){
                if(firstCategory.equals(moduleDto.getCode())){
                    break;
                }
                index++;
            }
        }

        model.addAttribute("categoryIndex", index);

        model.addAttribute("firstCategory", firstCategory == null ? "" : firstCategory);
        model.addAttribute("secondCategory", secondCategory == null ? "" : secondCategory);

        ModuleDto firstCategoryModuleDto = this.getFirstCategoryModuleDto(firstCategory, serviceCaseModuleList);
        model.addAttribute("firstCategoryModuleDto", firstCategoryModuleDto);

        if(StringUtils.isNotBlank(secondCategory)){
            List<ProjectDto> projectDtoList = firstCategoryModuleDto.getProjectDtoList();

            ProjectDto secondCategoryProjectDto = null;
            for (ProjectDto projectDto : projectDtoList){
                if(secondCategory.equals(projectDto.getCode())){
                    secondCategoryProjectDto = projectDto;
                    break;
                }
            }
            model.addAttribute("secondCategoryProjectDto", secondCategoryProjectDto);
        }

        this.bottomManagementCommon.bottom(model);
        return "service_case";
    }

    private void generatePageHtml(Model model, Page page, String firstCategory, String secondCategory){
        String html = ServiceCaseGeneratePageUtil.generatePageHtml(page, firstCategory, secondCategory);
        model.addAttribute("pageHtml", html);
    }

    private ModuleDto getFirstCategoryModuleDto(String firstCategory, List<ModuleDto> serviceCaseModuleList){

        if (StringUtils.isBlank(firstCategory)){
            return serviceCaseModuleList.get(0);
        }

        for (ModuleDto moduleDto : serviceCaseModuleList){
            if (firstCategory.equals(moduleDto.getCode())) {
                return moduleDto;
            }
        }

        return null;
    }

    private BannerDto getServiceCaseBanner(String firstCategory, String secondCategory, List<ModuleDto> serviceCaseModuleList){
        BannerDto bannerDto = null;
        // 一级类目编码为空，取第一个类目
        if (StringUtils.isBlank(firstCategory)) {
            ModuleDto firstModule = serviceCaseModuleList.get(0);
            bannerDto = this.getBannerDto(firstModule.getBannerId());
        } else if (StringUtils.isNotBlank(firstCategory) && StringUtils.isBlank(secondCategory)) {
            ModuleDto firstCategoryModuleDto = this.getFirstCategoryModuleDto(firstCategory, serviceCaseModuleList);
            if(Objects.nonNull(firstCategoryModuleDto)){
                bannerDto = this.getBannerDto(firstCategoryModuleDto.getBannerId());
            }
        } else if (StringUtils.isNotBlank(firstCategory) && StringUtils.isNotBlank(secondCategory)) {
            ModuleDto firstCategoryModuleDto = this.getFirstCategoryModuleDto(firstCategory, serviceCaseModuleList);

            if(Objects.nonNull(firstCategoryModuleDto)){
                ProjectDto secondCategoryProjectDto = null;
                for (ProjectDto projectDto : firstCategoryModuleDto.getProjectDtoList()){
                    if (secondCategory.equals(projectDto.getCode())) {
                        secondCategoryProjectDto = projectDto;
                        break;
                    }
                }
                if(Objects.nonNull(secondCategoryProjectDto)){

                    bannerDto = this.getBannerDto(secondCategoryProjectDto.getBannerId());
                    if(Objects.isNull(bannerDto)){
                        bannerDto = this.getBannerDto(firstCategoryModuleDto.getBannerId());
                    }
                }
            }
        }

        if(Objects.nonNull(bannerDto)){
            return bannerDto;
        }

        return new BannerDto();
    }

    private BannerDto getBannerDto(String bannerId){
        BannerDto moduleBannerDto = this.bannerService.selectById(bannerId);
        return moduleBannerDto;
    }


}
