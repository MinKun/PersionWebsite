package com.tydd.persion.status;

/**
 * @ClassName ResponseStatusEnum
 * @Description 接口返回状态
 * @Author kun
 * @Date 2019/2/16
 * @Version 1.0
 **/
public enum ResponseStatusEnum {

    SUCCESS("1", "接口调用成功"),

    FAIL("-1", "接口调用异常");

    ResponseStatusEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private String code;

    private String message;

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