package com.tydd.persion.model.article;

import javax.persistence.*;

/**
 * @ClassName ArticleContentDo
 * @Description 文章内容表
 * @Author kun
 * @Date 2019/3/30
 * @Version 1.0
 */
@Entity
@Table(name = "t_article_content")
public class ArticleContentDo {

    @Id
    @GeneratedValue
    private Long id;

    /**
     * 文章内容（带格式）
     */
    @Column(name = "article_content", nullable = false, columnDefinition = "text comment '文章内容（带格式）'")
    private String articleContent;

    /**
     * 文章内容（纯文本）
     */
    @Column(name = "article_plain_text", nullable = false, columnDefinition = "text comment '文章内容（纯文本）'")
    private String articlePlainText;

    @Override
    public String toString() {
        return "ArticleContentDo{" +
                "id=" + id +
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
