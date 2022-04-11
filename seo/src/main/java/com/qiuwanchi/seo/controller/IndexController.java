package com.qiuwanchi.seo.controller;

import com.qiuwanchi.seo.dto.BannerDto;
import com.qiuwanchi.seo.dto.ModuleDto;
import com.qiuwanchi.seo.dto.ProjectDto;
import com.qiuwanchi.seo.service.IAttachmentService;
import com.qiuwanchi.seo.service.IBannerService;
import com.qiuwanchi.seo.service.IModuleService;
import com.qiuwanchi.seo.service.IProjectService;
import com.qiuwanchi.seo.utils.FileConfiguration;
import com.qiuwanchi.seo.utils.ServerConfig;
import com.qiuwanchi.seo.utils.UrlAssemblyUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Log4j2
@Controller
public class IndexController {

    @Autowired
    private ServerConfig serverConfig;

    @Autowired
    private IBannerService bannerService;

    @Autowired
    private FileConfiguration fileConfiguration;

    @Autowired
    private IAttachmentService attachmentService;

    @Autowired
    private IModuleService moduleService;

    @Autowired
    private IProjectService projectService;

    @GetMapping()
    public String index(Model model){
        model.addAttribute("baseUrl", serverConfig.getUrl());

        // 1.banner列表
        List<BannerDto> logoList = this.bannerService.getBannerList("2");
        for (BannerDto bannerDto : logoList){
            bannerDto.setUrl(UrlAssemblyUtils.getImageUrl(bannerDto.getFilePath()));
        }
        if(!CollectionUtils.isEmpty(logoList)){
            model.addAttribute("logo", logoList.get(0));
        }

        // 1.banner列表
        List<BannerDto> bannerList = this.bannerService.getBannerList("1");
        for (BannerDto bannerDto : bannerList){
            bannerDto.setUrl(UrlAssemblyUtils.getImageUrl(bannerDto.getFilePath()));
        }
        model.addAttribute("bannerList", bannerList);

        // 2.1公司产品-模块列表
        List<ModuleDto> moduleList = this.getModuleDtoList("companyProduct-productModule");
        model.addAttribute("productModuleList", moduleList);

        // 2.2公司产品-模块-项目列表
        if(!CollectionUtils.isEmpty(moduleList)){
            List<ProjectDto> projectDtoList = this.projectService.getProjectListByModuleId(moduleList.get(0).getId());
            model.addAttribute("productModuleProjectList", projectDtoList);
        }

        // 3.1多媒体展厅-模块列表
        List<ModuleDto> showRoomModuleList = this.getModuleDtoList("multi-media-showroom");
        model.addAttribute("showRoomModuleList", showRoomModuleList);

        // 3.2多媒体展厅-模块-项目列表
        if(!CollectionUtils.isEmpty(showRoomModuleList)){
            List<ProjectDto> projectDtoList = this.projectService.getProjectListByModuleId(showRoomModuleList.get(0).getId());
            for (ProjectDto projectDto : projectDtoList){
                projectDto.setUrl(UrlAssemblyUtils.getImageUrl(projectDto.getFilePath()));
            }
            model.addAttribute("showRoomModuleProjectList", projectDtoList);
        }

        // 4.1视频案例-模块列表
        List<ModuleDto> videoCaseModuleList = this.getModuleDtoList("VideoCase");
        // 4.2视频案例-模块-项目列表
        if(!CollectionUtils.isEmpty(videoCaseModuleList)){
            List<ProjectDto> projectDtoList = this.projectService.getProjectListByModuleId(videoCaseModuleList.get(0).getId());
            for (ProjectDto projectDto : projectDtoList){
                projectDto.setUrl(UrlAssemblyUtils.getImageUrl(projectDto.getFilePath()));
            }
            model.addAttribute("videoCaseModuleProjectList", projectDtoList);
        }

        // 4.1公司优势-模块列表
        List<ModuleDto> companyAdvantageModuleList = this.getModuleDtoList("CompanyAdvantage");
        // 4.2公司优势-模块-项目列表
        if(!CollectionUtils.isEmpty(companyAdvantageModuleList)){
            List<ProjectDto> projectDtoList = this.projectService.getProjectListByModuleId(companyAdvantageModuleList.get(0).getId());
            for (ProjectDto projectDto : projectDtoList){
                projectDto.setUrl(UrlAssemblyUtils.getImageUrl(projectDto.getFilePath()));
            }
            model.addAttribute("companyAdvantageModuleProjectList", projectDtoList);
        }

        // 5.1解决方案-模块列表
        List<ModuleDto> solutionCaseModuleList = this.getModuleDtoList("SolutionCase");
        // 5.2解决方案-模块-项目列表
        if(!CollectionUtils.isEmpty(solutionCaseModuleList)){
            List<ProjectDto> projectDtoList = this.projectService.getProjectListByModuleId(solutionCaseModuleList.get(0).getId());
            for (ProjectDto projectDto : projectDtoList){
                projectDto.setUrl(UrlAssemblyUtils.getImageUrl(projectDto.getFilePath()));
            }
            model.addAttribute("solutionCaseModuleProjectList", projectDtoList);
        }

        // 6.1维护服务-模块列表
        List<ModuleDto> maintenanceServicesModuleList = this.getModuleDtoList("MaintenanceServices");
        // 6.2维护服务-模块-项目列表
        if(!CollectionUtils.isEmpty(maintenanceServicesModuleList)){
            List<ProjectDto> projectDtoList = this.projectService.getProjectListByModuleId(maintenanceServicesModuleList.get(0).getId());
            for (ProjectDto projectDto : projectDtoList){
                projectDto.setUrl(UrlAssemblyUtils.getImageUrl(projectDto.getFilePath()));
            }
            model.addAttribute("maintenanceServicesModuleProjectList", projectDtoList);
        }

        //7 新闻资讯

        return "index";
    }

    private List<ModuleDto> getModuleDtoList(String code){
        List<ModuleDto> moduleList = this.moduleService.getModuleList(code);
        for (ModuleDto moduleDto : moduleList){
            if(StringUtils.isNotBlank(moduleDto.getFilePath())){
                moduleDto.setUrl(UrlAssemblyUtils.getImageUrl(moduleDto.getFilePath()));
            }
        }
        return  moduleList;
    }

}
