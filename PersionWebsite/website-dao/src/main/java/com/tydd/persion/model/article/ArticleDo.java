package com.tydd.persion.model.article;

/**
 * @ClassName ArticleDo
 * @Description 文章表
 * @Author kun
 * @Date 2019/3/30
 * @Version 1.0
 */
import com.tydd.persion.model.user.AdminUserDo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_article")
public class ArticleDo {

    @Id
    @GeneratedValue
    private Long id;

    /** 文章标题 */
    @Column(name = "article_title", nullable = false, columnDefinition = "varchar(200) comment '文章标题'")
    private String articleTitle;

    /** 文章内容 */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ArticleContentDo articleContentDo;

    /** 文章类型 */
    @ManyToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name="articleType", nullable = true)
    private ArticleTypeDO articleType;

    /** 文章标签 */
    @Column(name = "article_label", nullable = true, columnDefinition = "varchar(300) comment '文章标签，多个逗号分隔'")
    private String articleLabel;

    /** 创建者 */
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name="createUser", nullable = true)
    private AdminUserDo createUser;

    /** 创建时间 */
    @Column(name = "create_time", nullable = false, columnDefinition = "datetime comment '创建时间'")
    private Date createTime;

    /** 更新时间 */
    @Column(name = "update_time", nullable = true, columnDefinition = "datetime comment '更新时间'")
    private Date updateTime;

    /** 发布时间 */
    @Column(name = "release_time", nullable = true, columnDefinition = "datetime comment '发布时间'")
    private Date releaseTime;

    /** 文章状态 */
    @Column(name = "article_status", nullable = false, columnDefinition = "int(2) comment '文章状态（1：草稿 2：发布 3：下架）'")
    private Integer articleStatus;

    @Override
    public String toString() {
        return "ArticleDo{" +
                "id=" + id +
                ", articleTitle='" + articleTitle + '\'' +
                ", articleContentDo=" + articleContentDo +
                ", articleType=" + articleType +
                ", articleLabel='" + articleLabel + '\'' +
                ", createUser=" + createUser +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", releaseTime=" + releaseTime +
                ", articleStatus=" + articleStatus +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public ArticleContentDo getArticleContentDo() {
        return articleContentDo;
    }

    public void setArticleContentDo(ArticleContentDo articleContentDo) {
        this.articleContentDo = articleContentDo;
    }

    public ArticleTypeDO getArticleType() {
        return articleType;
    }

    public void setArticleType(ArticleTypeDO articleType) {
        this.articleType = articleType;
    }

    public String getArticleLabel() {
        return articleLabel;
    }

    public void setArticleLabel(String articleLabel) {
        this.articleLabel = articleLabel;
    }

    public AdminUserDo getCreateUser() {
        return createUser;
    }

    public void setCreateUser(AdminUserDo createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Integer getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(Integer articleStatus) {
        this.articleStatus = articleStatus;
    }
}
