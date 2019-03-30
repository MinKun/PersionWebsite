package com.tydd.persion.service.user;

import com.tydd.persion.dto.user.AdminUserDTO;
import com.tydd.persion.model.user.AdminUserDo;

/**
 * @ClassName UserService
 * @Description 用户模块Service接口
 * @Author kun
 * @Date 2019/2/16
 * @Version 1.0
 **/
public interface UserService {

    /**
     * 管理员登录验证
     * @param loginName
     * @param loginPassword
     * @return
     */
    AdminUserDTO loginAuthentication(String loginName, String loginPassword);

    /**
     * 创建管理员用户登录凭证
     * @param adminUserDTO
     * @return
     */
    String createLoginToken(AdminUserDTO adminUserDTO);

    /**
     * 添加管理员用户操作记录
     * @param adminUserDo
     * @param operationType
     */
    void addAdminUserOperationRecord(AdminUserDo adminUserDo, String operationType);

}