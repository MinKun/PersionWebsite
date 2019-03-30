package com.tydd.persion.dto.user;

import com.tydd.persion.dto.BaseDTO;

import java.util.List;

/**
 * @ClassName AdminUserDTO
 * @Description 管理员用户DTO
 * @Author kun
 * @Date 2019/2/23
 * @Version 1.0
 **/
public class AdminUserDTO extends BaseDTO {

    /** 登录名 */
    private String loginName;

    /** 昵称 */
    private String nickName;

    /** 登录凭证 */
    private String loginToken;

    /** 最后访问时间 */
    private Long lastAccessTime;

    /** 访问ip */
    private String loginIp;

    /** 操作记录 */
    private List<AdminUserOperationRecordDTO> operationRecords;

    @Override
    public String toString() {
        return "AdminUserDTO{" +
                "loginName='" + loginName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", loginToken='" + loginToken + '\'' +
                ", lastAccessTime=" + lastAccessTime +
                ", loginIp='" + loginIp + '\'' +
                ", operationRecords=" + operationRecords +
                '}';
    }

    public Long getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(Long lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public List<AdminUserOperationRecordDTO> getOperationRecords() {
        return operationRecords;
    }

    public void setOperationRecords(List<AdminUserOperationRecordDTO> operationRecords) {
        this.operationRecords = operationRecords;
    }
}