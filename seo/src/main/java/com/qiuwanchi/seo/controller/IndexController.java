package com.qiuwanchi.seo.controller;

import com.google.common.collect.Lists;
import com.qiuwanchi.seo.dto.ModuleDto;
import com.qiuwanchi.seo.dto.ProjectDto;
import com.qiuwanchi.seo.dto.SubProjectDto;
import com.qiuwanchi.seo.service.IAttachmentService;
import com.qiuwanchi.seo.service.IModuleService;
import com.qiuwanchi.seo.service.IProjectService;
import com.qiuwanchi.seo.service.ISubProjectService;
import com.qiuwanchi.seo.utils.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 首页
 * @author ex_qiuwc1
 */
@Log4j2
@Controller
public class IndexController {

    @Autowired
    private ServerConfig serverConfig;

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
    private BottomManagementCommon bottomManagementCommon;

    @Autowired
    private LogoCommon logoCommon;

    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM,dd");

    @GetMapping("")
    public String index2(Model model){
        return index(model);
    }
    @GetMapping("/")
    public String index3(Model model){
        return index(model);
    }

    @GetMapping("/index.html")
    public String index(Model model){
        // 基本路径
        model.addAttribute("baseUrl", serverConfig.getUrl());
        // logo
        this.logoCommon.logo(model);

        // 1.首页banner列表
        model.addAttribute("bannerProjectDtoList", this.getIndexBannerProjectDtoList());

        // 2.公司热门产品(查询后台设置置顶的类目，每个类目查询8条数据)
        model.addAttribute("productModuleDtoList", this.getIndexHostProductModuleDtoList());

        // 3.公司产品更多按钮
        model.addAttribute("productMoreProjectDtoList", this.getIndexHostProductMoreProjectDtoList());

        // 4.服务案例(查询后台设置置顶的类目)
        model.addAttribute("serviceCaseModuleDtoList", this.getIndexServiceCaseModuleDtoList());

        // 5.服务案例更多按钮
        model.addAttribute("serviceCaseMoreProjectDtoList", this.getIndexServiceCaseMoreProjectDtoList());

        // 6公司优势
        model.addAttribute("companyAdvantageProjectDtoList", this.getIndexCompanyAdvantageProjectDtoList());

        // 7.解决方案
        model.addAttribute("solutionCaseProjectDtoList", this.getIndexSolutionCaseProjectDtoList());

        // 8.维护服务
        model.addAttribute("maintenanceServicesProjectDtoList", this.getIndexMaintenanceServicesProjectDtoList());

        // 9.新闻资讯
        model.addAttribute("newsModuleDtoList", this.getIndexNewsModuleDtoList());

        // 底部
        this.bottomManagementCommon.bottom(model);
        return "index";
    }

    /**
     * 1.首页banner图
     * @return
     */
    private List<ProjectDto> getIndexBannerProjectDtoList(){
        List<ModuleDto> bannerModuleList = this.moduleService.getModuleDtoList("FirstBanner");
        List<ProjectDto> bannerProjectList = null;
        if(CollectionUtils.isEmpty(bannerModuleList)){
            bannerProjectList = Lists.newArrayList();
        }else {
            ModuleDto bannerModule = bannerModuleList.get(0);
            bannerProjectList = CollectionUtils.isEmpty(bannerModule.getProjectDtoList()) ? Lists.newArrayList() : bannerModule.getProjectDtoList();
        }
        return bannerProjectList;
    }

    /**
     * 2.公司热门产品(查询后台设置置顶的类目，每个类目查询8条数据)
     * @return
     */
    private List<ModuleDto> getIndexHostProductModuleDtoList(){
        return this.moduleService.getIndexTopModuleDtoList("1","companyProduct-productModule", 8);
    }

    /**
     * 3.公司热门产品更多按钮
     * @return
     */
    private List<ProjectDto> getIndexHostProductMoreProjectDtoList(){
        // 最多只会有一条
        List<ModuleDto> productMoreModuleList = this.moduleService.getModuleDtoList("FirstPage-CompanyProduct-more");
        List<ProjectDto> productMoreProjectList = null;
        if(CollectionUtils.isEmpty(productMoreModuleList)){
            productMoreProjectList = Lists.newArrayList();
        } else {
            ModuleDto productMoreModule = productMoreModuleList.get(0);
            if(Objects.isNull(productMoreModule.getProjectDtoList())){
                productMoreProjectList = Lists.newArrayList();
            } else {
                productMoreProjectList = productMoreModule.getProjectDtoList();
            }
        }
        return productMoreProjectList;
    }

