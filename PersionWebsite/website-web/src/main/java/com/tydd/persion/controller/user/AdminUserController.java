package com.tydd.persion.controller.user;

import com.tydd.persion.dto.BaseResponseDTO;
import com.tydd.persion.dto.user.AdminUserDTO;
import com.tydd.persion.exception.WebInterfaceException;
import com.tydd.persion.metaspace.LoginMetaData;
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
 * @Description 管理员用户Controller
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
    public BaseResponseDTO adminUserLogin(@RequestParam String loginName, @RequestParam String loginPassword) {
        BaseResponseDTO responseDTO = new BaseResponseDTO();
        try {
            // 校验登录是否通过
            AdminUserDTO adminUserDTO = userService.loginAuthentication(loginName, loginPassword);
            if (adminUserDTO != null) {
                // 获取登录凭证
                String token = userService.createLoginToken(adminUserDTO);
                // 设置登录凭证记录
                LoginMetaData.getInstance().addLoginUser(token, adminUserDTO);
                // 设置返回信息
                adminUserDTO.setLoginToken(token);
                responseDTO.setData(adminUserDTO);
                responseDTO.setCode(ResponseStatusEnum.SUCCESS.getCode());
                responseDTO.setMessage("登录成功");
                LOGGER.info("管理员用户：" + loginName + "登录系统，登录凭证：" + token + "。");
            } else {
                responseDTO.setCode(ResponseStatusEnum.FAIL.getCode());
                responseDTO.setMessage("登录失败");
                LOGGER.info("管理员用户：" + loginName + "登录校验失败。");
            }
        } catch (WebInterfaceException ex) {
            responseDTO.setCode(ResponseStatusEnum.FAIL.getCode());
            responseDTO.setMessage("登录异常");
            LOGGER.error("登录异常", ex);
        }
        return responseDTO;
    }
}