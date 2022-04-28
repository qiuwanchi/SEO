package com.qiuwanchi.seo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Accessors(chain = true)
@TableName("sub_project")
public class SubProject implements Serializable {

	private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;

    @TableId("number")
    private Integer number;

    @TableField("project_id")
    private String projectId;

    @TableField("attachment_id")
    private String attachmentId;

    @TableField("name")
    private String name;

    @TableField("sort")
    private Integer sort;

    @TableField("create_by")
    private String createBy;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_by")
    private String updateBy;

    @TableField("update_time")
    private Date updateTime;

    @TableField("title")
    private String  title;

    @TableField("keywords")
    private String  keywords;

    @TableField("description")
    private String  description;

    @TableField("alt")
    private String  alt;

    @TableField("click_url")
    private String clickUrl;

    @TableField("content")
    private String content;

    @TableField("banner_id")
    private String bannerId;

    public static final String ID = "id";
    public static final String NUMBER = "number";
    public static final String PROJECT_ID = "project_id";
    public static final String ATTACHMENT_ID = "attachment_id";
    public static final String NAME = "name";
    public static final String SORT = "sort";
    public static final String CREATE_BY = "create_by";
    public static final String CREATE_TIME = "create_time";
    public static final String UPDATE_BY = "update_by";
    public static final String UPDATE_TIME = "update_time";
    public static final String TITLE = "title";
    public static final String KEYWORDS = "keywords";
    public static final String DESCRIPTION = "description";
    public static final String ALT = "alt";
    public static final String CLICK_URL = "click_url";
    public static final String CONTENT = "content";

}