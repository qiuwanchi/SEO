package com.qiuwanchi.seo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qiuwanchi.seo.dto.SubProjectDto;
import com.qiuwanchi.seo.entity.SubProject;

import java.util.List;

public interface ISubProjectService extends IService<SubProject> {

    List<SubProjectDto> getProjectListByProjectId(String projectId);

    List<SubProjectDto> getProjectListByProjectIds(List<String> projectIds);

    Page<SubProjectDto> getProjectPageListByProjectId(Page page, String projectId);

    SubProjectDto getPreSubProject(String projectId, String sort);

    SubProjectDto getNextSubProject(String projectId, String sort);

    List<SubProjectDto> recommend(String id, String[] keywordsArr);

    /**
     *
     * @param firstCategory 一级类目
     * @param secondCategory 二级类目
     * @return
     */
    Page<SubProjectDto> getPageList(Page page, String firstCategory, String secondCategory);
}
