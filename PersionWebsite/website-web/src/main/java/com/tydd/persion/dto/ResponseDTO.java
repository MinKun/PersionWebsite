package com.tydd.persion.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName ResponseDTO
 * @Description 接口返回对象DTO
 * @Author kun
 * @Date 2019/2/23
 * @Version 1.0
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO implements Serializable {

    /** 返回码 */
    private String code;

    /** 返回说明 */
    private String message;

    /** 返回数据 */
    private BaseDTO data;

    /** 返回数据列表 */
    private List<BaseDTO> dataList;

    @Override
    public String toString() {
        return "ResponseDTO{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", dataList=" + dataList +
                '}';
    }

    public BaseDTO getData() {
        return data;
    }

    public void setData(BaseDTO data) {
        this.data = data;
    }

    public List<BaseDTO> getDataList() {
        return dataList;
    }

    public void setDataList(List<BaseDTO> dataList) {
        this.dataList = dataList;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}