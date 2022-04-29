package cn.qiuwanchi.cms.entity;

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
@Table(name = "navigation_bar")
public class NavigationBar implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "id")
    private String id;

    @Column(name = "html_content")
    private String htmlContent;

    @Column(name = "belong")
    private String belong;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "update_time")
    private Date updateTime;

}