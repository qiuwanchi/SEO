package com.qiuwanchi.seo.controller;

import com.qiuwanchi.seo.dto.ModuleDto;
import com.qiuwanchi.seo.dto.ProjectDto;
import com.qiuwanchi.seo.entity.Module;
import com.qiuwanchi.seo.entity.Project;
import com.qiuwanchi.seo.service.IAttachmentService;
import com.qiuwanchi.seo.service.IModuleService;
import com.qiuwanchi.seo.service.IProjectService;
import com.qiuwanchi.seo.utils.*;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

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
    private ServerConfig serverConfig;

    @Autowired
    private BottomManagementCommon bottomManagementCommon;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM,dd");

    @GetMapping("/news.html")
    public String news(Model model){
        model.addAttribute("baseUrl", serverConfig.getUrl());

        // 1.logo
        List<ModuleDto> logoModuleList = this.moduleService.getModuleDtoList("LOGO");
        ModuleDto logoModuleDto = logoModuleList.get(0);
        ProjectDto logoProject = CollectionUtils.isEmpty(logoModuleDto.getProjectDtoList()) ? new ProjectDto() : logoModuleDto.getProjectDtoList().get(0);
        model.addAttribute("logoProject", logoProject);

        // 1新闻资讯
        List<ModuleDto> newsModuleList = this.moduleService.getModuleDtoList("News");
        model.addAttribute("newsModuleList", newsModuleList);
        if(!CollectionUtils.isEmpty(newsModuleList)){
            ModuleDto newsModule = newsModuleList.get(0);
            model.addAttribute("newsModule", newsModule);
            List<ProjectDto> newsProjectList = CollectionUtils.isEmpty(newsModule.getProjectDtoList()) ? new ArrayList<>() : newsModule.getProjectDtoList();
            for (ProjectDto projectDto : newsProjectList){
                String str = sdf.format(projectDto.getCreateTime());
                String[] arrStr = str.split(",");
                projectDto.setYears(arrStr[0]);
                projectDto.setDay(arrStr[1]);
                projectDto.setMonthDay(arrStr[0].split("-")[1] + "-" + projectDto.getDay());
            }

            newsProjectList.sort(new Comparator<ProjectDto>() {
                @Override
                public int compare(ProjectDto o1, ProjectDto o2) {
                    return o2.getCreateTime().compareTo(o1.getCreateTime());
                }
            });
            model.addAttribute("newsProjectList", newsProjectList);
        }

        this.bottomManagementCommon.bottom(model);

        return "news";
    }

    @GetMapping("/news/{id}.html")
    public String detail(Model model, @PathVariable("id") String id){
        model.addAttribute("baseUrl", serverConfig.getUrl());

        // 1.logo
        List<ModuleDto> logoModuleList = this.moduleService.getModuleDtoList("LOGO");
        ModuleDto logoModuleDto = logoModuleList.get(0);
        ProjectDto logoProject = CollectionUtils.isEmpty(logoModuleDto.getProjectDtoList()) ? new ProjectDto() : logoModuleDto.getProjectDtoList().get(0);
        model.addAttribute("logoProject", logoProject);

        Project project = this.projectService.getById(id);
        ProjectDto projectDto = new ProjectDto();
        BeanUtils.copyProperties(project, projectDto);

        String str = sdf.format(projectDto.getCreateTime());
        String[] arrStr = str.split(",");
        projectDto.setYears(arrStr[0]);
        projectDto.setDay(arrStr[1]);

        Module module = this.moduleService.getById(project.getModuleId());
        projectDto.setModuleName(module.getName());
        projectDto.setCreateBy(Objects.isNull(project.getCreateBy()) ? "admin" : project.getCreateBy());
        if(StringUtils.isBlank(projectDto.getContent())){
            projectDto.setContent(StringUtils.EMPTY);
        }else{
            projectDto.setContent(Utils.htmlDecode(projectDto.getContent()));
        }

        List<ProjectDto> list =  new ArrayList();
        list.add(projectDto);
        this.intProjectSeoValue(list);
        model.addAttribute("newsProject", projectDto);

        List<ProjectDto> recommendProjectDtoList = new ArrayList<>();
        List<String> keywordsList = new ArrayList<>();
        if(StringUtils.isNotBlank(projectDto.getKeywords())){
            String keywords = projectDto.getKeywords();
            keywords = keywords.replaceAll("，", ",");
            projectDto.setKeywords(keywords);

            String[] keywordsArr = keywords.split(",");
            for (String k : keywordsArr){
                keywordsList.add(k);
            }

            // 相关推荐
            recommendProjectDtoList = this.projectService.recommend(projectDto.getId(), keywordsArr);
        }
        model.addAttribute("recommendProjectDtoList", recommendProjectDtoList);

        // 关键字
        model.addAttribute("keywordsList", keywordsList);

        // 上一篇
        ProjectDto preProject = this.projectService.getPreProject(project.getModuleId(), project.getSort());
        model.addAttribute("preProject", preProject);

        // 下一篇
        ProjectDto nextProject = this.projectService.getNextProject(project.getModuleId(), project.getSort());
        model.addAttribute("nextProject", nextProject);

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
