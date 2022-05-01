package cn.qiuwanchi.cms.service.impl;

import cn.qiuwanchi.cms.dao.SubProjectMapper;
import cn.qiuwanchi.cms.entity.SubProject;
import cn.qiuwanchi.cms.service.AttachmentService;
import cn.qiuwanchi.cms.service.ISeoVideoService;
import cn.qiuwanchi.cms.service.ISubProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
public class SubProjectServiceImpl implements ISubProjectService {

    @Autowired
    private SubProjectMapper subProjectMapper;

    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private ISeoVideoService seoVideoService;

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

    @Transactional
    @Override
    public void deleteByProjectId(String projectId) {
        SubProject subProject = new SubProject();
        subProject.setProjectId(projectId);

        List<SubProject> subProjectList = this.subProjectMapper.select(subProject);
        if(!CollectionUtils.isEmpty(subProjectList)){
            for (SubProject tempSubProject : subProjectList){

                // 删除图片
                if(!StringUtils.isEmpty(tempSubProject.getAttachmentId())){
                    this.attachmentService.delete(tempSubProject.getAttachmentId());
                }

                this.seoVideoService.deleteByBelongId(tempSubProject.getId());

            }
        }

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
    public SubProject delete(String id) {
        SubProject subProject = this.subProjectMapper.selectByPrimaryKey(id);
        if(Objects.nonNull(subProject) && !StringUtils.isEmpty(subProject.getAttachmentId())){
            this.attachmentService.delete(subProject.getAttachmentId());
        }

        this.seoVideoService.selectByBelongId(id);
        this.subProjectMapper.deleteByPrimaryKey(id);

        return subProject;
    }
}
