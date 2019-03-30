package com.tydd.persion.model.user;

import javax.persistence.*;
import java.util.List;

/**
 * @ClassName AdminUserDo
 * @Description 管理员用户表
 * @Author kun
 * @Date 2019/2/16
 * @Version 1.0
 **/
@Entity
@Table(name = "t_admin_user", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"login_name"})
})
public class AdminUserDo {

    @Id
    @GeneratedValue
    private Long id;

    /** 登录名 */
    @Column(name = "login_name", nullable = false, columnDefinition = "varchar(20) comment '登录名'")
    private String loginName;

    /** 登录密码 */
    @Column(name = "login_password", nullable = false, columnDefinition = "varchar(40) comment '登录密码'")
    private String loginPassword;

    /** 昵称 */
    @Column(name = "nickname", nullable = false, columnDefinition = "varchar(40) comment '昵称'")
    private String nickName;

    /** 操作记录列表 */
    @OneToMany(mappedBy = "operateUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AdminUserOperationRecordDo> operationRecords;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public List<AdminUserOperationRecordDo> getOperationRecords() {
        return operationRecords;
    }

    public void setOperationRecords(List<AdminUserOperationRecordDo> operationRecords) {
        this.operationRecords = operationRecords;
    }
}