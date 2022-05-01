package cn.qiuwanchi.cms.service;

import cn.qiuwanchi.cms.entity.SubProject;

import java.util.List;

public interface ISubProjectService {

    /**
     * 根据项目id查询子项目列表
     * @param projectId
     * @return
     */
    List<SubProject> getByProjectId(String projectId);

    /**
     * 根据项目id删除所有的子项目
     * @param projectId
     */
    void deleteByProjectId(String projectId);

    SubProject getById(String id);

    void add(SubProject subProject);

    void update(SubProject subProject);

    SubProject delete(String id);
}
