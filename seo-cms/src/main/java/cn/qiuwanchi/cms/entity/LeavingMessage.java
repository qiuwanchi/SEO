package cn.qiuwanchi.cms.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "leaving_message")
public class LeavingMessage implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "message")
    private String message;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    public static final String ID = "id";
    public static final String SYSTEM_ATTACHMENT_ID = "system_attachment_id";
    public static final String NAME = "name";
    public static final String BELONG = "belong";
    public static final String CREATE_BY = "create_by";
    public static final String CREATE_TIME = "create_time";
    public static final String UPDATE_BY = "update_by";
    public static final String YPDATE_TIME = "update_time";

}