package cn.qiuwanchi.cms.dao;

import cn.qiuwanchi.cms.common.BaseMapper;
import cn.qiuwanchi.cms.entity.System;

public interface SystemMapper extends BaseMapper<System> {
    System getCurrentSystem();
}