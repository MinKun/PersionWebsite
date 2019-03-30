package com.tydd.persion.metaspace;

import com.tydd.persion.dto.user.AdminUserDTO;
import com.tydd.persion.util.StringUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName LoginMetaData
 * @Description 登录元数据
 * @Author kun
 * @Date 2019/3/29
 * @Version 1.0
 **/
@Service
public class LoginMetaData {

    /** 存储管理员登录信息 */
    private HashMap<String, AdminUserDTO> loginUserMap = null;

    private ReentrantReadWriteLock reentrantReadWriteLock = null;

    private ReentrantReadWriteLock.ReadLock readLock = null;

    private ReentrantReadWriteLock.WriteLock writeLock = null;

    public LoginMetaData() {
        this.loginUserMap = new HashMap<>();
        reentrantReadWriteLock = new ReentrantReadWriteLock();
        readLock = reentrantReadWriteLock.readLock();
        writeLock = reentrantReadWriteLock.writeLock();
    }

    /**
     * 判断token凭证是否已经登录
     * @param token
     * @return
     */
    public boolean isLogin(String token) {
        readLock.lock();
        boolean isLogin = false;
        if (!StringUtil.isEmpty(token)) {
            isLogin = loginUserMap.containsKey(token);
        }
        readLock.unlock();
        return isLogin;
    }

    /**
     * 获取登录管理员的信息
     * @param token
     * @return
     */
    public AdminUserDTO getLoginAdminUserInfo(String token) {
        readLock.lock();
        AdminUserDTO adminUserDTO = null;
        if (!StringUtil.isEmpty(token)) {
            adminUserDTO = loginUserMap.get(token);
        }
        readLock.unlock();
        return adminUserDTO;
    }

    /**
     * 更新登录管理员信息
     * @param loginUserMap
     */
    public void updateLoginUserMap(HashMap<String, AdminUserDTO> loginUserMap) {
        writeLock.lock();
        this.loginUserMap = loginUserMap;
        writeLock.unlock();
    }

    /**
     * 添加登录管理员信息
     * @param token
     * @param loginUser
     */
    public void addLoginUser(String token, AdminUserDTO loginUser) {
        writeLock.lock();
        loginUserMap.put(token, loginUser);
        writeLock.unlock();
    }

}
