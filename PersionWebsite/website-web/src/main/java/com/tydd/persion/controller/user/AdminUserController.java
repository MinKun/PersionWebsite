package com.tydd.persion.controller.user;

import com.tydd.persion.dto.ResponseDTO;
import com.tydd.persion.dto.user.AdminUserDTO;
import com.tydd.persion.exception.WebInterfaceException;
import com.tydd.persion.service.user.UserService;
import com.tydd.persion.status.ResponseStatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName AdminUserController
 * @Description TODO
 * @Author kun
 * @Date 2019/2/23
 * @Version 1.0
 **/
@Controller
@RequestMapping(value = "/user/admin")
public class AdminUserController {

    private static Logger LOGGER = LoggerFactory.getLogger(AdminUserController.class);

    @Autowired
    private UserService userService;

    /**
     * 管理员账户登录
     * @param loginName
     * @param loginPassword
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseDTO adminUserLogin(@RequestParam String loginName, @RequestParam String loginPassword) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            AdminUserDTO adminUserDTO = userService.loginAuthentication(loginName, loginPassword);
            if (adminUserDTO != null) {
                String token = userService.createLoginToken(adminUserDTO);
                adminUserDTO.setLoginToken(token);
                responseDTO.setData(adminUserDTO);
                responseDTO.setCode(ResponseStatusEnum.SUCCESS.getCode());
                responseDTO.setMessage("登录成功");
            } else {
                responseDTO.setCode(ResponseStatusEnum.FAIL.getCode());
                responseDTO.setMessage("登录失败");
            }
        } catch (WebInterfaceException ex) {
            responseDTO.setCode(ResponseStatusEnum.FAIL.getCode());
            responseDTO.setMessage("登录异常");
            LOGGER.error("登录异常", ex);
        }
        return responseDTO;
    }
}