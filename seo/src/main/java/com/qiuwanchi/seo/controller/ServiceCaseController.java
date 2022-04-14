package com.qiuwanchi.seo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qiuwanchi.seo.dto.ModuleDto;
import com.qiuwanchi.seo.dto.ProjectDto;
import com.qiuwanchi.seo.entity.Project;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    private IBannerService bannerService;

    @Autowired
    private IModuleService moduleService;

    @Autowired
    private IProjectService projectService;

    /**
     * 服务案例类目
     * @param model
     * @return
     */
    @GetMapping("/serviceCase.html")
    public String serviceCase(Model model){
        // 1.baseUrl
        model.addAttribute("baseUrl", serverConfig.getUrl());

        //
        List<ModuleDto> serviceCaseModuleList = this.getModuleDtoList("ServiceCase");
        model.addAttribute("serviceCaseModuleList", serviceCaseModuleList);

        ModuleDto moduleDto = serviceCaseModuleList.get(0);
        Page page = new Page();
        page.setSize(2);
        Page<ProjectDto> projectDtoPage = this.projectService.getProjectPageListByModuleId(page, moduleDto.getId());

        List<ProjectDto> projectDtoList = projectDtoPage.getRecords();
        for (ProjectDto projectDto : projectDtoList){
            projectDto.setUrl(UrlAssemblyUtils.getImageUrl(projectDto.getFilePath()));
        }
        moduleDto.setProjectDtoList(projectDtoList);

        model.addAttribute("serviceCaseModule", moduleDto);

        model.addAttribute("page", page);

        return "service_case";
    }

    /**
     * 分页查询
     * @param moduleId 模块id
     * @param currentPage 第几页
     * @return
     */
    @GetMapping("/serviceCase-page")
    @ResponseBody
    public Page<ProjectDto> serviceCasePage(@RequestParam("moduleId") String moduleId, @RequestParam("currentPage") long currentPage){
        Page page = new Page();
        page.setSize(2);
        page.setCurrent(currentPage);
        Page<ProjectDto> projectDtoPage = this.projectService.getProjectPageListByModuleId(page, moduleId);
        List<ProjectDto> projectDtoList = projectDtoPage.getRecords();
        for (ProjectDto projectDto : projectDtoList){
            projectDto.setUrl(UrlAssemblyUtils.getImageUrl(projectDto.getFilePath()));
        }
        return projectDtoPage;
    }

    /**
     * 查询总数
     * @param moduleId 模块id
     * @return
     */
    @GetMapping("/serviceCase/count")
    @ResponseBody
    public Integer count(@RequestParam("moduleId") String moduleId){
        QueryWrapper<Project> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Project.MODULE_ID, moduleId);
        return this.projectService.count(queryWrapper);
    }

    /**
     * 服务类目-项目详情
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/serviceCase/{id}.html")
    public String serviceCaseDetail(Model model, @PathVariable("id") String id){
        // 1.baseUrl
        model.addAttribute("baseUrl", serverConfig.getUrl());
        ProjectDto projectDto = new ProjectDto();
        Project project = this.projectService.getById(id);
        projectDto.setId(project.getId());
        projectDto.setName(project.getName());
        projectDto.setCreateTime(project.getCreateTime());
        projectDto.setCreateBy(Objects.isNull(project.getCreateBy()) ? "admin" : project.getCreateBy());
        model.addAttribute("currentProject", projectDto);

        // 上一篇
        ProjectDto preProject = this.projectService.getPreProject(project.getModuleId(), project.getSort());
        model.addAttribute("preProject", preProject);

        // 下一篇
        ProjectDto nextProject = this.projectService.getNextProject(project.getModuleId(), project.getSort());
        model.addAttribute("nextProject", nextProject);

        return "service_case_detail";
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
