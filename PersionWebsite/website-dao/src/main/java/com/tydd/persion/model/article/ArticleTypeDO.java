package com.tydd.persion.model.article;

import javax.persistence.*;

/**
 * @ClassName ArticleTypeDO
 * @Description 文章类型
 * @Author kun
 * @Date 2019/5/12
 * @Version 1.0
 */
@Entity
@Table(name = "t_article_type")
public class ArticleTypeDO {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "article_type", nullable = false, columnDefinition = "varchar(50) comment '文章类型'")
    private String typeName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
