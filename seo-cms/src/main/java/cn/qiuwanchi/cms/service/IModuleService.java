package cn.qiuwanchi.cms.service;

import cn.qiuwanchi.cms.entity.Module;

import java.util.List;

public interface IModuleService {

    List<Module> list(String belong);

    Module getById(String id);

    void deleteById(String id);

    void add(Module module);

    void update(Module module);

    int getCountByCode(String id, String code);
}
