package com.tydd.persion.metaspace;

import com.tydd.persion.common.BaseCommon;
import com.tydd.persion.dto.user.AdminUserDTO;
import com.tydd.persion.util.StringUtil;

import java.util.Date;
import java.util.HashMap;

/**
 * @ClassName LoginMetaData
 * @Description 登录元数据
 * @Author kun
 * @Date 2019/3/29
 * @Version 1.0
 **/
public class LoginMetaData {

    private static volatile LoginMetaData loginMetaData = null;

    /** 存储管理员登录信息 */
    private HashMap<String, AdminUserDTO> loginUserMap = null;

    /** 登录校验开关 */
    private boolean loginVerificationSwitch = false;

    private LoginMetaData() {
        this.loginUserMap = new HashMap<>();
    }

    public static LoginMetaData getInstance() {
        if (loginMetaData == null) {
            synchronized (LoginMetaData.class) {
                if (loginMetaData == null) {
                    loginMetaData = new LoginMetaData();
                }
            }
        }
        return loginMetaData;
    }

    /**
     * 判断token凭证是否已经登录
     * @param token
     * @return
     */
    public boolean isLogin(String token) {
        boolean isLogin = false;
        if (!StringUtil.isEmpty(token)) {
            AdminUserDTO adminUserDTO = loginUserMap.get(token);
            if (adminUserDTO != null) {
                Long lastAccessTime = adminUserDTO.getLastAccessTime();
                Long currentTime = new Date().getTime();
                // 当前时间大于等于最后访问时间，并且时间差不超过登录超时时间设置
                if (currentTime >= lastAccessTime && (currentTime - lastAccessTime < BaseCommon.LOGIN_TIMEOUT)) {
                    isLogin = true;
                    // 重置最后访问时间
                    adminUserDTO.setLastAccessTime(currentTime);
                    loginUserMap.put(token, adminUserDTO);
                }
            }
        }
        return isLogin;
    }

    /**
     * 获取登录管理员的信息
     * @param token
     * @return
     */
    public AdminUserDTO getLoginAdminUserInfo(String token) {
        AdminUserDTO adminUserDTO = null;
        if (!StringUtil.isEmpty(token)) {
            adminUserDTO = loginUserMap.get(token);
        }
        return adminUserDTO;
    }

    /**
     * 更新登录管理员信息
     * @param loginUserMap
     */
    public synchronized void updateLoginUserMap(HashMap<String, AdminUserDTO> loginUserMap) {
        this.loginUserMap = loginUserMap;
    }

    /**
     * 添加登录管理员信息
     * @param token
     * @param loginUser
     */
    public synchronized void addLoginUser(String token, AdminUserDTO loginUser) {
        loginUserMap.put(token, loginUser);
    }

    /**
     * 获取登录校验开关
     * @return
     */
    public boolean getLoginVerificationSwitch() {
        return loginMetaData.loginVerificationSwitch;
    }

    /**
     * 设置登录校验开关
     * @param loginVerificationSwitch
     */
    public synchronized void setLoginVerificationSwitch(boolean loginVerificationSwitch) {
        loginMetaData.loginVerificationSwitch = loginVerificationSwitch;
    }

}
