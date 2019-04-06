package com.tydd.persion.dto.article;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @ClassName ArticleAddDTO
 * @Description 添加文章DTO参数
 * @Author kun
 * @Date 2019/4/5
 * @Version 1.0
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleAddDTO {

    /** 文章标题 */
    @NotBlank
    @Size(max = 100)
    private String articleTitle;

    /** 文章类型 */
    @NotNull
    private Integer articleType;

    /** 文章标签 */
    @Size(max = 200)
    private String articleLabel;

    /** 文章状态 */
    @NotNull
    private Integer articleStatus;

    /** 文章内容（带格式）*/
    private String articleContent;

    /** 文章内容（纯文本）*/
    private String articlePlainText;

    @Override
    public String toString() {
        return "ArticleAddDTO{" +
                "articleTitle='" + articleTitle + '\'' +
                ", articleType=" + articleType +
                ", articleLabel='" + articleLabel + '\'' +
                ", articleStatus=" + articleStatus +
                ", articleContent='" + articleContent + '\'' +
                ", articlePlainText='" + articlePlainText + '\'' +
                '}';
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
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

    public Integer getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(Integer articleStatus) {
        this.articleStatus = articleStatus;
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
}