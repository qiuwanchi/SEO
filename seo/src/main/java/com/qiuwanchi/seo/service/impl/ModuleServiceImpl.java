package com.qiuwanchi.seo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiuwanchi.seo.dto.ModuleDto;
import com.qiuwanchi.seo.dto.ProjectDto;
import com.qiuwanchi.seo.entity.Module;
import com.qiuwanchi.seo.mapper.ModuleMapper;
import com.qiuwanchi.seo.service.IModuleService;
import com.qiuwanchi.seo.service.IProjectService;
import com.qiuwanchi.seo.utils.SeoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ModuleServiceImpl extends ServiceImpl<ModuleMapper, Module> implements IModuleService {

    @Autowired
    private ModuleMapper moduleMapper;

    @Autowired
    private IProjectService projectService;

    @Override
    public List<ModuleDto> getModuleDtoList(String belong) {
        List<ModuleDto> moduleDtoList = this.moduleMapper.getModuleList(belong);

        /*初始化Module-seo相关值*/
        SeoUtils.intModuleSeoValue(moduleDtoList);

        /*key=moduleId,value=module对象*/
        Map<String, ModuleDto> moduleDtoMap = moduleDtoList.stream().collect(Collectors.toMap(e -> e.getId(), e -> e));

        List<String> moduleIdList = new ArrayList<>();
        moduleIdList.addAll(moduleDtoMap.keySet());

        List<ProjectDto> projectDtoList = this.projectService.getProjectListByModuleIds(moduleIdList);
        /*初始化Project-seo相关值*/
        SeoUtils.intProjectSeoValue(projectDtoList);

        /*key=moduleId,value=List<ProjectDto> 列表*/
        Map<String,List<ProjectDto>> moduleIdProjectDtoListMap = projectDtoList.stream().collect(Collectors.groupingBy(ProjectDto::getModuleId));

        for (Map.Entry<String, ModuleDto> entry : moduleDtoMap.entrySet()){
            String moduleId = entry.getKey();
            ModuleDto moduleDto = entry.getValue();

            List<ProjectDto> moduleProjectDtoList =  moduleIdProjectDtoListMap.get(moduleId);
            if(CollectionUtils.isEmpty(moduleProjectDtoList)){
                moduleProjectDtoList = new ArrayList<>();
            }
            /*根据序号排序*/
            moduleProjectDtoList.sort(new Comparator<ProjectDto>() {
                @Override
                public int compare(ProjectDto o1, ProjectDto o2) {
                    int a = o1.getSort().compareTo(o2.getSort());
                    if(a == 0){
                        a = o1.getUpdateTime().compareTo(o2.getUpdateTime());
                    }
                    return a;
                }
            });
            moduleDto.setProjectDtoList(moduleProjectDtoList);
        }

        return moduleDtoList;
    }

    @Override
    public List<ModuleDto> getSimpleModuleDtoList(String belong) {
        return this.moduleMapper.getModuleList(belong);
    }

    @Override
    public ModuleDto selectByModuleId(String moduleId) {
        return this.moduleMapper.selectByModuleId(moduleId);
    }

}
