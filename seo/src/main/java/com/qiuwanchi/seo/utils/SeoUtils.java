package com.qiuwanchi.seo.utils;

import com.qiuwanchi.seo.dto.ModuleDto;
import com.qiuwanchi.seo.dto.ProjectDto;
import com.qiuwanchi.seo.dto.SubProjectDto;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class SeoUtils {

    public static void intSubProjectSeoValue(SubProjectDto subProjectDto){
        if(StringUtils.isNotBlank(subProjectDto.getFilePath())){
            subProjectDto.setUrl(UrlAssemblyUtils.getImageUrl(subProjectDto.getFilePath()));
        }

        if(StringUtils.isBlank(subProjectDto.getTitle())){
            subProjectDto.setTitle(subProjectDto.getName());
        }

        if(StringUtils.isBlank(subProjectDto.getAlt())){
            subProjectDto.setAlt(subProjectDto.getName());
        }

        if(StringUtils.isNotBlank(subProjectDto.getClickUrl())){
            if(!Utils.isStartsWith(subProjectDto.getClickUrl(), Utils.HTTP)){
                subProjectDto.setClickUrl(Utils.HTTP + subProjectDto.getClickUrl());
            }
        }

        if(StringUtils.isBlank(subProjectDto.getContent())){
            subProjectDto.setContent(StringUtils.EMPTY);
        }else{
            subProjectDto.setContent(Utils.htmlDecode(subProjectDto.getContent()));
        }

    }

    public static void intSubProjectSeoValue(List<SubProjectDto> subProjectDtoList){
        for (SubProjectDto subProjectDto : subProjectDtoList){
            intSubProjectSeoValue(subProjectDto);
        }
    }


    public static void intProjectSeoValue(ProjectDto projectDto){
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

        if(StringUtils.isBlank(projectDto.getContent())){
            projectDto.setContent(StringUtils.EMPTY);
        }else{
            projectDto.setContent(Utils.htmlDecode(projectDto.getContent()));
        }
    }

    public static void intProjectSeoValue(List<ProjectDto> projectDtoList){
        for (ProjectDto projectDto : projectDtoList){
            intProjectSeoValue(projectDto);
        }
    }


    public static void intModuleSeoValue(ModuleDto moduleDto){
        if(StringUtils.isNotBlank(moduleDto.getFilePath())){
            moduleDto.setUrl(UrlAssemblyUtils.getImageUrl(moduleDto.getFilePath()));
        }

        if(StringUtils.isBlank(moduleDto.getTitle())){
            moduleDto.setTitle(moduleDto.getName());
        }

        if(StringUtils.isBlank(moduleDto.getAlt())){
            moduleDto.setAlt(moduleDto.getName());
        }

        if(StringUtils.isNotBlank(moduleDto.getClickUrl())){
            if(!Utils.isStartsWith(moduleDto.getClickUrl(), Utils.HTTP)){
                moduleDto.setClickUrl(Utils.HTTP + moduleDto.getClickUrl());
            }
        }
    }

    public static void intModuleSeoValue(List<ModuleDto> moduleDtoList){
        for (ModuleDto moduleDto : moduleDtoList){
            intModuleSeoValue(moduleDto);
        }
    }
}
