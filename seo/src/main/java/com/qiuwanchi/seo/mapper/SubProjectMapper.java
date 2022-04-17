package com.qiuwanchi.seo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qiuwanchi.seo.dto.ProjectDto;
import com.qiuwanchi.seo.dto.SubProjectDto;
import com.qiuwanchi.seo.entity.Project;
import com.qiuwanchi.seo.entity.SubProject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SubProjectMapper extends BaseMapper<SubProject> {

    List<SubProjectDto> getProjectListByProjectId(@Param("projectId")String projectId);

    Page<SubProjectDto> getProjectPageListByProjectId(Page page, @Param("projectId")String projectId);

    List<SubProjectDto> getProjectListByProjectIds(@Param("projectIds")List<String> projectIds);

    List<SubProjectDto> recommend(@Param("subProjectId")String id, @Param("keywordsList")List<String> keywordsList);
}
