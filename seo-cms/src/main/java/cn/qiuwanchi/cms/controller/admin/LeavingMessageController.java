package cn.qiuwanchi.cms.controller.admin;

import cn.qiuwanchi.cms.entity.LeavingMessage;
import cn.qiuwanchi.cms.service.ILeavingMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/leavingMessage")
public class LeavingMessageController {

    @Autowired
    private ILeavingMessageService leavingMessageService;

    @RequestMapping
    public String list(Model model, String moduleId) {
        List<LeavingMessage> leavingMessageList = this.leavingMessageService.getList();
        model.addAttribute("leavingMessageList", leavingMessageList);

        return "firstPage/leavingMessage";
    }
}
