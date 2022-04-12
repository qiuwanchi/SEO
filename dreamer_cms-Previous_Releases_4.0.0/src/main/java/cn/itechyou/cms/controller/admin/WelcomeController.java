package cn.itechyou.cms.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/welcome")
public class WelcomeController {

    @RequestMapping("/index")
    public String index() {
        return "admin/dashboard/index2";
    }
}
