package cn.qiuwanchi.cms.service.impl;

import cn.qiuwanchi.cms.dao.SubProjectMapper;
import cn.qiuwanchi.cms.entity.SubProject;
import cn.qiuwanchi.cms.service.ISubProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class SubProjectServiceImpl implements ISubProjectService {

    @Autowired
    private SubProjectMapper subProjectMapper;

    @Override
    public List<SubProject> getByProjectId(String projectId) {
        SubProject subProject = new SubProject();
        subProject.setProjectId(projectId);
        List<SubProject> list = this.subProjectMapper.select(subProject);

        Collections.sort(list, new Comparator<SubProject>() {
            @Override
            public int compare(SubProject o1, SubProject o2) {
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
    public void deleteByProjectId(String projectId) {
        SubProject subProject = new SubProject();
        subProject.setProjectId(projectId);
        this.subProjectMapper.delete(subProject);
    }

    @Override
    public SubProject getById(String id) {
        return this.subProjectMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(SubProject subProject) {
        if(!StringUtils.isEmpty(subProject.getKeywords())){
            subProject.setKeywords(subProject.getKeywords().replaceAll("，", ","));
        }
        this.subProjectMapper.insert(subProject);
    }

    @Override
    public void update(SubProject subProject) {
        if(!StringUtils.isEmpty(subProject.getKeywords())){
            subProject.setKeywords(subProject.getKeywords().replaceAll("，", ","));
        }
        this.subProjectMapper.updateByPrimaryKey(subProject);
    }

    @Override
    public void delete(String id) {
        this.subProjectMapper.deleteByPrimaryKey(id);
    }
}
