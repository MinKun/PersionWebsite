package com.tydd.persion.controller;

import com.tydd.persion.common.UrlCommon;
import com.tydd.persion.model.article.ArticleTypeDO;
import com.tydd.persion.service.article.IArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @ClassName HtmlController
 * @Description 管理员页面控制Controller
 * @Author kun
 * @Date 2019/4/6
 * @Version 1.0
 **/
@Controller
@RequestMapping(value = "admin")
public class AdminHtmlController {

    private static Logger LOGGER = LoggerFactory.getLogger(AdminHtmlController.class);

    @Autowired
    private UrlCommon urlCommon;

    @Autowired
    private IArticleService articleService;

    /**
     * 转向管理员登录界面
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView adminLoginPage() {
        ModelAndView mav = new ModelAndView("admin/adminLogin");
        mav.addObject("baseUrl", urlCommon.getBaseUrl());
        mav.addObject("staticResourceUrl", urlCommon.getStaticResourceUrl());
        return mav;
    }

    /**
     * 跳转到管理员首页
     * @return
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public ModelAndView adminIndex() {
        ModelAndView mav = new ModelAndView("admin/adminIndex");
        mav.addObject("staticResourceUrl", urlCommon.getStaticResourceUrl());
        return mav;
    }

    @RequestMapping(value = "index_body", method = RequestMethod.GET)
    public ModelAndView adminIndexBody() {
        ModelAndView mav = new ModelAndView("admin/index_body");
        return mav;
    }

    @RequestMapping(value = "page_head", method = RequestMethod.GET)
    public ModelAndView adminPageHead() {
        ModelAndView mav = new ModelAndView("admin/page_head");
        return mav;
    }

    /**
     * 跳转至文章列表
     * @return
     */
    @RequestMapping(value = "article/list", method = RequestMethod.GET)
    public ModelAndView articleList() {
        ModelAndView mav = new ModelAndView("admin/article/articleList");
        mav.addObject("staticResourceUrl", urlCommon.getStaticResourceUrl());

        List<ArticleTypeDO> articleTypeList = articleService.queryAllArticleType();
        if (!articleTypeList.isEmpty()) {
            mav.addObject("articleTypeList", articleTypeList);
        }
        return mav;
    }
}