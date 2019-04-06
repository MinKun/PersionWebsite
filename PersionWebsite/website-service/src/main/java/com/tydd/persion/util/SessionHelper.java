package com.tydd.persion.util;

import com.tydd.persion.dto.user.AdminUserDTO;
import com.tydd.persion.metaspace.LoginMetaData;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName SessionHelper
 * @Description Session工具类
 * @Author kun
 * @Date 2019/4/5
 * @Version 1.0
 **/
public class SessionHelper {

    /**
     * 获取登录管理员信息
     * @param request
     * @return
     */
    public static AdminUserDTO getLoginAdminUser(HttpServletRequest request) {
        AdminUserDTO adminUserDTO = null;
        /*Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if ("loginToken".equals(cookie.getName())) {
                    String loginToken = cookie.getValue();
                    adminUserDTO = LoginMetaData.getInstance().getLoginAdminUserInfo(loginToken);
                }
            }
        }*/
        adminUserDTO = SessionHelper.getLoginAdminUserFromHeader(request);
        return adminUserDTO;
    }

    public static AdminUserDTO getLoginAdminUserFromHeader(HttpServletRequest request) {
        String loginToken = request.getHeader("loginToken");
        AdminUserDTO adminUserDTO = LoginMetaData.getInstance().getLoginAdminUserInfo(loginToken);
        return adminUserDTO;
    }

}