package com.qiuwanchi.seo.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

@Data
public class ProjectDto {

    private String id;

    private String name;

    private String describeMsg;

    private String moduleId;

    private String url;

    private String attachmentId;

    private String fileName;

    private Integer fileSize;

    private String fileType;

    private String filePath;

    private String fileId;

    private Date createTime;

    /**
     * 年月
     */
    private String years;

    private String day;

}
