package com.qiuwanchi.seo.utils;

import com.alibaba.fastjson.JSON;
import com.qiuwanchi.seo.dto.ModuleDto;
import com.qiuwanchi.seo.dto.ProjectDto;
import com.qiuwanchi.seo.dto.SeoVideoDto;
import com.qiuwanchi.seo.service.IModuleService;
import com.qiuwanchi.seo.service.ISeoVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class VideoCommon {

    @Autowired
    private ISeoVideoService seoVideoService;

    public void video(Model model, String belongId) {
        List<SeoVideoDto> seoVideoDtoList = this.seoVideoService.getVideoListByBelongId(belongId);
        List<String> seoVideoUrlArray = new ArrayList<>();
        for (SeoVideoDto seoVideoDto : seoVideoDtoList){
            seoVideoDto.setUrl(UrlAssemblyUtils.getVideoUrl(seoVideoDto.getFilePath()));
            seoVideoUrlArray.add(seoVideoDto.getUrl());
        }
        model.addAttribute("seoVideoDtoList", seoVideoDtoList);
        model.addAttribute("seoVideoUrlArray", JSON.toJSONString(seoVideoUrlArray));

    }

}
