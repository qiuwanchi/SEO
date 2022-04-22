package com.qiuwanchi.seo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * seo_image
 */
@Data
public class SeoImageDto implements Serializable {

	private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String attachmentId;

    private String  alt;

    private String clickUrl;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String url;

    private String fileName;
    private Integer fileSize;
    private String fileType;
    private String filePath;
    private String fileId;

}