package com.qiuwanchi.seo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qiuwanchi.seo.dto.ProjectDto;
import com.qiuwanchi.seo.entity.Project;

import java.util.List;

public interface IProjectService extends IService<Project> {

    List<ProjectDto> getProjectListByModuleId(String moduleId);

    List<ProjectDto> getProjectListByModuleIds(List<String> moduleIds);

    Page<ProjectDto> getProjectPageListByModuleId(Page page, String moduleId);

    ProjectDto getPreProject(String moduleId, String sort);

    ProjectDto getNextProject(String moduleId, String sort);

    List<ProjectDto> recommend(String id, String[] keywordsArr);
}
