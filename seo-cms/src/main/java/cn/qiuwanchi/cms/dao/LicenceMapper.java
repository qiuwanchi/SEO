package cn.qiuwanchi.cms.dao;

import cn.qiuwanchi.cms.common.BaseMapper;
import cn.qiuwanchi.cms.entity.Licence;

public interface LicenceMapper extends BaseMapper<Licence> {

	Licence getCurrentLicence();
    
}