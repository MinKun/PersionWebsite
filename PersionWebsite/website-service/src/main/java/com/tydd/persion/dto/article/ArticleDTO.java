package com.tydd.persion.dto.article;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tydd.persion.dto.BaseDTO;
import com.tydd.persion.model.user.AdminUserDo;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @ClassName ArticleDTO
 * @Description 文章DTO
 * @Author kun
 * @Date 2019/3/30
 * @Version 1.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleDTO extends BaseDTO {

    private Long id;

    /** 文章标题 */
    @NotEmpty
    private String articleTitle;

    /** 文章内容（带格式） */
    @NotEmpty
    private String articleContent;

    /** 文章内容（纯文本） */
    @NotEmpty
    private String articlePlainText;

    /** 文章类型 */
    @NotNull
    private Integer articleType;

    /** 文章标签 */
    private String articleLabel;

    /** 创建者 */
    private AdminUserDo createUser;

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;

    /** 发布时间 */
    private Date releaseTime;

    /** 文章状态 */
    private Integer articleStatus;

    @Override
    public String toString() {
        return "ArticleDTO{" +
                "id=" + id +
                ", articleTitle='" + articleTitle + '\'' +
                ", articleContent=" + articleContent +
                ", articlePlainText='" + articlePlainText + '\'' +
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

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public String getArticlePlainText() {
        return articlePlainText;
    }

    public void setArticlePlainText(String articlePlainText) {
        this.articlePlainText = articlePlainText;
    }

    public Integer getArticleType() {
        return articleType;
    }

    public void setArticleType(Integer articleType) {
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
