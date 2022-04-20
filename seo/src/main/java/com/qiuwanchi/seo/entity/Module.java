package com.qiuwanchi.seo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
@TableName("module")
public class Module implements Serializable {

	private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;

    @TableId("number")
    private Integer number;

    @TableField("attachment_id")
    private String attachmentId;

    @TableField("name")
    private String name;

    @TableField("describe_msg")
    private String describeMsg;

    @TableField("belong")
    private String belong;

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

    @TableField("code")
    private String code;

    public static final String ID = "id";
    public static final String NUMBER = "number";
    public static final String ATTACHMENT_ID = "attachment_id";
    public static final String NAME = "name";
    public static final String DESCRIBE_MSG = "describe_msg";
    public static final String BELONG = "belong";
    public static final String CREATE_BY = "create_by";
    public static final String CREATE_TIME = "create_time";
    public static final String UPDATE_BY = "update_by";
    public static final String UPDATE_TIME = "update_time";
    public static final String TITLE = "title";
    public static final String KEYWORDS = "keywords";
    public static final String DESCRIPTION = "description";
    public static final String ALT = "alt";
    public static final String CLICK_URL = "click_url";
    public static final String CODE = "code";

}