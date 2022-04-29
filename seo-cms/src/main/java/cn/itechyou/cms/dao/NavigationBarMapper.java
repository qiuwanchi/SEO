package cn.itechyou.cms.dao;

import cn.itechyou.cms.common.BaseMapper;
import cn.itechyou.cms.entity.NavigationBar;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NavigationBarMapper extends BaseMapper<NavigationBar> {
    
    List<NavigationBar> queryListByBelong(@Param("belong")String belong);

}