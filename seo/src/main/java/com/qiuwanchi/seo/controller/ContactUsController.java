package com.qiuwanchi.seo.controller;

import com.qiuwanchi.seo.constant.ThreeElementsOfColumnPageSeoEnum;
import com.qiuwanchi.seo.dto.BannerDto;
import com.qiuwanchi.seo.dto.ModuleDto;
import com.qiuwanchi.seo.dto.ProjectDto;
import com.qiuwanchi.seo.service.IAttachmentService;
import com.qiuwanchi.seo.service.IBannerService;
import com.qiuwanchi.seo.service.IModuleService;
import com.qiuwanchi.seo.service.IProjectService;
import com.qiuwanchi.seo.utils.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Objects;

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

    @Autowired
    private IBannerService bannerService;

    @Autowired
    private IProjectService projectService;

    @GetMapping("/contactUs.html")
    public String contactUs(Model model){
        model.addAttribute("baseUrl", serverConfig.getUrl());

        // logo
        this.logoCommon.logo(model);

        ProjectDto seoProjectDto = this.projectService.selectSeoThreeElements(ThreeElementsOfColumnPageSeoEnum.CONTACT_US.getCode());
        model.addAttribute("seoProjectDto", seoProjectDto);

        // 2.联系我们
        List<ModuleDto> contactUsModuleList = this.moduleService.getModuleDtoList("ContactUs");
        ModuleDto contactUsModuleDto = contactUsModuleList.get(0);

        // 解决方案的banner
        BannerDto bannerDto = this.bannerService.selectById(contactUsModuleDto.getBannerId());
        if(Objects.isNull(bannerDto)){
            bannerDto = new BannerDto();
        }
        SeoUtils.intBannerSeoValue(bannerDto);
        model.addAttribute("bannerDto", bannerDto);

        // 底部
        this.bottomManagementCommon.bottom(model);
        return "contact";
    }

}
