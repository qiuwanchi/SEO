package cn.itechyou.cms.service;

import cn.itechyou.cms.entity.Project;

import java.util.List;

public interface IProjectService {

    /**
     * 根据模块id查询项目列表
     * @param moduleId
     * @return
     */
    List<Project> getByModuleId(String moduleId);

    /**
     * 根据模块id删除所有的项目
     * @param moduleId
     */
    void deleteByModuleId(String moduleId);

    Project getById(String id);

    void add(Project project);

    void update(Project project);

    void delete(String id);

    int getCountByCode(String moduleId, String id, String code);
}
