package cn.qiuwanchi.cms.service.impl;

import cn.qiuwanchi.cms.dao.ProjectMapper;
import cn.qiuwanchi.cms.dto.ProjectDto;
import cn.qiuwanchi.cms.entity.Project;
import cn.qiuwanchi.cms.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class ProjectServiceImpl implements IProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public List<Project> getByModuleId(String moduleId) {
        Project project = new Project();
        project.setModuleId(moduleId);
        List<Project> list = this.projectMapper.select(project);

        Collections.sort(list, new Comparator<Project>() {
            @Override
            public int compare(Project o1, Project o2) {
                int a = o1.getSort().compareTo(o2.getSort());
                if(a == 0){
                    a = o1.getUpdateTime().compareTo(o2.getUpdateTime());
                }
                return a;
            }
        });
        return list;
    }

    @Override
    public void deleteByModuleId(String moduleId) {
        Project project = new Project();
        project.setModuleId(moduleId);
        this.projectMapper.delete(project);
    }

    @Override
    public Project getById(String id) {
        return this.projectMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(Project project) {
        if(!StringUtils.isEmpty(project.getKeywords())){
            project.setKeywords(project.getKeywords().replaceAll("，", ","));
        }
        this.projectMapper.insert(project);
    }

    @Override
    public void update(Project project) {
        if(!StringUtils.isEmpty(project.getKeywords())){
            project.setKeywords(project.getKeywords().replaceAll("，", ","));
        }
        this.projectMapper.updateByPrimaryKey(project);
    }

    @Override
    public void delete(String id) {
        this.projectMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int getCountByCode(String moduleId, String id, String code) {
        Project project = new Project();
        project.setCode(code);
        project.setModuleId(moduleId);
        List<Project> projectList = this.projectMapper.select(project);
        if(!StringUtils.isEmpty(id)){
            return (int)projectList.stream().filter(m -> !m.getId().equals(id)).count();
        }
        return projectList.size();
    }

    @Override
    public List<ProjectDto> getByBelong(String belong) {
        return this.projectMapper.getByBelong(belong);
    }
}
