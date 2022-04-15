package com.qiuwanchi.seo.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.List;

@Data
public class ModuleDto {

    private String id;

    private String name;

    private String belong;

    private String url;

    private String attachmentId;

    private String fileName;

    private Integer fileSize;

    private String fileType;

    private String filePath;

    private String fileId;

    private String title;

    private String keywords;

    private String description;

    private String alt;

    private String clickUrl;

    private List<ProjectDto> projectDtoList;

}
