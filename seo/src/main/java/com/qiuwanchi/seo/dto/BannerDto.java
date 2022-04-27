package com.qiuwanchi.seo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ex_qiuwc1
 */
@Data
public class BannerDto implements Serializable {

    private String id;

    private String attachmentId;

    private String name;

    private String  alt;

    private String clickUrl;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String fileName;

    private Integer fileSize;

    private String fileType;

    private String filePath;

    private String fileId;

    private String url;

}
