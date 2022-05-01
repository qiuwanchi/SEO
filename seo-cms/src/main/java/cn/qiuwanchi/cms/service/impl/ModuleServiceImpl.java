package cn.qiuwanchi.cms.service.impl;

import cn.qiuwanchi.cms.dao.ModuleMapper;
import cn.qiuwanchi.cms.entity.Module;
import cn.qiuwanchi.cms.entity.Project;
import cn.qiuwanchi.cms.service.AttachmentService;
import cn.qiuwanchi.cms.service.IModuleService;
import cn.qiuwanchi.cms.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
public class ModuleServiceImpl implements IModuleService {

    @Autowired
    private ModuleMapper moduleMapper;

    @Autowired
    private IProjectService projectService;

    @Autowired
    private AttachmentService attachmentService;

    @Override
    public List<Module> list(String belong) {
        Module module = new Module();
        module.setBelong(belong);
        List<Module> list = this.moduleMapper.select(module);

        Collections.sort(list, new Comparator<Module>() {
            @Override
            public int compare(Module o1, Module o2) {
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
    public Module getById(String id) {
        return this.moduleMapper.selectByPrimaryKey(id);
    }

    @Transactional
    @Override
    public Module deleteById(String id) {
        Module module = this.moduleMapper.selectByPrimaryKey(id);

        // 删除图片
        if(!StringUtils.isEmpty(module.getAttachmentId())){
            this.attachmentService.delete(module.getAttachmentId());
        }

        // 删除project
        this.projectService.deleteByModuleId(id);

        // 删除 module
        this.moduleMapper.deleteByPrimaryKey(id);

        return module;
    }

    @Override
    public void add(Module module) {
        this.moduleMapper.insert(module);
    }

    @Override
    public void update(Module module) {
        this.moduleMapper.updateByPrimaryKey(module);
    }

    @Override
    public int getCountByCode(String id, String code) {

        Module dbModule = null;
        if(!StringUtils.isEmpty(id)){
            Module module = new Module();
            module.setId(id);
            dbModule = this.moduleMapper.selectOne(module);
        }

        Module module = new Module();
        module.setCode(code);
        if(Objects.nonNull(dbModule)){
            module.setBelong(dbModule.getBelong());
        }

        List<Module> moduleList = this.moduleMapper.select(module);
        if(!StringUtils.isEmpty(id)){
            return (int)moduleList.stream().filter(m -> !m.getId().equals(id)).count();
        }

        return moduleList.size();
    }

}