    /**
     * 4.首页服务案例(查询后台设置置顶的类目)
     * @return
     */
    private List<ModuleDto> getIndexServiceCaseModuleDtoList(){
        List<ModuleDto> serviceCaseModuleList = this.moduleService.getModuleDtoList("1", "ServiceCase");
        List<String> moduleIds = serviceCaseModuleList.stream().map(ModuleDto::getId).collect(Collectors.toList());
        List<SubProjectDto> subProjectDtoList = this.subProjectService.getSubProjectListByModuleIds(moduleIds, 8);
        Map<String, List<SubProjectDto>> subProjectDtoMap = subProjectDtoList.stream().collect(Collectors.toMap(SubProjectDto::getModuleId, a-> Lists.newArrayList(a),(List<SubProjectDto> newValueList, List<SubProjectDto> oldValueList) ->{
            oldValueList.addAll(newValueList);
            return oldValueList;
        }));

        for(ModuleDto moduleDto : serviceCaseModuleList){
            List<SubProjectDto> temSubProjectDtoList = subProjectDtoMap.get(moduleDto.getId());
            if(Objects.isNull(temSubProjectDtoList)){
                temSubProjectDtoList = Lists.newArrayList();
            }
            if(temSubProjectDtoList.size() > 8){
                moduleDto.setSubProjectDtoList(temSubProjectDtoList.subList(0,7));
            }
            moduleDto.setSubProjectDtoList(temSubProjectDtoList);
        }

        return serviceCaseModuleList;
    }

    private List<ProjectDto> getIndexServiceCaseMoreProjectDtoList(){
        List<ModuleDto> serviceCaseMoreModuleList = this.moduleService.getModuleDtoList("FirstPage-ServiceCase-more");
        ModuleDto serviceCaseMoreModule = serviceCaseMoreModuleList.get(0);
        List<ProjectDto> serviceCaseMoreProjectList = serviceCaseMoreModule.getProjectDtoList();

        return serviceCaseMoreProjectList;
    }

    private List<ProjectDto> getIndexCompanyAdvantageProjectDtoList(){
        List<ModuleDto> companyAdvantageModuleList = this.moduleService.getModuleDtoList("CompanyAdvantage");
        List<ProjectDto> companyAdvantageProjectList = null;
        if(!CollectionUtils.isEmpty(companyAdvantageModuleList)){
            companyAdvantageProjectList = companyAdvantageModuleList.get(0).getProjectDtoList();
        }
        return companyAdvantageProjectList;
    }

    private List<ProjectDto> getIndexSolutionCaseProjectDtoList(){
        List<ProjectDto> solutionCaseProjectList = this.projectService.getSolutionCaseList("1", 4);
        SeoUtils.intProjectSeoValue(solutionCaseProjectList);
        return solutionCaseProjectList;
    }

    private List<ProjectDto> getIndexMaintenanceServicesProjectDtoList(){
        List<ModuleDto> maintenanceServicesModuleList = this.moduleService.getModuleDtoList("MaintenanceServices");
        List<ProjectDto> maintenanceServicesProjectList = null;
        if(!CollectionUtils.isEmpty(maintenanceServicesModuleList)){
            maintenanceServicesProjectList = maintenanceServicesModuleList.get(0).getProjectDtoList();
        }
        return maintenanceServicesProjectList;
    }

    private List<ModuleDto> getIndexNewsModuleDtoList(){
        List<ModuleDto> newsModuleList = this.moduleService.getIndexTopModuleDtoList(null,"News", 6);
        if(!CollectionUtils.isEmpty(newsModuleList)){
            for (ModuleDto moduleDto : newsModuleList){
                if(!CollectionUtils.isEmpty(moduleDto.getProjectDtoList())){
                    for (ProjectDto projectDto : moduleDto.getProjectDtoList()){
                        String str = SDF.format(projectDto.getCreateTime());
                        String[] arrStr = str.split(",");
                        projectDto.setYears(arrStr[0]);
                        projectDto.setDay(arrStr[1]);
                    }
                }

            }
        }
        return newsModuleList;
    }

}
