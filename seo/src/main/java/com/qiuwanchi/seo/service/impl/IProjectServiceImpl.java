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
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
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
    public Page<ProjectDto> getProjectPageListByModuleId(Page page, String moduleId, String orderBy, String absOrDesc) {
        return this.projectMapper.getProjectPageListByModuleId(page,moduleId, orderBy, absOrDesc);
    }

    @Override
    public ProjectDto getPreProject(String moduleId, String currentProjectId, int sort) {
        return this.projectMapper.getPreProject(moduleId, currentProjectId ,sort);
    }

    @Override
    public ProjectDto getNextProject(String moduleId, String currentProjectId, int sort) {
        return this.projectMapper.getNextProject(moduleId, currentProjectId, sort);
    }

    @Override
    public List<ProjectDto> recommend(String category, String id, List<String> keywordsList) {
        if(CollectionUtils.isEmpty(keywordsList)){
            return new ArrayList<>();
        }
        return this.projectMapper.recommend(category, id, keywordsList);
    }

    @Override
    public ProjectDto selectByNumber(int number) {
        return this.projectMapper.selectByNumber(number);
    }
}
