package cn.qiuwanchi.cms.controller.admin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.qiuwanchi.cms.common.ExceptionEnum;
import cn.qiuwanchi.cms.entity.Licence;
import cn.qiuwanchi.cms.exception.CmsException;
import cn.qiuwanchi.cms.exception.FormParameterException;
import cn.qiuwanchi.cms.security.token.TokenManager;
import cn.qiuwanchi.cms.service.LicenceService;
import cn.qiuwanchi.cms.utils.StringUtil;
import cn.qiuwanchi.cms.utils.UUIDUtils;

/**
 * 许可授权
 * @author 王俊南
 * Date: 2021-3-5
 */
@Controller
@RequestMapping("/admin/licence")
public class LicenceController {
	@Autowired
	private LicenceService licenceService;
	
	@RequestMapping("/toIndex")
	public String toIndex(Model model) {
		Licence licence = licenceService.getLicence();
		model.addAttribute("licence", licence);
		return "admin/licence/licence";
	}
	
	@RequestMapping(value = "/grant", method = RequestMethod.POST)
	public String grant(Licence licence) throws CmsException {
		if(StringUtil.isBlank(licence.getEncryptData()) || StringUtil.isBlank(licence.getPrivateKey())) {
			throw new FormParameterException(
					ExceptionEnum.LICENCE_PARAMETER_EXCEPTION.getCode(), 
					ExceptionEnum.LICENCE_PARAMETER_EXCEPTION.getMessage(), 
					"系统授权失败，请检查加密证书或密钥是否正确！");
		}
		try {
			String privateKey = licence.getPrivateKey().trim();
			String encryptData = licence.getEncryptData().trim();
			RSA rsa = new RSA(privateKey, null);
			byte[] encryptByte = Base64.decode(encryptData.getBytes());
			byte[] decryptByte = rsa.decrypt(encryptByte, KeyType.PrivateKey);
			String decryptData = new String(decryptByte);
			
			String[] decryptArr = decryptData.split("#=#");
			licence.setId(UUIDUtils.getPrimaryKey());
			licence.setType(decryptArr[0]);
			licence.setMainName(decryptArr[1]);
			licence.setCreditCode(decryptArr[2]);
			licence.setLicenceNum(decryptArr[3]);
			licence.setMainDomain(decryptArr[4]);
			licence.setCreateBy(TokenManager.getUserId());
			licence.setCreateTime(new Date());
			int row = licenceService.save(licence);
		}catch (Exception e) {
			throw new FormParameterException(
					ExceptionEnum.LICENCE_PARAMETER_EXCEPTION.getCode(), 
					ExceptionEnum.LICENCE_PARAMETER_EXCEPTION.getMessage(), 
					"系统授权失败，请检查加密证书或密钥是否正确！");
		}
		return "redirect:/admin/licence/toIndex";
	}
}
