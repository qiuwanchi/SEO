package cn.qiuwanchi.cms.service;

import cn.qiuwanchi.cms.entity.Licence;

public interface LicenceService {

	Licence getLicence();

	int save(Licence licence);

}
