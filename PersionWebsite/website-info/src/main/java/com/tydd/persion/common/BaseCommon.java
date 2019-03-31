package com.tydd.persion.common;

/**
 * @ClassName BaseCommon
 * @Description 基础公共属性
 * @Author kun
 * @Date 2019/2/23
 * @Version 1.0
 **/
public class BaseCommon {

    /** 管理员用户操作类型-登录 */
    public final static String ADMIN_USER_OPERATION_TYPE_LOGIN = "1";

    /** 登录超时时间（单位分钟） */
    public final static Integer LOGIN_TIMEOUT = 30;

    /** 返回状态-成功 */
    public final static String RESPONSE_STATUS_SUCCESS = "1";

    /** 返回状态-失败 */
    public final static String RESPONSE_STATUS_FAIL = "2";

    /** 返回状态-异常 */
    public final static String RESPONSE_STATUS_ERROR = "3";

    /** 返回状态-拒绝 */
    public final static String RESPONSE_STATUS_REJECT = "4";
}