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

    Page<ProjectDto> getProjectPageListByModuleId(Page page, @Param("moduleId")String moduleId);

    List<ProjectDto> getProjectListByModuleIds(@Param("moduleIds")List<String> moduleIds);

    List<ProjectDto> recommend(@Param("projectId")String id, @Param("keywordsList")List<String> keywordsList);
}
