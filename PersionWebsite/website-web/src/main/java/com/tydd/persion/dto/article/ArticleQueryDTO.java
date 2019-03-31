package com.tydd.persion.dto.article;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tydd.persion.dto.PageDTO;

/**
 * @ClassName ArticleQueryDTO
 * @Description TODO
 * @Author kun
 * @Date 2019/3/30
 * @Version 1.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleQueryDTO extends PageDTO {

    /** 文章标题 */
    private String articleTitle;

    /** 文章类型 */
    private Integer articleType;

    /** 文章状态 */
    private Integer articleStatus;

    @Override
    public String toString() {
        return "ArticleQueryDTO{" +
                "articleTitle='" + articleTitle + '\'' +
                ", articleType=" + articleType +
                ", articleStatus=" + articleStatus +
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

    public Integer getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(Integer articleStatus) {
        this.articleStatus = articleStatus;
    }
}
