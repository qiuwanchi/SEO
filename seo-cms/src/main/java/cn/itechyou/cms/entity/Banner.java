package cn.itechyou.cms.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 文章
 * @author 王俊南
 * Date: 2020-12-29
 */
@Data
@Table(name = "banner")
public class Banner implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "id")
    private String id;

    @Column(name = "attachment_id")
    private String attachmentId;

    @Column(name = "name")
    private String name;

    @Column(name = "click_url")
    private String clickUrl;

    @Column(name = "alt")
    private String alt;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "update_time")
    private Date updateTime;

    private Attachment attachment;

    public static final String ID = "id";
    public static final String SYSTEM_ATTACHMENT_ID = "system_attachment_id";
    public static final String NAME = "name";
    public static final String BELONG = "belong";
    public static final String CREATE_BY = "create_by";
    public static final String CREATE_TIME = "create_time";
    public static final String UPDATE_BY = "update_by";
    public static final String YPDATE_TIME = "update_time";

}