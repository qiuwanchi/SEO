package cn.itechyou.cms.service.impl;

import cn.itechyou.cms.dao.ModuleMapper;
import cn.itechyou.cms.entity.Module;
import cn.itechyou.cms.service.IModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class ModuleServiceImpl implements IModuleService {

    @Autowired
    private ModuleMapper moduleMapper;

    @Override
    public List<Module> list(String belong) {
        Module module = new Module();
        module.setBelong(belong);
        List<Module> list = this.moduleMapper.select(module);

        Collections.sort(list, new Comparator<Module>() {
            @Override
            public int compare(Module o1, Module o2) {
                int a = Integer.valueOf(o1.getSort()).compareTo(Integer.valueOf(o2.getSort()));
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

    @Override
    public void deleteById(String id) {
        this.moduleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void add(Module module) {
        this.moduleMapper.insert(module);
    }

    @Override
    public void update(Module module) {
        this.moduleMapper.updateByPrimaryKey(module);
    }

}
