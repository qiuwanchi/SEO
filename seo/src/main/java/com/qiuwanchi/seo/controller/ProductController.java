package com.qiuwanchi.seo.controller;

import com.qiuwanchi.seo.dto.BannerDto;
import com.qiuwanchi.seo.dto.ModuleDto;
import com.qiuwanchi.seo.dto.ProjectDto;
import com.qiuwanchi.seo.entity.Attachment;
import com.qiuwanchi.seo.entity.Banner;
import com.qiuwanchi.seo.service.IAttachmentService;
import com.qiuwanchi.seo.service.IBannerService;
import com.qiuwanchi.seo.service.IModuleService;
import com.qiuwanchi.seo.service.IProjectService;
import com.qiuwanchi.seo.utils.FileConfiguration;
import com.qiuwanchi.seo.utils.UrlAssemblyUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

@Log4j2
@Controller
public class ProductController {

    @Autowired
    private IBannerService bannerService;

    @Autowired
    private FileConfiguration fileConfiguration;

    @Autowired
    private IAttachmentService attachmentService;

    @Autowired
    private IModuleService moduleService;

    @Autowired
    private IProjectService projectService;

    @GetMapping("/products")
    public String products(Model model){
        // 2.1公司产品-模块列表
        List<ModuleDto> moduleList = this.getModuleDtoList("companyProduct-productModule");
        model.addAttribute("productModuleList", moduleList);

        // 2.2公司产品-模块-项目列表
        if(!CollectionUtils.isEmpty(moduleList)){
            List<ProjectDto> projectDtoList = this.projectService.getProjectListByModuleId(moduleList.get(0).getId());
            model.addAttribute("productModuleProjectList", projectDtoList);
        }




        return "goods";
    }

    private List<ModuleDto> getModuleDtoList(String code){
        List<ModuleDto> moduleList = this.moduleService.getModuleList(code);
        for (ModuleDto moduleDto : moduleList){
            if(StringUtils.isNotBlank(moduleDto.getFilePath())){
                moduleDto.setUrl(UrlAssemblyUtils.getImageUrl(moduleDto.getFilePath()));
            }
        }
        return  moduleList;
    }

}
