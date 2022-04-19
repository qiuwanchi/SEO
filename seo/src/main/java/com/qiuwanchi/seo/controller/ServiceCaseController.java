package com.qiuwanchi.seo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qiuwanchi.seo.dto.ImageDto;
import com.qiuwanchi.seo.dto.ModuleDto;
import com.qiuwanchi.seo.dto.ProjectDto;
import com.qiuwanchi.seo.dto.SubProjectDto;
import com.qiuwanchi.seo.entity.Attachment;
import com.qiuwanchi.seo.entity.Module;
import com.qiuwanchi.seo.entity.Project;
import com.qiuwanchi.seo.service.IAttachmentService;
import com.qiuwanchi.seo.service.IModuleService;
import com.qiuwanchi.seo.service.IProjectService;
import com.qiuwanchi.seo.service.ISubProjectService;
import com.qiuwanchi.seo.utils.*;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
        return serviceCase(model, page, null, null);
    }

    /**
     * 服务案例分页查询
     * @param model
     * @param current (index_1)第几页
     * @return
     */
    @GetMapping("/serviceCase/{current}.html")
    public String serviceCasePage(Model model, @PathVariable("current") String current){
        String[] arr = current.split("_");
        Page page = new Page();
        page.setSize(PAGE_SIZE);
        page.setCurrent(Long.valueOf(arr[1]));
        return serviceCase(model, page, null, null);
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
        return serviceCase(model, page, firstCategory, null);
    }

    /**
     * 服务案例一级类目分页查询
     * @param model
     * @param current
     * @return
     */
    @GetMapping("/serviceCase/{firstCategory}/page_{current}.html")
    public String serviceCaseFirstCategoryPage(Model model, @PathVariable("firstCategory") String firstCategory, @PathVariable("current") String current){
        String[] arr = current.split("_");
        Page page = new Page();
        page.setSize(PAGE_SIZE);
        page.setCurrent(Long.valueOf(arr[1]));
        return serviceCase(model, page, firstCategory, null);
    }

    @GetMapping("/serviceCase/{firstCategory}/{secondCategory}")
    public String serviceCaseSecondCategoryPage0(Model model, @PathVariable("firstCategory") String firstCategory, @PathVariable("secondCategory") String secondCategory){
        Page page = new Page();
        page.setSize(PAGE_SIZE);
        return serviceCase(model, page, firstCategory, secondCategory);
    }

    @GetMapping("/serviceCase/{firstCategory}/{secondCategory}/page_{current}.html")
    public String serviceCaseSecondCategoryPage(Model model, @PathVariable("firstCategory") String firstCategory, @PathVariable("secondCategory") String secondCategory,@PathVariable("current") String current){
        String[] arr = current.split("_");
        Page page = new Page();
        page.setSize(PAGE_SIZE);
        page.setCurrent(Long.valueOf(arr[1]));
        return serviceCase(model, page, firstCategory, secondCategory);
    }


    /**
     * 服务类目-项目详情
     * @param model
     * @param number
     * @return
     */
    @GetMapping("/serviceCase/{firstCategory}/{secondCategory}/{number}.html")
    public String serviceCaseDetail(Model model, @PathVariable("firstCategory") String firstCategory, @PathVariable("secondCategory") String secondCategory, @PathVariable("number") int number){
        model.addAttribute("baseUrl", serverConfig.getUrl());
        // logo
        this.logoCommon.logo(model);
        SubProjectDto currentSubProjectDto = this.subProjectService.getByNumber(number);
        Project project = this.projectService.getById(currentSubProjectDto.getProjectId());
        model.addAttribute("project", project);
        Module module = this.moduleService.getById(project.getModuleId());
        model.addAttribute("module", module);

        currentSubProjectDto.setCreateBy(Objects.isNull(currentSubProjectDto.getCreateBy()) ? "admin" : currentSubProjectDto.getCreateBy());
        if(StringUtils.isBlank(currentSubProjectDto.getContent())){
            currentSubProjectDto.setContent(StringUtils.EMPTY);
        }else{
            currentSubProjectDto.setContent(Utils.htmlDecode(currentSubProjectDto.getContent()));
        }

        if(StringUtils.isNotBlank(currentSubProjectDto.getFilePath())){
            currentSubProjectDto.setUrl(UrlAssemblyUtils.getImageUrl(currentSubProjectDto.getFilePath()));
        }else {

            if(StringUtils.isNotBlank(project.getAttachmentId())){
                Attachment attachment = this.attachmentService.getById(project.getAttachmentId());
                currentSubProjectDto.setUrl(UrlAssemblyUtils.getImageUrl(attachment.getFilepath()));
            }
        }

        model.addAttribute("currentSubProject", currentSubProjectDto);

        List<String> keywordsList = new ArrayList<>();

        if(StringUtils.isNotBlank(currentSubProjectDto.getKeywords())){
            currentSubProjectDto.setKeywords(Utils.replaceAll(currentSubProjectDto.getKeywords()));
            keywordsList = Utils.toList(currentSubProjectDto.getKeywords());
        }
        // 关键字
        model.addAttribute("keywordsList", keywordsList);

        // 相关推荐
        List<SubProjectDto> recommendSubProjectDtoList = this.subProjectService.recommend(currentSubProjectDto.getId(), keywordsList);
        model.addAttribute("recommendSubProjectDtoList", recommendSubProjectDtoList);

        // 上一篇
        SubProjectDto preSubProjectDto = this.subProjectService.getPreSubProject(currentSubProjectDto.getProjectId(), currentSubProjectDto.getId(), currentSubProjectDto.getSort());
        model.addAttribute("preSubProjectDto", preSubProjectDto);

        // 下一篇
        SubProjectDto nextSubProjectDto = this.subProjectService.getNextSubProject(currentSubProjectDto.getProjectId(), currentSubProjectDto.getId(), currentSubProjectDto.getSort());
        model.addAttribute("nextSubProjectDto", nextSubProjectDto);

        this.bottomManagementCommon.bottom(model);

        return "service_case_detail";
    }

    private String serviceCase(Model model, Page page, String firstCategory, String secondCategory) {
        // 1.baseUrl
        model.addAttribute("baseUrl", serverConfig.getUrl());
        // logo
        this.logoCommon.logo(model);

        // 服务案例
        List<ModuleDto> serviceCaseModuleList = this.moduleService.getModuleDtoList("ServiceCase");
        for (ModuleDto moduleDto : serviceCaseModuleList) {
            for (ProjectDto projectDto : moduleDto.getProjectDtoList()) {
                projectDto.setModuleCode(moduleDto.getCode());
            }
        }
        model.addAttribute("serviceCaseModuleList", serviceCaseModuleList);

        //banner
        ImageDto bannerImageDto = this.getServiceCaseBanner(firstCategory, secondCategory, serviceCaseModuleList);
        model.addAttribute("bannerImageDto", bannerImageDto);

        Page<SubProjectDto> subProjectDtoPage = this.subProjectService.getPageList(page, firstCategory, secondCategory);
        List<SubProjectDto> subProjectDtoList = subProjectDtoPage.getRecords();
        for (SubProjectDto subProjectDto : subProjectDtoList) {
            subProjectDto.setUrl(UrlAssemblyUtils.getImageUrl(subProjectDto.getFilePath()));
        }

        model.addAttribute("subProjectList", subProjectDtoList);
        model.addAttribute("page", page);

        model.addAttribute("firstCategory", firstCategory == null ? "" : firstCategory);
        model.addAttribute("secondCategory", secondCategory == null ? "" : secondCategory);

        ModuleDto firstCategoryModuleDto = this.getFirstCategoryModuleDto(firstCategory, serviceCaseModuleList);
        model.addAttribute("firstCategoryModuleDto", firstCategoryModuleDto);

        this.bottomManagementCommon.bottom(model);
        return "service_case";
    }

    private ModuleDto getFirstCategoryModuleDto(String firstCategory, List<ModuleDto> serviceCaseModuleList){

        if (StringUtils.isBlank(firstCategory)){
            return null;
        }

        for (ModuleDto moduleDto : serviceCaseModuleList){
            if (firstCategory.equals(moduleDto.getCode())) {
                return moduleDto;
            }
        }

        return null;
    }

    private ImageDto getServiceCaseBanner(String firstCategory, String secondCategory, List<ModuleDto> serviceCaseModuleList){
        ImageDto imageDto = new ImageDto();

        ModuleDto firstModule = serviceCaseModuleList.get(0);
        imageDto.setUrl(firstModule.getUrl());
        imageDto.setAlt(firstModule.getAlt());

        if (StringUtils.isNotBlank(firstCategory)) {
            ModuleDto firstCategoryModuleDto = null;
            for (ModuleDto moduleDto : serviceCaseModuleList) {
                if (firstCategory.equals(moduleDto.getCode())) {
                    firstCategoryModuleDto = moduleDto;
                    break;
                }
            }

            if(Objects.nonNull(firstCategoryModuleDto)){
                imageDto.setUrl(firstCategoryModuleDto.getUrl());
                imageDto.setAlt(firstCategoryModuleDto.getAlt());

                if (StringUtils.isNotBlank(secondCategory)) {
                    ProjectDto secondCategoryProjectDto = null;
                    for (ProjectDto projectDto : firstCategoryModuleDto.getProjectDtoList()){
                        if (secondCategory.equals(projectDto.getCode())) {
                            secondCategoryProjectDto = projectDto;
                            break;
                        }
                    }

                    if(Objects.nonNull(secondCategoryProjectDto)){
                        imageDto.setUrl(secondCategoryProjectDto.getUrl());
                        imageDto.setAlt(secondCategoryProjectDto.getAlt());
                    }
                }
            }
        }
        return imageDto;
    }


}
