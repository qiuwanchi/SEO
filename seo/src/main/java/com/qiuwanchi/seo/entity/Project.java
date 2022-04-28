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
@TableName("project")
public class Project implements Serializable {

	private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;

    @TableField("number")
    private Integer number;

    @TableField("module_id")
    private String moduleId;

    @TableField("attachment_id")
    private String attachmentId;

    @TableField("seo_image_id")
    private String seoImageId;

    @TableField("name")
    private String name;

    @TableField("describe_msg")
    private String describeMsg;

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

    @TableField("code")
    private String code;

    @TableField("banner_id")
    private String bannerId;

    public static final String ID = "id";
    public static final String NUMBER = "number";
    public static final String MODULE_ID = "module_id";
    public static final String ATTACHMENT_ID = "attachment_id";
    public static final String NAME = "name";
    public static final String DESCRIBE_MSG = "describe_msg";
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
    public static final String CODE = "code";

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("module_id");
        list.add("system_attachment_id");
        list.add("name");
        list.add("sort");
        list.add("create_by");
        list.add("create_time");
        list.add("update_by");
        list.add("update_time");
        list.add("title");
        list.add("keywords");
        list.add("description");
        list.add("alt");
        list.add("click_url");

        for (String a : list){
            System.out.println("public static final String " + a.toUpperCase() + " = " + "\"" + a + "\";");
        }

    }

}