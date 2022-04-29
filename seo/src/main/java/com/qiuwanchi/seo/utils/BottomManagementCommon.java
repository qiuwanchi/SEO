package com.qiuwanchi.seo.utils;

import com.google.common.collect.Lists;
import com.qiuwanchi.seo.dto.ModuleDto;
import com.qiuwanchi.seo.dto.ProjectDto;
import com.qiuwanchi.seo.service.IModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class BottomManagementCommon {

    @Autowired
    private IModuleService moduleService;

    public void bottom(Model model) {
        bottomWebsiteNavigation(model);
        bottomContactUs(model);
        bottomFriendlyLinks(model);
    }

    public void bottomContactUs(Model model) {
        // 2.联系我们
        List<ModuleDto> contactUsModuleList = this.moduleService.getModuleDtoList("ContactUs");
        ModuleDto contactUsModuleDto = contactUsModuleList.get(0);
        model.addAttribute("contactUsModuleDto", contactUsModuleDto);

        // 3扫码关注我们
        List<ProjectDto> scanCodeProjectList = CollectionUtils.isEmpty(contactUsModuleDto.getProjectDtoList()) ? new ArrayList<>() : contactUsModuleDto.getProjectDtoList();
        model.addAttribute("scanCodeProjectList", scanCodeProjectList);
    }

    public void bottomWebsiteNavigation(Model model) {
        // 4网站导航
        List<ModuleDto> websiteNavigationModuleList = this.moduleService.getModuleDtoList("WebsiteNavigation");
        model.addAttribute("websiteNavigationModuleList", websiteNavigationModuleList);
    }

    public void bottomFriendlyLinks(Model model) {
        // 友情链接
        List<ModuleDto> friendlyLinksModuleList = this.moduleService.getModuleDtoList("FriendlyLinks");
        ModuleDto moduleDto = friendlyLinksModuleList.get(0);
        List<ProjectDto> friendlyLinksProjectDtoList = moduleDto.getProjectDtoList();
        if(Objects.isNull(friendlyLinksProjectDtoList)){
            friendlyLinksProjectDtoList = Lists.newArrayList();
        }
        model.addAttribute("friendlyLinksProjectDtoList", friendlyLinksProjectDtoList);
    }
}
