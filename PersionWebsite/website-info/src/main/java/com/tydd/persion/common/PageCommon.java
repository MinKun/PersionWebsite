package com.tydd.persion.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

/**
 * @ClassName PaginationCommon
 * @Description 分页相关公共参数
 * @Author kun
 * @Date 2019/2/16
 * @Version 1.0
 **/
@Service
@PropertySource("classpath:page-common.properties")
public class PageCommon {

    /**
     * 默认分页页码
     */
    @Value("${default.page.number}")
    private Integer defaultPageNumber;

    /**
     * 默认分页大小
     */
    @Value("${default.page.size}")
    private Integer defaultPageSize;

    public Integer getDefaultPageNumber() {
        return defaultPageNumber;
    }

    public void setDefaultPageNumber(Integer defaultPageNumber) {
        this.defaultPageNumber = defaultPageNumber;
    }

    public Integer getDefaultPageSize() {
        return defaultPageSize;
    }

    public void setDefaultPageSize(Integer defaultPageSize) {
        this.defaultPageSize = defaultPageSize;
    }
}