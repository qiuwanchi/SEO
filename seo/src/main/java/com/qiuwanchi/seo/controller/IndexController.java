package com.qiuwanchi.seo.controller;

import com.qiuwanchi.seo.dto.ModuleDto;
import com.qiuwanchi.seo.dto.ProjectDto;
import com.qiuwanchi.seo.service.IAttachmentService;
import com.qiuwanchi.seo.service.IModuleService;
import com.qiuwanchi.seo.service.IProjectService;
import com.qiuwanchi.seo.utils.FileConfiguration;
import com.qiuwanchi.seo.utils.ServerConfig;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM,dd");

    @GetMapping("")
    public String index2(Model model){
        return index(model);
    }

    @GetMapping("/index.html")
    public String index(Model model){
        // 基本路径
        model.addAttribute("baseUrl", serverConfig.getUrl());

        // 1.logo
        List<ModuleDto> logoModuleList = this.moduleService.getModuleDtoList("LOGO");
        ModuleDto logoModuleDto = logoModuleList.get(0);
        ProjectDto logoProject = CollectionUtils.isEmpty(logoModuleDto.getProjectDtoList()) ? new ProjectDto() : logoModuleDto.getProjectDtoList().get(0);
        model.addAttribute("logoProject", logoProject);

        // 2banner列表
        List<ModuleDto> bannerModuleList = this.moduleService.getModuleDtoList("FirstBanner");
        if(!CollectionUtils.isEmpty(bannerModuleList)){
            ModuleDto bannerModule = bannerModuleList.get(0);
            model.addAttribute("bannerProjectList", CollectionUtils.isEmpty(bannerModule.getProjectDtoList()) ? new ArrayList<>() : bannerModule.getProjectDtoList());
        }

        // 3公司产品
        List<ModuleDto> productModuleList = this.moduleService.getModuleDtoList("companyProduct-productModule");
        model.addAttribute("productModuleList", productModuleList);

        // 4多媒体展厅
        List<ModuleDto> showRoomModuleList = this.moduleService.getModuleDtoList("multi-media-showroom");
        model.addAttribute("showRoomModuleList", showRoomModuleList);

        // 5视频案例
        List<ModuleDto> videoCaseModuleList = this.moduleService.getModuleDtoList("VideoCase");
        if(!CollectionUtils.isEmpty(videoCaseModuleList)){
            List<ProjectDto> videoCaseProjectList = videoCaseModuleList.get(0).getProjectDtoList();
            model.addAttribute("videoCaseProjectList", videoCaseProjectList);
        }

        // 6公司优势
        List<ModuleDto> companyAdvantageModuleList = this.moduleService.getModuleDtoList("CompanyAdvantage");
        if(!CollectionUtils.isEmpty(companyAdvantageModuleList)){
            List<ProjectDto> companyAdvantageProjectList = companyAdvantageModuleList.get(0).getProjectDtoList();
            model.addAttribute("companyAdvantageProjectList", companyAdvantageProjectList);
        }

        // 7解决方案
        List<ModuleDto> solutionCaseModuleList = this.moduleService.getModuleDtoList("SolutionCase");
        if(!CollectionUtils.isEmpty(solutionCaseModuleList)){
            List<ProjectDto> solutionCaseProjectList = solutionCaseModuleList.get(0).getProjectDtoList();
            model.addAttribute("solutionCaseProjectList", solutionCaseProjectList);
        }

        // 8维护服务
        List<ModuleDto> maintenanceServicesModuleList = this.moduleService.getModuleDtoList("MaintenanceServices");
        if(!CollectionUtils.isEmpty(maintenanceServicesModuleList)){
            List<ProjectDto> maintenanceServicesProjectList = maintenanceServicesModuleList.get(0).getProjectDtoList();
            model.addAttribute("maintenanceServicesProjectList", maintenanceServicesProjectList);
        }

        //9 新闻资讯
        List<ModuleDto> newsModuleList = this.moduleService.getModuleDtoList("News");
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

        return "index";
    }

}
