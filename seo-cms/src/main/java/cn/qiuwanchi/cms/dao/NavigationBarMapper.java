package cn.qiuwanchi.cms.dao;

import cn.qiuwanchi.cms.common.BaseMapper;
import cn.qiuwanchi.cms.entity.NavigationBar;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NavigationBarMapper extends BaseMapper<NavigationBar> {
    
    List<NavigationBar> queryListByBelong(@Param("belong")String belong);

}