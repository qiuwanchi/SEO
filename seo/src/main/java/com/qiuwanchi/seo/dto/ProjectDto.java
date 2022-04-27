package com.qiuwanchi.seo.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ProjectDto {

    private String id;
    private Integer number;
    private String name;
    private String describeMsg;
    private String moduleId;
    private String attachmentId;
    private String seoImageId;
    private Integer sort;
    private String title;
    private String keywords;
    private String description;
    private String alt;
    private String clickUrl;
    private String content;
    private String code;
    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;

    /**
     * 年月(根据创建时间来的)
     */
    private String years;

    /**
     * 日(根据创建时间来的)
     */
    private String day;

    /**
     * 月-日
     */
    private String monthDay;

    private String moduleName;

    /**
     * 图片或者视频全路径
     */
    private String url;

    private String fileName;
    private Integer fileSize;
    private String fileType;
    private String filePath;
    private String fileId;

    private String moduleCode;

    private String bannerId;

    private List<SubProjectDto> subProjectDtoList;

}
