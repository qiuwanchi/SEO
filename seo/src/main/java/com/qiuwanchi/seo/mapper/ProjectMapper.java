package com.qiuwanchi.seo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qiuwanchi.seo.dto.ProjectDto;
import com.qiuwanchi.seo.entity.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProjectMapper extends BaseMapper<Project> {

    List<ProjectDto> getProjectListByModuleId(@Param("moduleId")String moduleId);

    Page<ProjectDto> getProjectPageListByModuleId(Page page, @Param("moduleId")String moduleId, @Param("orderBy")String orderBy, @Param("absOrDesc") String absOrDesc);

    List<ProjectDto> getProjectListByModuleIds(@Param("moduleIds")List<String> moduleIds);

    List<ProjectDto> recommend(@Param("category")String category, @Param("projectId")String id, @Param("keywordsList")List<String> keywordsList);

    ProjectDto selectByNumber(@Param("number")int number);

    ProjectDto getPreProject(@Param("moduleId")String moduleId, @Param("currentProjectId")String currentProjectId, @Param("sort")int sort);

    ProjectDto getNextProject(@Param("moduleId")String moduleId, @Param("currentProjectId")String currentProjectId, @Param("sort")int sort);

    /**
     * 热门回答
     * @param moduleCode
     * @return
     */
    List<ProjectDto> getHotAnswer(@Param("moduleCode")String moduleCode);

    /**
     * 查询是否置顶的projectDto列表
     * @param homePageDisplay 是否置顶
     * @param moduleIdList
     * @param size
     * @return
     */
    List<ProjectDto> selectTopProjectListGroupByModuleId(@Param("homePageDisplay")String homePageDisplay, @Param("moduleIds")List<String> moduleIdList, @Param("pageSize")Integer size);

    /**
     * 解决方案
     * @param homePageDisplay
     * @param pageSize
     * @return
     */
    List<ProjectDto> getSolutionCaseList(@Param("homePageDisplay")String homePageDisplay, @Param("pageSize")Integer pageSize);

    List<ProjectDto> selectNewsTopProjectListGroupByModuleId(@Param("homePageDisplay")String homePageDisplay, @Param("moduleIds")List<String> moduleIdList, @Param("pageSize")Integer size);

    List<ProjectDto> getRecommendNewsFqaProjectList(@Param("firstCategory")String firstCategory, @Param("keywordsList")List<String> keywordsList);

    /**
     * 最近更新
     * @return
     */
    List<ProjectDto> getRecentUpdatesNewsProjectList();

    Page<ProjectDto> newsSearch(Page page, @Param("keyword")String keyword);

    List<String> selectSolutionKeywords();

    List<ProjectDto> selectSeoThreeElements(@Param("code")String code);

    List<String> getHotLabel();
}
