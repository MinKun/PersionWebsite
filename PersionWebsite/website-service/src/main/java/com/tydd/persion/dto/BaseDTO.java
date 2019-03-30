package com.tydd.persion.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * @ClassName BaseDTO
 * @Description 基础DTO模型
 * @Author kun
 * @Date 2019/2/23
 * @Version 1.0
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseDTO implements Serializable {

    /** 主键ID */
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}