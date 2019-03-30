package com.tydd.persion.common;

/**
 * @ClassName RedisCommon
 * @Description Redis相关公共属性
 * @Author kun
 * @Date 2019/2/23
 * @Version 1.0
 **/
public class RedisCommon {

    /** 管理员登录凭证key前缀 */
    public final static String ADMIN_USER_LOGIN_TOKEN_KEY_PREFIX = "ADMIN_USER_";

    /** 管理员登录超时时间 */
    public final static int ADMIN_USER_LOGIN_TIMEOUT = 30;
}