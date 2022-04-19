package com.qiuwanchi.seo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qiuwanchi.seo.dto.ProjectDto;
import com.qiuwanchi.seo.entity.Project;

import java.util.List;

public interface IProjectService extends IService<Project> {

    List<ProjectDto> getProjectListByModuleId(String moduleId);

    List<ProjectDto> getProjectListByModuleIds(List<String> moduleIds);

    Page<ProjectDto> getProjectPageListByModuleId(Page page, String moduleId, String orderBy, String absOrDesc);

    ProjectDto getPreProject(String moduleId, int sort);

    ProjectDto getNextProject(String moduleId, int sort);

    List<ProjectDto> recommend(String id, List<String> keywordsList);
}
