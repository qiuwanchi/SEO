package com.qiuwanchi.seo.controller;

import com.qiuwanchi.seo.service.IAttachmentService;
import com.qiuwanchi.seo.utils.FileConfiguration;
import com.qiuwanchi.seo.utils.ServerConfig;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Log4j2
@Controller
public class ContactUsController {

    @Autowired
    private FileConfiguration fileConfiguration;

    @Autowired
    private IAttachmentService attachmentService;

    @Autowired
    private ServerConfig serverConfig;

    @GetMapping("/contactUs")
    public String contactUs(Model model){
        model.addAttribute("baseUrl", serverConfig.getUrl());

        return "contact";
    }

}
