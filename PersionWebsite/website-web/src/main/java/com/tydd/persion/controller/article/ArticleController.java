package com.tydd.persion.controller.article;

import com.tydd.persion.dto.BaseResponseDTO;
import com.tydd.persion.dto.article.ArticleQueryDTO;
import com.tydd.persion.service.article.IArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public BaseResponseDTO queryArticleList(@Validated ArticleQueryDTO articleQueryDTO, BindingResult result) {
        BaseResponseDTO responseDTO = new BaseResponseDTO();
        try {
            if (!result.hasErrors()) {
                Map<String, Object> queryMap = new HashMap<>();
                responseDTO = articleService.queryArticleList(queryMap);
                responseDTO.setCode("1");
                responseDTO.setMessage("查询成功");
            }
        } catch (Exception ex) {
            LOGGER.error("查询文章列表异常", ex);
            responseDTO.setCode("0");
            responseDTO.setMessage("查询文章列表异常");
        }
        return responseDTO;
    }
}
