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
@TableName("system_attachment")
public class Attachment implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId("id")
    private String id;

	@TableField("code")
    private String code;

	@TableField("filename")
    private String filename;

	@TableField("filesize")
    private Integer filesize;

	@TableField("filetype")
    private String filetype;

	@TableField("filepath")
	private String filepath;

	@TableField("file_id")
	private String fileId;

	@TableField("create_by")
    private String createBy;

	@TableField("create_time")
    private Date createTime;

	@TableField("update_by")
    private String updateBy;

	@TableField("update_time")
    private Date updateTime;


	public static final String ID = "id";
	public static final String FILE_ID = "file_id";
	public static final String NAME = "name";
	public static final String BELONG = "belong";
	public static final String CREATE_BY = "create_by";
	public static final String CREATE_TIME = "create_time";
	public static final String UPDATE_BY = "update_by";
	public static final String UPDATE_TIME = "update_time";
}