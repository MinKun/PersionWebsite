package com.tydd.persion.controller;

import com.tydd.persion.common.UrlCommon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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

    /**
     * 转向管理员登录界面
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView adminLoginPage() {
        ModelAndView mav = new ModelAndView("adminLogin");
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
        ModelAndView mav = new ModelAndView("adminIndex");

        return mav;
    }
}