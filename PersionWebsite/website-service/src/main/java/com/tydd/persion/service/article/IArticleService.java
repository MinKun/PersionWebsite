package com.tydd.persion.service.article;

import com.tydd.persion.dto.article.ArticleDTO;
import com.tydd.persion.model.article.ArticleDo;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @ClassName IArticleService
 * @Description 文章模块Service接口
 * @Author kun
 * @Date 2019/3/30
 * @Version 1.0
 */
public interface IArticleService {

    /**
     * 查询列表
     * @param queryMap
     * @return
     */
    Page<ArticleDo> queryArticleList(Map<String, Object> queryMap);

    /**
     * 查询文章详细信息
     * @param id
     * @return
     */
    ArticleDTO queryArticleById(Long id);

    /**
     * 添加文章
     * @param articleDTO
     * @param request
     * @return
     */
    boolean addArticle(ArticleDTO articleDTO, HttpServletRequest request);

    /**
     * 更新文章
     * @param articleDTO
     * @return
     */
    boolean updateArticle(ArticleDTO articleDTO);

    /**
     * 更新文章的内容
     * @param id
     * @param content
     * @param plainText
     * @return
     */
    boolean updateArticleContent(Long id, String content, String plainText);

}
