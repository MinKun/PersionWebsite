package com.tydd.persion.dto.article;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @ClassName ArticleUpdateDTO
 * @Description 更新文章DTO
 * @Author kun
 * @Date 2019/4/1
 * @Version 1.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleUpdateDTO {

    @NotNull
    private Long id;

    @Size(max = 100)
    private String articleTitle;

    private Integer articleStatus;

    private Integer articleType;

    @Size(max = 200)
    private String articleLabel;

    private String articleContent;

    private String articlePlainText;

    @Override
    public String toString() {
        return "ArticleUpdateDTO{" +
                "id=" + id +
                ", articleTitle='" + articleTitle + '\'' +
                ", articleStatus=" + articleStatus +
                ", articleType=" + articleType +
                ", articleLabel='" + articleLabel + '\'' +
                ", articleContent='" + articleContent + '\'' +
                ", articlePlainText='" + articlePlainText + '\'' +
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

    public Integer getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(Integer articleStatus) {
        this.articleStatus = articleStatus;
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
