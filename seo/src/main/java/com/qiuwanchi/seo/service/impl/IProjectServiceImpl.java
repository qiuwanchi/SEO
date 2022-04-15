package com.qiuwanchi.seo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiuwanchi.seo.dto.ProjectDto;
import com.qiuwanchi.seo.entity.Project;
import com.qiuwanchi.seo.mapper.ProjectMapper;
import com.qiuwanchi.seo.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class IProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements IProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public List<ProjectDto> getProjectListByModuleId(String moduleId) {
        return this.projectMapper.getProjectListByModuleId(moduleId);
    }

    @Override
    public List<ProjectDto> getProjectListByModuleIds(List<String> moduleIds) {
        return this.projectMapper.getProjectListByModuleIds(moduleIds);
    }

    @Override
    public Page<ProjectDto> getProjectPageListByModuleId(Page page, String moduleId) {
        return this.projectMapper.getProjectPageListByModuleId(page,moduleId);
    }

    @Override
    public ProjectDto getPreProject(String moduleId, String sort) {
        QueryWrapper<Project> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Project.MODULE_ID, moduleId);
        queryWrapper.lt(Project.SORT, sort);
        queryWrapper.orderByDesc(Project.SORT);
        queryWrapper.last("limit 1");
        Project project = this.projectMapper.selectOne(queryWrapper);

        if(Objects.isNull(project)){
            return null;
        }

        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(project.getId());
        projectDto.setName(project.getName());
        return projectDto;
    }

    @Override
    public ProjectDto getNextProject(String moduleId, String sort) {
        QueryWrapper<Project> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Project.MODULE_ID, moduleId);
        queryWrapper.gt(Project.SORT, sort);
        queryWrapper.orderByAsc(Project.SORT);
        queryWrapper.last("limit 1");
        Project project = this.projectMapper.selectOne(queryWrapper);
        if(Objects.isNull(project)){
            return null;
        }
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(project.getId());
        projectDto.setName(project.getName());
        return projectDto;
    }
}
