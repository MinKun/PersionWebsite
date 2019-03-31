package com.tydd.persion.interceptor;

import com.tydd.persion.dto.user.AdminUserDTO;
import com.tydd.persion.metaspace.LoginMetaData;
import com.tydd.persion.util.HttpUtil;
import com.tydd.persion.util.StringUtil;
import com.tydd.persion.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName SessionInterceptor
 * @Description Session验证拦截器
 * @Author kun
 * @Date 2019/3/3
 * @Version 1.0
 **/
public class SessionInterceptor implements HandlerInterceptor {

    private static Logger LOGGER = LoggerFactory.getLogger(SessionInterceptor.class);

    private List<String> notInterceptorPath = new ArrayList<>();

    private boolean sessionSwitch = false;

    public SessionInterceptor(){}

    public SessionInterceptor(List<String> notInterceptorPath) {
        this.notInterceptorPath = notInterceptorPath;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 默认不通过
        boolean returnMarking = false;
        if (judgeWhetherToIntercept(request.getRequestURI()) && sessionSwitch) {
            // 验证请求cook中是否带有正确的登录token标示
            Cookie[] cookies = request.getCookies();
            if (cookies != null && cookies.length > 0) {
                for (Cookie cookie : cookies) {
                    if ("loginToken".equals(cookie.getName())) {
                        String loginToken = cookie.getValue();
                        returnMarking = LoginMetaData.getInstance().isLogin(loginToken);
                    }
                }
            }
        } else {
            // 不需要拦截放过
            returnMarking = true;
        }
        return returnMarking;
    }

    /**
     * 判断请求是否需要被拦截验证
     * @param requestUrl
     * @return true : 需要拦截验证  false : 不需要拦截验证
     */
    private boolean judgeWhetherToIntercept(String requestUrl) {
        // 默认拦截
        boolean returnMarking = true;
        if (!StringUtil.isEmpty(requestUrl)) {
            if (notInterceptorPath.contains(requestUrl)) {
                returnMarking = false;
            }
        }
        return returnMarking;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}