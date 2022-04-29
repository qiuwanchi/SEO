package cn.qiuwanchi.cms.dao;

import cn.qiuwanchi.cms.dto.ProjectDto;
import cn.qiuwanchi.cms.entity.Project;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

public interface ProjectMapper extends BaseMapper<Project> {

    List<ProjectDto> getByBelong(String belong);
}
