package com.qiuwanchi.seo.controller;

import com.qiuwanchi.seo.dto.ModuleDto;
import com.qiuwanchi.seo.dto.ProjectDto;
import com.qiuwanchi.seo.service.IAttachmentService;
import com.qiuwanchi.seo.service.IModuleService;
import com.qiuwanchi.seo.utils.BottomManagementCommon;
import com.qiuwanchi.seo.utils.FileConfiguration;
import com.qiuwanchi.seo.utils.LogoCommon;
import com.qiuwanchi.seo.utils.ServerConfig;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Controller
public class ContactUsController {

    @Autowired
    private FileConfiguration fileConfiguration;

    @Autowired
    private IAttachmentService attachmentService;

    @Autowired
    private ServerConfig serverConfig;

    @Autowired
    private IModuleService moduleService;

    @Autowired
    private BottomManagementCommon bottomManagementCommon;

    @Autowired
    private LogoCommon logoCommon;

    @GetMapping("/contactUs.html")
    public String contactUs(Model model){
        model.addAttribute("baseUrl", serverConfig.getUrl());

        // logo
        this.logoCommon.logo(model);

        // 2.联系我们
        List<ModuleDto> contactUsModuleList = this.moduleService.getModuleDtoList("ContactUs");
        ModuleDto contactUsModuleDto = contactUsModuleList.get(0);
        model.addAttribute("contactUsModuleDto", contactUsModuleDto);

        // 3扫码关注我们
        List<ProjectDto> scanCodeProjectList = CollectionUtils.isEmpty(contactUsModuleDto.getProjectDtoList()) ? new ArrayList<>() : contactUsModuleDto.getProjectDtoList();
        model.addAttribute("scanCodeProjectList", scanCodeProjectList);

        this.bottomManagementCommon.bottomWebsiteNavigation(model);

        return "contact";
    }

}
