package cn.qiuwanchi.cms.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 项目子项目表
 */
@Data
@Table(name = "sub_project")
public class SubProject implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "describe_msg")
    private String describeMsg;

    @Column(name = "project_id")
    private String projectId;

    @Column(name = "attachment_id")
    private String attachmentId;

    @Column(name = "sort")
    private Integer sort;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "update_time")
    private Date updateTime;

    private Attachment attachment;

    @Column(name = "title")
    private String  title;

    @Column(name = "keywords")
    private String  keywords;

    @Column(name = "description")
    private String  description;

    @Column(name = "alt")
    private String  alt;

    @Column(name = "click_url")
    private String clickUrl;

    @Column(name = "content")
    private String content;

    @Column(name = "home_page_display")
    private String  homePageDisplay;

    @Column(name = "banner_id")
    private String  bannerId;

}