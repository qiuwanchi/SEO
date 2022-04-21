package com.qiuwanchi.seo.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
public class SeoVideoDto implements Serializable {

	private static final long serialVersionUID = 1L;

    private String id;

    private Integer sort;

    private String belongId;

    private String attachmentId;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;


    /**
     * 图片或者视频全路径
     */
    private String url;

    private String fileName;
    private Integer fileSize;
    private String fileType;
    private String filePath;
    private String fileId;
}