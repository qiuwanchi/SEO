package com.qiuwanchi.seo.controller;

import com.qiuwanchi.seo.service.IAttachmentService;
import com.qiuwanchi.seo.utils.FileConfiguration;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Log4j2
@Controller
public class ContactUsController {

    @Autowired
    private FileConfiguration fileConfiguration;

    @Autowired
    private IAttachmentService attachmentService;

    @GetMapping("/contactUs")
    public String contactUs(){

        return "contact";
    }

}
