package com.tydd.persion.controller.user;

import com.tydd.persion.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName UserController
 * @Description 用户模块控制层
 * @Author kun
 * @Date 2019/2/16
 * @Version 1.0
 **/
@Controller
@RequestMapping(value = "user")
public class UserController {

    private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;


}