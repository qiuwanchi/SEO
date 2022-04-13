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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM,dd");

    @GetMapping()
    public String index(Model model){
        model.addAttribute("baseUrl", serverConfig.getUrl());

        // 1.1logo
        List<ModuleDto> logoModuleList = this.getModuleDtoList("LOGO");
        // 1.2logo
        this.intiProject(logoModuleList);
        ModuleDto logo = logoModuleList.get(0);
        model.addAttribute("logo", CollectionUtils.isEmpty(logo.getProjectDtoList()) ? new ProjectDto() : logo.getProjectDtoList().get(0));

        // 2.1banner列表
        List<ModuleDto> bannerModuleList = this.getModuleDtoList("FirstBanner");
        // 2.2banner列表
        this.intiProject(bannerModuleList);
        ModuleDto banner = bannerModuleList.get(0);
        model.addAttribute("bannerList", CollectionUtils.isEmpty(banner.getProjectDtoList()) ? new ArrayList<>() : banner.getProjectDtoList());

        // 2.1公司产品-模块列表
        List<ModuleDto> moduleList = this.getModuleDtoList("companyProduct-productModule");
        // 2.2公司产品-模块-项目列表
        this.intiProject(moduleList);
        model.addAttribute("productModuleList", moduleList);

        // 3.1多媒体展厅-模块列表
        List<ModuleDto> showRoomModuleList = this.getModuleDtoList("multi-media-showroom");
        // 3.2多媒体展厅-模块-项目列表
        this.intiProject(showRoomModuleList);
        model.addAttribute("showRoomModuleList", showRoomModuleList);

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

        //7.1 新闻资讯-模块列表
        List<ModuleDto> newsModuleList = this.getModuleDtoList("News");
        this.intiProject(newsModuleList);
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

    private List<ModuleDto> getModuleDtoList(String code){
        List<ModuleDto> moduleList = this.moduleService.getModuleList(code);
        for (ModuleDto moduleDto : moduleList){
            if(StringUtils.isNotBlank(moduleDto.getFilePath())){
                moduleDto.setUrl(UrlAssemblyUtils.getImageUrl(moduleDto.getFilePath()));
            }
        }
        return  moduleList;
    }

    private void intiProject(List<ModuleDto> moduleList){
        if(!CollectionUtils.isEmpty(moduleList)){
            for (ModuleDto moduleDto : moduleList){
                List<ProjectDto> projectDtoList = this.projectService.getProjectListByModuleId(moduleDto.getId());
                for (ProjectDto projectDto : projectDtoList){
                    projectDto.setUrl(UrlAssemblyUtils.getImageUrl(projectDto.getFilePath()));
                }
                moduleDto.setProjectDtoList(projectDtoList);
            }
        }
    }
}
