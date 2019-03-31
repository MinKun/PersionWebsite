package com.tydd.persion.dao.article;

import com.tydd.persion.model.article.ArticleContentDo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName ArticleContentRepository
 * @Description 文章内容DAO
 * @Author kun
 * @Date 2019/3/30
 * @Version 1.0
 */
public interface ArticleContentRepository extends JpaRepository<ArticleContentDo, Long> {
}
