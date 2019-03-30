package com.tydd.persion.util;

/**
 * @ClassName StringUtil
 * @Description 字符串工具类
 * @Author kun
 * @Date 2019/2/16
 * @Version 1.0
 **/
public class StringUtil {

    /**
     * 判断字符串是否为空或者长度为0
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        boolean isEmpty = false;
        if (str == null || str.length() == 0) {
            isEmpty = true;
        }
        return isEmpty;
    }

}