package com.tydd.persion.controller.user;

import com.tydd.persion.dto.ResponseDTO;
import com.tydd.persion.dto.user.AdminUserDTO;
import com.tydd.persion.exception.WebInterfaceException;
import com.tydd.persion.service.user.UserService;
import com.tydd.persion.status.ResponseStatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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