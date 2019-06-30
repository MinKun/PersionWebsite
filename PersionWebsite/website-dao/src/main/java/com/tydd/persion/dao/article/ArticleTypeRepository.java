package com.tydd.persion.dao.article;

import com.tydd.persion.model.article.ArticleTypeDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName ArticleTypeRepository
 * @Description 文章类型DAO
 * @Author kun
 * @Date 2019/5/12
 * @Version 1.0
 */
public interface ArticleTypeRepository extends JpaRepository<ArticleTypeDO, Long> {
}
