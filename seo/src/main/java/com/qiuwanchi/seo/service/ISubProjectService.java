package com.qiuwanchi.seo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qiuwanchi.seo.dto.ModuleDto;
import com.qiuwanchi.seo.dto.SubProjectDto;
import com.qiuwanchi.seo.entity.SubProject;

import java.util.List;

public interface ISubProjectService extends IService<SubProject> {

    List<SubProjectDto> getProjectListByProjectId(String projectId);

    List<SubProjectDto> getProjectListByProjectIds(List<String> projectIds);

    Page<SubProjectDto> getProjectPageListByProjectId(Page page, String projectId);

    SubProjectDto getPreSubProject(String projectId, String currentSubProjectId, int sort);

    SubProjectDto getNextSubProject(String projectId, String currentSubProjectId, int sort);

    List<SubProjectDto> recommend(String id, List<String> keywordsList);

    /**
     *
     * @param firstCategory 一级类目
     * @param secondCategory 二级类目
     * @return
     */
    Page<SubProjectDto> getPageList(Page page, String firstCategory, String secondCategory);

    SubProjectDto getByNumber(int number);

    List<String> selectKeywords();

    List<ModuleDto> getSubProjectList(String homePageDisplay, String serviceCase);

    List<SubProjectDto> getSubProjectListByModuleIds(String homePageDisplay, List<String> moduleIds, int pageSize);

    List<SubProjectDto> getRecommendServiceCaseSubProjectList(String firstCategory);

    List<String> selectServiceCaseKeywordsList();

    Page<SubProjectDto> search(Page page, String keyword);

    Page<SubProjectDto> searchAll(Page page, String keyword);
}
