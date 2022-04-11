package com.qiuwanchi.seo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qiuwanchi.seo.dto.ProjectDto;
import com.qiuwanchi.seo.entity.Project;

import java.util.List;

public interface IProjectService extends IService<Project> {

    List<ProjectDto> getProjectListByModuleId(String moduleId);

}
