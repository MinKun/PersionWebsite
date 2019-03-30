package com.tydd.persion.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tydd.persion.dto.BaseDTO;

/**
 * @ClassName AdminUserOperationRecordDTO
 * @Description 管理员用户操作记录DTO
 * @Author kun
 * @Date 2019/2/23
 * @Version 1.0
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdminUserOperationRecordDTO extends BaseDTO {

    /** 操作类型 */
    private String operateType;

    /** 操作时间 */
    private String operateTime;

    /** 操作人ID */
    private Long operateUserId;

    /** 操作人名称 */
    private String operateUserName;

    /** 操作备注 */
    private String operateRemark;


    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }

    public Long getOperateUserId() {
        return operateUserId;
    }

    public void setOperateUserId(Long operateUserId) {
        this.operateUserId = operateUserId;
    }

    public String getOperateUserName() {
        return operateUserName;
    }

    public void setOperateUserName(String operateUserName) {
        this.operateUserName = operateUserName;
    }

    public String getOperateRemark() {
        return operateRemark;
    }

    public void setOperateRemark(String operateRemark) {
        this.operateRemark = operateRemark;
    }
}