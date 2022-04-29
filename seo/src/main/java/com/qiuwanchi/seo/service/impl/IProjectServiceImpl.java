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
import java.util.stream.Collectors;

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

    @Override
    public List<ProjectDto> getHotAnswer(String moduleCode) {
        return this.projectMapper.getHotAnswer(moduleCode);
    }

    @Override
    public List<String> selectKeywords() {
        QueryWrapper<Project> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(Project.KEYWORDS);
        queryWrapper.isNotNull(Project.KEYWORDS);
        List<Project> projectList = this.list(queryWrapper);
        return projectList.stream().map(Project::getKeywords).collect(Collectors.toList());
    }

    @Override
    public List<ProjectDto> selectProjectListGroupByModuleId(List<String> moduleIdList, Integer size) {
        return this.selectTopProjectListGroupByModuleId(null, moduleIdList, size);
    }

    @Override
    public List<ProjectDto> selectTopProjectListGroupByModuleId(String homePageDisplay, List<String> moduleIdList, Integer size) {
        return this.projectMapper.selectTopProjectListGroupByModuleId(homePageDisplay, moduleIdList, size);
    }

    @Override
    public List<ProjectDto> selectNewsTopProjectListGroupByModuleId(String homePageDisplay, List<String> moduleIdList, Integer size) {
        return this.projectMapper.selectNewsTopProjectListGroupByModuleId(homePageDisplay, moduleIdList, size);
    }

    @Override
    public List<ProjectDto> getSolutionCaseList(String homePageDisplay, Integer pageSize) {
        return this.projectMapper.getSolutionCaseList(homePageDisplay, pageSize);
    }

    @Override
    public List<ProjectDto> getRecommendNewsFqaProjectList(String firstCategory, List<String> keywordsList) {
        return this.projectMapper.getRecommendNewsFqaProjectList(firstCategory, keywordsList);
    }

    @Override
    public List<ProjectDto> getRecentUpdatesNewsProjectList() {
        return this.projectMapper.getRecentUpdatesNewsProjectList();
    }

    @Override
    public Page<ProjectDto> newsSearch(Page page, String keyword) {
        return this.projectMapper.newsSearch(page, keyword);
    }

    @Override
    public List<String> selectSolutionKeywords() {
        return this.projectMapper.selectSolutionKeywords();
    }

}
