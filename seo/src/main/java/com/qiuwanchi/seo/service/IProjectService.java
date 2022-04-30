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

    ProjectDto getPreProject(String moduleId, String currentProjectId, int sort);

    ProjectDto getNextProject(String moduleId, String currentProjectId, int sort);

    List<ProjectDto> recommend(String category, String id, List<String> keywordsList);

    ProjectDto selectByNumber(int number);

    List<ProjectDto> getHotAnswer(String moduleCode);

    List<String> selectKeywords();

    List<ProjectDto> selectProjectListGroupByModuleId(List<String> moduleIdList, Integer size);

    /**
     * 查询置顶的项目
     * @param homePageDisplay 是否置顶
     * @param moduleIdList
     * @param size
     * @return
     */
    List<ProjectDto> selectTopProjectListGroupByModuleId(String homePageDisplay, List<String> moduleIdList, Integer size);

    List<ProjectDto> selectNewsTopProjectListGroupByModuleId(String homePageDisplay, List<String> moduleIdList, Integer size);

    /**
     * 查询解决方案列表
     * @param homePageDisplay 是否首页置顶
     * @return
     */
    List<ProjectDto> getSolutionCaseList(String homePageDisplay, Integer pageSize);

    /**
     * 推荐关键字相关的常见问答数据列表
     * @param firstCategory
     * @param keywordsList
     * @return
     */
    List<ProjectDto> getRecommendNewsFqaProjectList(String firstCategory, List<String> keywordsList);

    List<ProjectDto> getRecentUpdatesNewsProjectList();

    /**
     * 关键词进行新闻搜索
     * @param page
     * @param keyword
     * @return
     */
    Page<ProjectDto> newsSearch(Page page, String keyword);

    List<String> selectSolutionKeywords();

    ProjectDto selectSeoThreeElements(String code);

    List<String> getHotLabel();
}
