package com.tydd.persion.model.user;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName AdminUserRecordDo
 * @Description 管理员操作记录表
 * @Author kun
 * @Date 2019/2/23
 * @Version 1.0
 **/
@Entity
@Table(name = "t_admin_user_operation_record")
public class AdminUserOperationRecordDo {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "operate_type", length = 1, nullable = false, columnDefinition = "char(1) comment '操作类型'")
    private String operateType;

    @Column(name = "operate_time", nullable = false, columnDefinition = "datetime comment '操作时间'")
    private Date operateTime;

    @Column(name = "operate_remark", columnDefinition = "varchar(100) comment '操作备注'")
    private String operateRemark;

    @ManyToOne(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_user_id", columnDefinition = "bigint(20) comment '操作人id'")
    private AdminUserDo operateUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public AdminUserDo getOperateUser() {
        return operateUser;
    }

    public void setOperateUser(AdminUserDo operateUser) {
        this.operateUser = operateUser;
    }

    public String getOperateRemark() {
        return operateRemark;
    }

    public void setOperateRemark(String operateRemark) {
        this.operateRemark = operateRemark;
    }
}