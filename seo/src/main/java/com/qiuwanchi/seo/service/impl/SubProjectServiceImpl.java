package com.qiuwanchi.seo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiuwanchi.seo.dto.ModuleDto;
import com.qiuwanchi.seo.dto.SubProjectDto;
import com.qiuwanchi.seo.entity.Project;
import com.qiuwanchi.seo.entity.SubProject;
import com.qiuwanchi.seo.mapper.SubProjectMapper;
import com.qiuwanchi.seo.service.ISubProjectService;
import com.qiuwanchi.seo.utils.SeoUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubProjectServiceImpl  extends ServiceImpl<SubProjectMapper, SubProject> implements ISubProjectService {

    @Autowired
    private SubProjectMapper subProjectMapper;

    @Override
    public List<SubProjectDto> getProjectListByProjectId(String projectId) {
        return this.subProjectMapper.getProjectListByProjectId(projectId);
    }

    @Override
    public List<SubProjectDto> getProjectListByProjectIds(List<String> projectIds) {
        return this.subProjectMapper.getProjectListByProjectIds(projectIds);
    }

    @Override
    public Page<SubProjectDto> getProjectPageListByProjectId(Page page, String projectId) {
        return this.subProjectMapper.getProjectPageListByProjectId(page, projectId);
    }

    @Override
    public SubProjectDto getPreSubProject(String projectId, String currentSubProjectId, int sort) {
        return this.subProjectMapper.getPreSubProject(projectId, currentSubProjectId, sort);
    }

    @Override
    public SubProjectDto getNextSubProject(String projectId, String currentSubProjectId, int sort) {
        return this.subProjectMapper.getNextSubProject(projectId, currentSubProjectId, sort);
    }

    @Override
    public List<SubProjectDto> recommend(String id, List<String> keywordsList) {
        return this.subProjectMapper.recommend(id, keywordsList);
    }

    @Override
    public Page<SubProjectDto> getPageList(Page page, String firstCategory, String secondCategory) {
        if(StringUtils.isNotBlank(firstCategory) && StringUtils.isNotBlank(secondCategory)){
            return this.subProjectMapper.selectPageBySecondCategory(page, firstCategory, secondCategory);
        }else if(StringUtils.isNotBlank(firstCategory) && StringUtils.isBlank(secondCategory)){
            return this.subProjectMapper.selectPageByFirstCategory(page, firstCategory);
        }else {
            return this.subProjectMapper.selectPageAll(page);
        }
    }

    @Override
    public SubProjectDto getByNumber(int number) {
        return this.subProjectMapper.selectByNumber(number);
    }

    @Override
    public List<String> selectKeywords() {
        QueryWrapper<SubProject> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(SubProject.KEYWORDS);
        queryWrapper.isNotNull(Project.KEYWORDS);
        List<SubProject> subProjectList = this.list(queryWrapper);
        return subProjectList.stream().map(SubProject::getKeywords).collect(Collectors.toList());
    }

    @Override
    public List<ModuleDto> getSubProjectList(String homePageDisplay, String serviceCase) {
        //TODO
        return null;
    }

    @Override
    public List<SubProjectDto> getSubProjectListByModuleIds(String homePageDisplay, List<String> moduleIds, int pageSize) {
        List<SubProjectDto> subProjectDtoList = this.subProjectMapper.getSubProjectListByModuleIds(homePageDisplay, moduleIds, pageSize);
        /*初始化Module-seo相关值*/
        SeoUtils.intSubProjectSeoValue(subProjectDtoList);
        return subProjectDtoList;
    }

    @Override
    public List<SubProjectDto> getRecommendServiceCaseSubProjectList(String firstCategory) {
        return this.subProjectMapper.getRecommendServiceCaseSubProjectList(firstCategory);
    }
}
