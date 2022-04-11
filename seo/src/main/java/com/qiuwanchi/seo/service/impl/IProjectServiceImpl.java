package com.qiuwanchi.seo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiuwanchi.seo.dto.ProjectDto;
import com.qiuwanchi.seo.entity.Project;
import com.qiuwanchi.seo.mapper.ProjectMapper;
import com.qiuwanchi.seo.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements IProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public List<ProjectDto> getProjectListByModuleId(String moduleId) {
        return this.projectMapper.getProjectListByModuleId(moduleId);
    }
}
