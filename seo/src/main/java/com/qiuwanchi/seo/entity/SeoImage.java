package com.qiuwanchi.seo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * seo_image
 */
@Data
@TableName("seo_image")
public class SeoImage implements Serializable {

	private static final long serialVersionUID = 1L;

     @TableId( "id")
    private String id;

     @TableId( "name")
    private String name;

     @TableId( "attachment_id")
    private String attachmentId;

     @TableId( "alt")
    private String  alt;

     @TableId( "click_url")
    private String clickUrl;

     @TableId( "create_by")
    private String createBy;

     @TableId( "create_time")
    private Date createTime;

     @TableId( "update_by")
    private String updateBy;

     @TableId( "update_time")
    private Date updateTime;

}