package com.qiuwanchi.seo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiuwanchi.seo.dto.ModuleDto;
import com.qiuwanchi.seo.dto.ProjectDto;
import com.qiuwanchi.seo.entity.Module;
import com.qiuwanchi.seo.mapper.ModuleMapper;
import com.qiuwanchi.seo.service.IModuleService;
import com.qiuwanchi.seo.service.IProjectService;
import com.qiuwanchi.seo.utils.UrlAssemblyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ModuleServiceImpl extends ServiceImpl<ModuleMapper, Module> implements IModuleService {

    @Autowired
    private ModuleMapper moduleMapper;

    @Autowired
    private IProjectService projectService;

    private static final String HTTP = "http://";

    @Override
    public List<ModuleDto> getModuleDtoList(String belong) {
        List<ModuleDto> moduleDtoList = this.moduleMapper.getModuleList(belong);

        /*初始化Module-seo相关值*/
        this.intModuleSeoValue(moduleDtoList);

        /*key=moduleId,value=module对象*/
        Map<String, ModuleDto> moduleDtoMap = moduleDtoList.stream().collect(Collectors.toMap(e -> e.getId(), e -> e));

        List<String> moduleIdList = new ArrayList<>();
        moduleIdList.addAll(moduleDtoMap.keySet());

        List<ProjectDto> projectDtoList = this.projectService.getProjectListByModuleIds(moduleIdList);
        /*初始化Project-seo相关值*/
        this.intProjectSeoValue(projectDtoList);

        /*key=moduleId,value=List<ProjectDto> 列表*/
        Map<String,List<ProjectDto>> moduleIdProjectDtoListMap = projectDtoList.stream().collect(Collectors.groupingBy(ProjectDto::getModuleId));

        for (Map.Entry<String, ModuleDto> entry : moduleDtoMap.entrySet()){
            String moduleId = entry.getKey();
            ModuleDto moduleDto = entry.getValue();

            List<ProjectDto> moduleProjectDtoList =  moduleIdProjectDtoListMap.get(moduleId);
            if(CollectionUtils.isEmpty(moduleProjectDtoList)){
                moduleProjectDtoList = new ArrayList<>();
            }
            /*根据序号排序*/
            moduleProjectDtoList.stream().sorted(new Comparator<ProjectDto>() {
                @Override
                public int compare(ProjectDto o1, ProjectDto o2) {
                    int a = o1.getSort().compareTo(o2.getSort());
                    if(a == 0){
                        a = o1.getUpdateTime().compareTo(o2.getUpdateTime());
                    }
                    return a;
                }
            });
            moduleDto.setProjectDtoList(moduleProjectDtoList);
        }

        return moduleDtoList;
    }

    private void intProjectSeoValue(List<ProjectDto> projectDtoList){
        for (ProjectDto projectDto : projectDtoList){
            if(StringUtils.isNotBlank(projectDto.getFilePath())){
                projectDto.setUrl(UrlAssemblyUtils.getImageUrl(projectDto.getFilePath()));
            }

            if(StringUtils.isNotBlank(projectDto.getTitle())){
                projectDto.setTitle(projectDto.getName());
            }

            if(StringUtils.isNotBlank(projectDto.getAlt())){
                projectDto.setAlt(projectDto.getName());
            }

            if(StringUtils.isNotBlank(projectDto.getClickUrl())){
                if(!isStartsWith(projectDto.getClickUrl(), HTTP)){
                    projectDto.setClickUrl(HTTP + projectDto.getClickUrl());
                }
            }
        }
    }

    private void intModuleSeoValue(List<ModuleDto> moduleDtoList){
        for (ModuleDto moduleDto : moduleDtoList){
            if(StringUtils.isNotBlank(moduleDto.getFilePath())){
                moduleDto.setUrl(UrlAssemblyUtils.getImageUrl(moduleDto.getFilePath()));
            }

            if(StringUtils.isNotBlank(moduleDto.getTitle())){
                moduleDto.setTitle(moduleDto.getName());
            }

            if(StringUtils.isNotBlank(moduleDto.getAlt())){
                moduleDto.setAlt(moduleDto.getName());
            }

            if(StringUtils.isNotBlank(moduleDto.getClickUrl())){
                if(!isStartsWith(moduleDto.getClickUrl(), HTTP)){
                    moduleDto.setClickUrl(HTTP + moduleDto.getClickUrl());
                }
            }
        }
    }

    private boolean isStartsWith(String str, String startStr){
        if(str.startsWith(startStr)){
            return true;
        }
        return false;
    }

}
