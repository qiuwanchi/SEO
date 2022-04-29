package cn.itechyou.cms.dto;

import cn.itechyou.cms.entity.Attachment;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 模块的项目
 */
@Data
public class ProjectDto implements Serializable {

	private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String describeMsg;

    private String moduleId;

    private String attachmentId;

    private String leftAttachmentId;

    private Integer sort;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private Attachment attachment;

    private String  title;

    private String  keywords;

    private String  description;

    private String  alt;

    private String clickUrl;

    private String content;

    private String code;

    private String moduleName;

}