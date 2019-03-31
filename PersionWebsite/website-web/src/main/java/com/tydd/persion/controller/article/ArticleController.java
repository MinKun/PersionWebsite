package com.tydd.persion.controller.article;

import com.tydd.persion.common.BaseCommon;
import com.tydd.persion.dto.BaseResponseDTO;
import com.tydd.persion.dto.ResponseDataDTO;
import com.tydd.persion.dto.article.ArticleDTO;
import com.tydd.persion.dto.article.ArticleQueryDTO;
import com.tydd.persion.model.article.ArticleDo;
import com.tydd.persion.service.article.IArticleService;
import com.tydd.persion.util.ResponseUtil;
import com.tydd.persion.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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
@Controller
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
    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponseDTO queryArticleList(@Valid ArticleQueryDTO articleQueryDTO, BindingResult result) {
        BaseResponseDTO responseDTO = new BaseResponseDTO();
        try {
            if (!result.hasErrors()) {
                Map<String, Object> queryMap = new HashMap<>();
                String articleTitle = articleQueryDTO.getArticleTitle();
                if (!StringUtil.isEmpty(articleTitle)) {
                    queryMap.put("articleTitle", articleQueryDTO.getArticleTitle());
                }
                Integer articleType = articleQueryDTO.getArticleType();
                if (articleType != null) {
                    queryMap.put("articleType", articleType);
                }
                Integer articleStatus = articleQueryDTO.getArticleStatus();
                if (articleStatus != null) {
                    queryMap.put("articleStatus", articleStatus);
                }

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
        } catch (Exception ex) {
            String errorMessage = "查询文章列表异常";
            responseDTO = ResponseUtil.buildErrorResponse(errorMessage);
            LOGGER.error(errorMessage, ex);
        }
        return responseDTO;
    }
}
