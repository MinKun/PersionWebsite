package com.tydd.persion.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * @ClassName ResponseDataDTO
 * @Description 返回数据对象DTO
 * @Author kun
 * @Date 2019/3/31
 * @Version 1.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDataDTO {

    private BaseDTO data;

    private List<? extends BaseDTO> dataList;

    private Integer totalPage;

    private Long totalSize;

    private Integer pageNumber;

    private Integer pageSize;

    @Override
    public String toString() {
        return "ResponseDataDTO{" +
                "data=" + data +
                ", dataList=" + dataList +
                ", totalPage=" + totalPage +
                ", totalSize=" + totalSize +
                ", pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                '}';
    }

    public BaseDTO getData() {
        return data;
    }

    public void setData(BaseDTO data) {
        this.data = data;
    }

    public List<? extends BaseDTO> getDataList() {
        return dataList;
    }

    public void setDataList(List<? extends BaseDTO> dataList) {
        this.dataList = dataList;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Long totalSize) {
        this.totalSize = totalSize;
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
}
