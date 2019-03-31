package com.tydd.persion.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @ClassName 分页参数DTO
 * @Author kun
 * @Date 2019/3/31
 * @Version 1.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageDTO {

    @Min(value = 1, message = "页码不能小于1")
    private Integer pageNumber;

    @Min(value = 1, message = "每页数量不能小于1")
    @Max(value = 100, message = "每页数量不能大于100")
    private Integer pageSize;

    private String order;

    @Override
    public String toString() {
        return "PageDTO{" +
                "pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                ", order='" + order + '\'' +
                '}';
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
