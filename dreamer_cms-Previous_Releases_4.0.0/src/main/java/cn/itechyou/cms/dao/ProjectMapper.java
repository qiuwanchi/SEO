package cn.itechyou.cms.dao;

import cn.itechyou.cms.dto.ProjectDto;
import cn.itechyou.cms.entity.Project;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

public interface ProjectMapper extends BaseMapper<Project> {

    List<ProjectDto> getByBelong(String belong);
}
