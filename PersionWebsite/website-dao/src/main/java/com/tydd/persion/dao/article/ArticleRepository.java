package com.tydd.persion.dao.article;

import com.tydd.persion.model.article.ArticleDo;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @ClassName ArticleRepository
 * @Description 文章DAO
 * @Author kun
 * @Date 2019/3/30
 * @Version 1.0
 */
public interface ArticleRepository extends JpaRepository<ArticleDo, Long>, JpaSpecificationExecutor<ArticleDo> {

}
