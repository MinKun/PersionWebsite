package com.tydd.persion.service.article.impl;

import com.tydd.persion.dao.article.ArticleContentRepository;
import com.tydd.persion.dao.article.ArticleRepository;
import com.tydd.persion.dto.BaseResponseDTO;
import com.tydd.persion.dto.ResponseDataDTO;
import com.tydd.persion.dto.article.ArticleDTO;
import com.tydd.persion.model.article.ArticleContentDo;
import com.tydd.persion.model.article.ArticleDo;
import com.tydd.persion.service.article.IArticleService;
import com.tydd.persion.util.PagingUtil;
import com.tydd.persion.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ArticleServiceImpl
 * @Description 文章模块Service实现
 * @Author kun
 * @Date 2019/3/30
 * @Version 1.0
 */
@Service
public class ArticleServiceImpl implements IArticleService {

    private static Logger LOGGER = LoggerFactory.getLogger(ArticleServiceImpl.class);

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleContentRepository articleContentRepository;

    /**
     * 查询文章列表
     * @param queryMap
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public BaseResponseDTO queryArticleList(Map<String, Object> queryMap) {
        BaseResponseDTO responseDTO = new BaseResponseDTO();
        List<ArticleDTO> articleList = new ArrayList<>();

        // 创建动态查询语句
        Specification specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();

                Integer articleStatus = (Integer) queryMap.get("articleStatus");
                if (articleStatus != null) {
                    predicateList.add(criteriaBuilder.equal(root.get("articleStatus"), articleStatus));
                }

                Integer articleType = (Integer) queryMap.get("articleType");
                if (articleStatus != null) {
                    predicateList.add(criteriaBuilder.equal(root.get("articleType"), articleType));
                }

                String articleTitle = (String) queryMap.get("articleTitle");
                if (!StringUtil.isEmpty(articleTitle)) {
                    predicateList.add(criteriaBuilder.like(root.get("articleTitle"),"%" + articleTitle + "%"));
                }
                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };

        Integer pageNumber = (Integer) queryMap.get("pageNumber");
        Integer pageSize = (Integer) queryMap.get("pageSize");
        String order = (String) queryMap.get("order");
        Page<ArticleDo> articleDoPage = articleRepository.findAll(specification, PagingUtil.buildPageRequest(pageNumber, pageSize, order));
        if (articleDoPage.getSize() > 0) {
            List<ArticleDo> articleDoList = articleDoPage.getContent();
            ArticleDTO articleDTO = null;
            for (ArticleDo articleDo : articleDoList) {
                articleDTO = new ArticleDTO();
                BeanUtils.copyProperties(articleDo, articleDTO);
                articleList.add(articleDTO);
            }
            ResponseDataDTO responseDataDTO = new ResponseDataDTO();
            responseDataDTO.setDataList(articleList);
            responseDataDTO.setPageNumber(pageNumber);
            responseDataDTO.setPageSize(pageSize);
            responseDataDTO.setTotalSize(articleDoPage.getTotalElements());
            responseDataDTO.setTotalPage(articleDoPage.getTotalPages());
            responseDTO.setResp(responseDataDTO);
        }
        return responseDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public ArticleDTO queryArticleById(Long id) {
        ArticleDo articleDo = articleRepository.findOne(id);
        ArticleDTO articleDTO = null;
        if (articleDo != null) {
            articleDTO = new ArticleDTO();
            BeanUtils.copyProperties(articleDo, articleDTO);
        } else {
            LOGGER.error("未查询到ID=" + id + "的article表数据");
        }
        return articleDTO;
    }

    /**
     * 添加文章
     *
     * @param articleDTO
     * @return
     */
    @Override
    @Transactional
    public boolean addArticle(ArticleDTO articleDTO) {
        boolean addFlag = false;
        // 保存文章内容表数据
        ArticleContentDo articleContentDo = new ArticleContentDo();
        articleContentDo.setArticleContent(articleDTO.getArticleContent());
        articleContentDo.setArticlePlainText(articleContentDo.getArticlePlainText());
        articleContentDo = articleContentRepository.save(articleContentDo);
        // 保存文章表数据
        ArticleDo articleDo = new ArticleDo();
        BeanUtils.copyProperties(articleDTO, articleDo);
        articleDo.setArticleContentDo(articleContentDo);
        articleDo = articleRepository.save(articleDo);

        if (articleDo != null) {
            addFlag = true;
        }
        return addFlag;
    }

    /**
     * 更新文章
     *
     * @param articleDTO
     * @return
     */
    @Override
    @Transactional
    public boolean updateArticle(ArticleDTO articleDTO) {
        boolean updateFlag = false;
        ArticleDo articleDo = new ArticleDo();
        BeanUtils.copyProperties(articleDo, articleDTO);
        articleDo = articleRepository.save(articleDo);
        if (articleDo != null) {
            updateFlag = true;
        }
        return updateFlag;
    }

    /**
     * 更新文章的内容
     *
     * @param id
     * @param content
     * @param plainText
     * @return
     */
    @Override
    @Transactional
    public boolean updateArticleContent(Long id, String content, String plainText) {
        boolean updateFlag = false;
        ArticleContentDo articleContentDo = new ArticleContentDo();
        articleContentDo.setId(id);
        articleContentDo.setArticleContent(content);
        articleContentDo.setArticlePlainText(plainText);
        articleContentDo = articleContentRepository.save(articleContentDo);
        if (articleContentDo != null) {
            updateFlag = true;
        }
        return updateFlag;
    }
}
