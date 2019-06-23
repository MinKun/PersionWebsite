package com.tydd.persion.service.article.impl;

import com.tydd.persion.dao.article.ArticleContentRepository;
import com.tydd.persion.dao.article.ArticleRepository;
import com.tydd.persion.dao.article.ArticleTypeRepository;
import com.tydd.persion.dao.user.AdminUserRepository;
import com.tydd.persion.dto.article.ArticleDTO;
import com.tydd.persion.dto.user.AdminUserDTO;
import com.tydd.persion.model.article.ArticleContentDo;
import com.tydd.persion.model.article.ArticleDo;
import com.tydd.persion.model.article.ArticleTypeDO;
import com.tydd.persion.model.user.AdminUserDo;
import com.tydd.persion.service.article.IArticleService;
import com.tydd.persion.util.PagingUtil;
import com.tydd.persion.util.SessionHelper;
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
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Autowired
    private ArticleTypeRepository articleTypeRepository;

    /**
     * 查询文章列表
     * @param queryMap
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ArticleDo> queryArticleList(final Map<String, Object> queryMap) {
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
        return articleRepository.findAll(specification, PagingUtil.buildPageRequest(pageNumber, pageSize, order));
    }

    /**
     * 根据id查询文章
     * @param id
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public ArticleDTO queryArticleById(Long id) {
        ArticleDo articleDo = articleRepository.findOne(id);
        ArticleDTO articleDTO = null;
        if (articleDo != null) {
            articleDTO = new ArticleDTO();
            BeanUtils.copyProperties(articleDo, articleDTO);
            ArticleContentDo articleContentDo = articleDo.getArticleContentDo();
            if (articleContentDo != null) {
                articleDTO.setArticleContent(articleContentDo.getArticleContent());
                articleDTO.setArticlePlainText(articleContentDo.getArticlePlainText());
            }
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
    public boolean addArticle(ArticleDTO articleDTO, HttpServletRequest request) {
        boolean addFlag = false;
        // 保存文章内容表数据
        String articleContent = articleDTO.getArticleContent();
        String articlePlainText = articleDTO.getArticlePlainText();
        ArticleContentDo articleContentDo = null;
        if (!StringUtil.isEmpty(articleContent) && !StringUtil.isEmpty(articlePlainText)) {
            articleContentDo = new ArticleContentDo();
            articleContentDo.setArticleContent(articleDTO.getArticleContent());
            articleContentDo.setArticlePlainText(articleDTO.getArticlePlainText());
            articleContentDo = articleContentRepository.save(articleContentDo);
        }

        // 保存文章表数据
        ArticleDo articleDo = new ArticleDo();
        BeanUtils.copyProperties(articleDTO, articleDo);
        AdminUserDTO adminUserDTO = SessionHelper.getLoginAdminUser(request);
        AdminUserDo adminUserDo = adminUserRepository.findOne(adminUserDTO.getId());
        articleDo.setCreateUser(adminUserDo);
        articleDo.setCreateTime(new Date());
        if (articleContentDo != null) {
            articleDo.setArticleContentDo(articleContentDo);
        }
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
        Date currentTime = new Date();
        ArticleDo articleDo = articleRepository.findOne(articleDTO.getId());
        if (articleDo != null) {
            // 更新文章基本信息
            String articleTitle = articleDTO.getArticleTitle();
            Long articleTypeId = articleDTO.getArticleTypeId();
            String articleLabel = articleDTO.getArticleLabel();
            Integer articleStatus = articleDTO.getArticleStatus();
            if (!StringUtil.isEmpty(articleTitle)) {
                articleDo.setArticleTitle(articleTitle);
            }
            if (articleTypeId != null) {
                ArticleTypeDO articleType = articleTypeRepository.findOne(articleTypeId);
                if (articleType != null) {
                    articleDo.setArticleType(articleType);
                }
            }
            if (!StringUtil.isEmpty(articleLabel)) {
                articleDo.setArticleLabel(articleLabel);
            }
            if (articleStatus != null) {
                if (articleStatus == 2) {
                    articleDo.setReleaseTime(currentTime);
                }
                articleDo.setArticleStatus(articleStatus);
            }

            // 更新文章内容
            String articleContent = articleDTO.getArticleContent();
            String articlePlainText = articleDTO.getArticlePlainText();
            if (!StringUtil.isEmpty(articleContent) && !StringUtil.isEmpty(articlePlainText)) {
                ArticleContentDo articleContentDo = articleDo.getArticleContentDo();
                articleContentDo.setArticleContent(articleContent);
                articleContentDo.setArticlePlainText(articlePlainText);
            }
            articleRepository.save(articleDo);
        }
        articleDo.setUpdateTime(currentTime);
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

    /**
     * 查询所有的文章类型
     *
     * @return
     */
    @Override
    public List<ArticleTypeDO> queryAllArticleType() {
        return articleTypeRepository.findAll();
    }
}
