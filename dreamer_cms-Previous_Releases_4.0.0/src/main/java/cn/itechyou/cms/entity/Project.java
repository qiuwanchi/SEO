package cn.itechyou.cms.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 模块的项目
 */
@Data
@Table(name = "project")
public class Project implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "describe_msg")
    private String describe;

    @Column(name = "module_id")
    private String moduleId;

    @Column(name = "system_attachment_id")
    private String systemAttachmentId;

    @Column(name = "sort")
    private String sort;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "update_time")
    private Date updateTime;

    private Attachment attachment;
}