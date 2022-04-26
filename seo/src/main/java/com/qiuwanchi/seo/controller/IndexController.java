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
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM,dd");

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

        // 2banner列表
        List<ModuleDto> bannerModuleList = this.moduleService.getModuleDtoList("FirstBanner");
        if(!CollectionUtils.isEmpty(bannerModuleList)){
            ModuleDto bannerModule = bannerModuleList.get(0);
            model.addAttribute("bannerProjectList", CollectionUtils.isEmpty(bannerModule.getProjectDtoList()) ? new ArrayList<>() : bannerModule.getProjectDtoList());
        }

        // 3公司产品(查询后台设置置顶的类目，每个类目查询8条数据)
        List<ModuleDto> productModuleList = this.moduleService.getIndexModuleDtoList("1","companyProduct-productModule", 8);
        model.addAttribute("productModuleList", productModuleList);

        // 3公司产品更多按钮
        List<ModuleDto> productMoreModuleList = this.moduleService.getModuleDtoList("FirstPage-CompanyProduct-more");
        ModuleDto productMoreModule = productMoreModuleList.get(0);
        List<ProjectDto> productMoreProjectList = productMoreModule.getProjectDtoList();
        model.addAttribute("productMoreProjectList", productMoreProjectList);

        // 4.服务案例(查询后台设置置顶的类目,)
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

        model.addAttribute("serviceCaseModuleList", serviceCaseModuleList);

        // 5.服务案例更多按钮
        List<ModuleDto> serviceCaseMoreModuleList = this.moduleService.getModuleDtoList("FirstPage-ServiceCase-more");
        ModuleDto serviceCaseMoreModule = serviceCaseMoreModuleList.get(0);
        List<ProjectDto> serviceCaseMoreProjectList = serviceCaseMoreModule.getProjectDtoList();
        model.addAttribute("serviceCaseMoreProjectList", serviceCaseMoreProjectList);

//        // 5视频案例
//        List<ModuleDto> videoCaseModuleList = this.moduleService.getModuleDtoList("VideoCase");
//        if(!CollectionUtils.isEmpty(videoCaseModuleList)){
//            List<ProjectDto> videoCaseProjectList = videoCaseModuleList.get(0).getProjectDtoList();
//            model.addAttribute("videoCaseProjectList", videoCaseProjectList);
//        }

        // 6公司优势
        List<ModuleDto> companyAdvantageModuleList = this.moduleService.getModuleDtoList("CompanyAdvantage");
        if(!CollectionUtils.isEmpty(companyAdvantageModuleList)){
            List<ProjectDto> companyAdvantageProjectList = companyAdvantageModuleList.get(0).getProjectDtoList();
            model.addAttribute("companyAdvantageProjectList", companyAdvantageProjectList);
        }

        // 7.解决方案
        List<ProjectDto> solutionCaseProjectList = this.projectService.getSolutionCaseList("1", 4);
        SeoUtils.intProjectSeoValue(solutionCaseProjectList);
        model.addAttribute("solutionCaseProjectList", solutionCaseProjectList);

        // 8维护服务
        List<ModuleDto> maintenanceServicesModuleList = this.moduleService.getModuleDtoList("MaintenanceServices");
        if(!CollectionUtils.isEmpty(maintenanceServicesModuleList)){
            List<ProjectDto> maintenanceServicesProjectList = maintenanceServicesModuleList.get(0).getProjectDtoList();
            model.addAttribute("maintenanceServicesProjectList", maintenanceServicesProjectList);
        }

        //9 新闻资讯
        List<ModuleDto> newsModuleList = this.moduleService.getIndexModuleDtoList(null,"News", 6);
        if(!CollectionUtils.isEmpty(newsModuleList)){
            for (ModuleDto moduleDto : newsModuleList){
                if(!CollectionUtils.isEmpty(moduleDto.getProjectDtoList())){
                    for (ProjectDto projectDto : moduleDto.getProjectDtoList()){
                        String str = sdf.format(projectDto.getCreateTime());
                        String[] arrStr = str.split(",");
                        projectDto.setYears(arrStr[0]);
                        projectDto.setDay(arrStr[1]);
                    }
                }

            }
        }

        model.addAttribute("newsModuleList", newsModuleList);

        // 底部
        this.bottomManagementCommon.bottom(model);
        return "index";
    }

}
