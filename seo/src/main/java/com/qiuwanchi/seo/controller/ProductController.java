package com.qiuwanchi.seo.controller;

import com.qiuwanchi.seo.dto.ModuleDto;
import com.qiuwanchi.seo.dto.ProjectDto;
import com.qiuwanchi.seo.dto.SeoImageDto;
import com.qiuwanchi.seo.dto.SubProjectDto;
import com.qiuwanchi.seo.entity.SeoImage;
import com.qiuwanchi.seo.service.IAttachmentService;
import com.qiuwanchi.seo.service.IModuleService;
import com.qiuwanchi.seo.service.IProjectService;
import com.qiuwanchi.seo.service.ISeoImageService;
import com.qiuwanchi.seo.utils.*;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 公司产品
 */
@Log4j2
@Controller
public class ProductController {

    @Autowired
    private FileConfiguration fileConfiguration;

    @Autowired
    private IAttachmentService attachmentService;

    @Autowired
    private IModuleService moduleService;

    @Autowired
    private IProjectService projectService;

    @Autowired
    private ServerConfig serverConfig;

    @Autowired
    private BottomManagementCommon bottomManagementCommon;

    @Autowired
    private LogoCommon logoCommon;

    @Autowired
    private ISeoImageService seoImageService;

    @GetMapping("/products.html")
    public String products(Model model){
        model.addAttribute("baseUrl", serverConfig.getUrl());

        // logo
        this.logoCommon.logo(model);

        // 2.公司产品-模块列表
        List<ModuleDto> moduleList = this.moduleService.getModuleDtoList("companyProduct-productModule");
        model.addAttribute("productModuleList", moduleList);

        this.bottomManagementCommon.bottom(model);

        return "goods";
    }

    /**
     * 公司唱片详情页
     * @param model
     * @param firstCategory
     * @param number
     * @return
     */
    @GetMapping("/products/{firstCategory}/{number}.html")
    public String productsDetail(Model model, @PathVariable("firstCategory") String firstCategory, @PathVariable("number") int number){
        model.addAttribute("baseUrl", serverConfig.getUrl());

        // logo
        this.logoCommon.logo(model);

        ProjectDto currentProjectDto = this.projectService.selectByNumber(number);
        currentProjectDto.setUrl(UrlAssemblyUtils.getImageUrl(currentProjectDto.getFilePath()));
        if(StringUtils.isBlank(currentProjectDto.getContent())){
            currentProjectDto.setContent(StringUtils.EMPTY);
        }else{
            currentProjectDto.setContent(Utils.htmlDecode(currentProjectDto.getContent()));
        }
        model.addAttribute("currentProjectDto", currentProjectDto);

        SeoImageDto seoImageDto = this.seoImageService.selectById(currentProjectDto.getSeoImageId());
        seoImageDto.setUrl(UrlAssemblyUtils.getImageUrl(seoImageDto.getFilePath()));
        model.addAttribute("seoImageDto", seoImageDto);

        this.bottomManagementCommon.bottom(model);

        return "goods_detail";
    }

}
