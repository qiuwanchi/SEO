package cn.qiuwanchi.cms.controller.admin;

import cn.qiuwanchi.cms.entity.NavigationBar;
import cn.qiuwanchi.cms.service.NavigationBarService;
import cn.qiuwanchi.cms.utils.UUIDUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

@Controller
@RequestMapping("firstPage/navigationBar")
public class NavigationBarController {

	@Autowired
	private NavigationBarService navigationBarService;

	/**
	 * 列表
	 */
	@RequestMapping
	public String get(Model model, String belong) {
		NavigationBar navigationBar = this.navigationBarService.queryListByBelong(belong);
		if(Objects.isNull(navigationBar)){
			navigationBar = new NavigationBar();
		}
		navigationBar.setBelong(belong);
		model.addAttribute("navigationBar", navigationBar);
		return "firstPage/navigationBar/add";
	}

	@PostMapping("/save")
	public String save(Model model, NavigationBar param) {
		NavigationBar navigationBarDB = this.navigationBarService.queryListByBelong(param.getBelong());
		if(Objects.isNull(navigationBarDB)){
			navigationBarDB = new NavigationBar();
			navigationBarDB.setId(UUIDUtils.getPrimaryKey());
			navigationBarDB.setBelong(param.getBelong());
			navigationBarDB.setHtmlContent(param.getHtmlContent());
			this.navigationBarService.add(navigationBarDB);
		}else {
			navigationBarDB.setHtmlContent(param.getHtmlContent());
			this.navigationBarService.update(navigationBarDB);
		}


		model.addAttribute("navigationBar", navigationBarDB);
		return "firstPage/navigationBar/add";
	}
	
}
