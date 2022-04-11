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
@TableName("project")
public class Project implements Serializable {

	private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;

    @TableField("module_id")
    private String moduleId;

    @TableField("system_attachment_id")
    private String attachmentId;

    @TableField("name")
    private String name;

    @TableField("create_by")
    private String createBy;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_by")
    private String updateBy;

    @TableField("update_time")
    private Date updateTime;

    public static final String ID = "id";
    public static final String ATTACHMENT_ID = "attachment_id";
    public static final String NAME = "name";
    public static final String BELONG = "belong";
    public static final String CREATE_BY = "create_by";
    public static final String CREATE_TIME = "create_time";
    public static final String UPDATE_BY = "update_by";
    public static final String UPDATE_TIME = "update_time";

}