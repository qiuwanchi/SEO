package cn.itechyou.cms.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 模块
 */
@Data
@Table(name = "module")
public class Module implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "describe_msg")
    private String describeMsg;

    @Column(name = "belong")
    private String belong;

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

    private List<Project> projectList;

    @Column(name = "attachment_id")
    private String  attachmentId;
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

    @Column(name = "code")
    private String  code;

    @Column(name = "home_page_display")
    private String  homePageDisplay;

}