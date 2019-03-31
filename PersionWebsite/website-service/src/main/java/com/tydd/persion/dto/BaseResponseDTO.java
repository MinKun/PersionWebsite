package com.tydd.persion.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @ClassName BaseResponseDTO
 * @Description 基础返回信息DTO
 * @Author kun
 * @Date 2019/3/31
 * @Version 1.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponseDTO {

    /** 返回码 */
    private String code;

    /** 返回说明 */
    private String message;

    /** 返回数据 */
    private ResponseDataDTO resp;

    @Override
    public String toString() {
        return "BaseResponseDTO{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", resp=" + resp +
                '}';
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

    public ResponseDataDTO getResp() {
        return resp;
    }

    public void setResp(ResponseDataDTO resp) {
        this.resp = resp;
    }

    public void setData(BaseDTO baseDTO) {
        if (this.resp == null) {
            this.resp = new ResponseDataDTO();
        }
        this.resp.setData(baseDTO);
    }
}
