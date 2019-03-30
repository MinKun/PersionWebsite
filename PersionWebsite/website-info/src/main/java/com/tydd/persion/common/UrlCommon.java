package com.tydd.persion.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

/**
 * @ClassName UrlCommon
 * @Description Url地址相关的公共参数
 * @Author kun
 * @Date 2019/2/16
 * @Version 1.0
 **/
@Service
@PropertySource("classpath:url-common.properties")
public class UrlCommon {

    /**
     * 基础URL前缀
     */
    @Value("${base.url}")
    private String baseUrl;

    /**
     * 资源地址URL前缀
     */
    @Value("${static.resource.url}")
    private String staticResourceUrl;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getStaticResourceUrl() {
        return staticResourceUrl;
    }

    public void setStaticResourceUrl(String staticResourceUrl) {
        this.staticResourceUrl = staticResourceUrl;
    }
}