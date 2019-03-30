package com.tydd.persion.interceptor;

import com.tydd.persion.dto.user.AdminUserDTO;
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

    private List<String> notInterceptorPath = null;

    public SessionInterceptor(){}

    public SessionInterceptor(List<String> notInterceptorPath) {
        this.notInterceptorPath = notInterceptorPath;
    }

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Resource(name = "redisTemplate")
    private ValueOperations<Object, Object> valOps;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 默认不通过
        boolean returnMarking = false;
        if (judgeWhetherToIntercept(request.getRequestURI())) {
            // 验证请求cook中是否带有正确的登录token标示
            Cookie[] cookies = request.getCookies();
            if (cookies != null && cookies.length > 0) {
                for (Cookie cookie : cookies) {
                    if ("loginToken".equals(cookie.getName())) {
                        String loginToken = cookie.getValue();
                        AdminUserDTO administrator = (AdminUserDTO) valOps.get(loginToken);
                        LOGGER.info("用户缓存中的登录信息:" + administrator);
                        if (administrator != null) {
                            // 用户最后访问时间
                            Long lastAccessTime = administrator.getLastAccessTime();
                            String loginIp = administrator.getLoginIp();
                            // 当前时间
                            Long currentTime = TimeUtil.getTimeStamp();
                            if (lastAccessTime != null && !StringUtil.isEmpty(loginIp)) {
                                // 时间差
                                Long timeDifference = currentTime - lastAccessTime;
                                LOGGER.info("请求url = "
                                        + request.getRequestURI()
                                        + "，最后访问时间 = "
                                        + TimeUtil.stampToDate(lastAccessTime)
                                        + ", 当前时间 = "
                                        + TimeUtil.stampToDate(currentTime)
                                        + "，时间差 = "
                                        + timeDifference);
                                // 请求ip地址
                                String requestIp = HttpUtil.getRequestIp(request);
                                LOGGER.info("请求IP = " + requestIp + "，登录IP = " + loginIp);
                                // 验证登录时效和请求ip地址和登录时是否一致
                                if (currentTime > lastAccessTime && timeDifference <= 30 && loginIp.equals(requestIp)) {
                                    // 重新设置登录信息的时效（30分钟）
                                    administrator.setLastAccessTime(currentTime);
                                    valOps.set(loginToken, administrator, 30, TimeUnit.MINUTES);
                                    returnMarking = true;
                                    break;
                                }
                            }
                        }
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
            int length = notInterceptorPath.size();
            for (int i = 0; i < length; i++) {
                if (notInterceptorPath.get(i).equals(requestUrl)) {
                    // 请求url不需要拦截验证，设置返回标示，跳出检查循环
                    returnMarking = false;
                    break;
                }
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