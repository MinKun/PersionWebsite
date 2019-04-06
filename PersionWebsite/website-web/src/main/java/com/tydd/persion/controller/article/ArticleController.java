package com.tydd.persion.controller.article;

import com.tydd.persion.common.BaseCommon;
import com.tydd.persion.dto.BaseResponseDTO;
import com.tydd.persion.dto.ResponseDataDTO;
import com.tydd.persion.dto.article.ArticleAddDTO;
import com.tydd.persion.dto.article.ArticleDTO;
import com.tydd.persion.dto.article.ArticleQueryDTO;
import com.tydd.persion.dto.article.ArticleUpdateDTO;
import com.tydd.persion.exception.WebInterfaceException;
import com.tydd.persion.model.article.ArticleDo;
import com.tydd.persion.service.article.IArticleService;
import com.tydd.persion.util.ResponseUtil;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ArticleController
 * @Description 文章模块Controller
 * @Author kun
 * @Date 2019/3/30
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "article")
public class ArticleController {

    private static Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private IArticleService articleService;

    /**
     * 查询文章列表
     * @param articleQueryDTO
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public BaseResponseDTO queryArticleList(@Validated ArticleQueryDTO articleQueryDTO, BindingResult result) {
        BaseResponseDTO responseDTO = new BaseResponseDTO();
        try {
            if (!result.hasErrors()) {
                Map<String, Object> queryMap = new HashMap<>();
                new DozerBeanMapper().map(articleQueryDTO, queryMap);

                Page<ArticleDo> articleDoPage = articleService.queryArticleList(queryMap);
                List<ArticleDTO> articleList = new ArrayList<>();
                List<ArticleDo> articleDoList = articleDoPage.getContent();
                ArticleDTO articleDTO = null;
                for (ArticleDo articleDo : articleDoList) {
                    articleDTO = new ArticleDTO();
                    BeanUtils.copyProperties(articleDo, articleDTO);
                    articleList.add(articleDTO);
                }
                ResponseDataDTO responseDataDTO = new ResponseDataDTO();
                responseDataDTO.setDataList(articleList);
                responseDataDTO.setPageNumber(articleDoPage.getNumber());
                responseDataDTO.setPageSize(articleDoPage.getSize());
                responseDataDTO.setTotalSize(articleDoPage.getTotalElements());
                responseDataDTO.setTotalPage(articleDoPage.getTotalPages());
                responseDTO.setResp(responseDataDTO);
                responseDTO.setCode(BaseCommon.RESPONSE_STATUS_SUCCESS);
                responseDTO.setMessage("查询成功");
            } else {
                responseDTO = ResponseUtil.buildWrongRequestResponse(result);
            }
        } catch (WebInterfaceException ex) {
            String errorMessage = "查询文章列表异常";
            responseDTO = ResponseUtil.buildErrorResponse(errorMessage);
            LOGGER.error(errorMessage, ex);
        }
        return responseDTO;
    }

    /**
     * 查询文章详细信息
     * @param articleId
     * @return
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public BaseResponseDTO queryArticleView(@PathVariable("id") Long articleId) {
        BaseResponseDTO responseDTO = null;
        try {
            ArticleDTO articleDTO = articleService.queryArticleById(articleId);
            responseDTO = ResponseUtil.buildSuccessResponse("查询文章详细成功");
            responseDTO.setData(articleDTO);
        } catch (WebInterfaceException ex) {
            String errorMessage = "查询文章详细异常";
            responseDTO = ResponseUtil.buildErrorResponse(errorMessage);
            LOGGER.error(errorMessage, ex);
        }
        return responseDTO;
    }

    /**
     * 更新文章信息
     * @param articleUpdateDTO
     * @param result
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    public BaseResponseDTO updateArticle(@Validated @RequestBody ArticleUpdateDTO articleUpdateDTO, BindingResult result) {
        BaseResponseDTO responseDTO = null;
        try {
            if (!result.hasErrors()) {
                ArticleDTO articleDTO = new ArticleDTO();
                BeanUtils.copyProperties(articleUpdateDTO, articleDTO);
                boolean isSuccess = articleService.updateArticle(articleDTO);
                if (isSuccess) {
                    responseDTO = ResponseUtil.buildSuccessResponse("更新文章成功");
                } else {
                    responseDTO = ResponseUtil.buildFailResponse("更新文章失败");
                }
            } else {
                responseDTO = ResponseUtil.buildWrongRequestResponse(result);
            }
        } catch (WebInterfaceException ex) {
            String errorMessage = "更新文章信息异常";
            responseDTO = ResponseUtil.buildErrorResponse(errorMessage);
            LOGGER.error(errorMessage, ex);
        }
        return responseDTO;
    }

    /**
     * 添加文章
     * @param articleAddDTO
     * @param result
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public BaseResponseDTO addArticle(@Validated @RequestBody ArticleAddDTO articleAddDTO, BindingResult result, HttpServletRequest request) {
        BaseResponseDTO responseDTO = null;
        try {
            if (!result.hasErrors()) {
                ArticleDTO articleDTO = new ArticleDTO();
                BeanUtils.copyProperties(articleAddDTO, articleDTO);
                boolean isSuccess = articleService.addArticle(articleDTO, request);
                if (isSuccess) {
                    responseDTO = ResponseUtil.buildSuccessResponse("添加文章成功");
                } else {
                    responseDTO = ResponseUtil.buildFailResponse("添加文章失败");
                }
            } else {
                responseDTO = ResponseUtil.buildWrongRequestResponse(result);
            }
        } catch (Exception ex) {
            String errorMessage = "添加文章信息异常";
            responseDTO = ResponseUtil.buildErrorResponse(errorMessage);
            LOGGER.error(errorMessage, ex);
        }
        return responseDTO;
    }
}
