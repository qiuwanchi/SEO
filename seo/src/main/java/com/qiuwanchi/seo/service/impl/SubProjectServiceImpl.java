package com.qiuwanchi.seo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiuwanchi.seo.dto.SubProjectDto;
import com.qiuwanchi.seo.entity.SubProject;
import com.qiuwanchi.seo.mapper.SubProjectMapper;
import com.qiuwanchi.seo.service.ISubProjectService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
}
