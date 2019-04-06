package com.tydd.persion.service.user.impl;

import com.tydd.persion.common.BaseCommon;
import com.tydd.persion.common.RedisCommon;
import com.tydd.persion.dao.user.AdminUserOperationRecordRepository;
import com.tydd.persion.dao.user.AdminUserRepository;
import com.tydd.persion.dto.user.AdminUserDTO;
import com.tydd.persion.metaspace.LoginMetaData;
import com.tydd.persion.model.user.AdminUserDo;
import com.tydd.persion.model.user.AdminUserOperationRecordDo;
import com.tydd.persion.service.user.UserService;
import com.tydd.persion.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

/**
 * @ClassName UserServiceImpl
 * @Description 用户模块Service实现
 * @Author kun
 * @Date 2019/2/16
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements UserService {

    private Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Autowired
    private AdminUserOperationRecordRepository adminUserOperationRecordRepository;

    /**
     * 管理员登录验证
     *
     * @param loginName
     * @param loginPassword
     * @return
     */
    @Override
    public AdminUserDTO loginAuthentication(String loginName, String loginPassword) {
        AdminUserDTO adminUserDTO = null;
        if (!StringUtil.isEmpty(loginName) && !StringUtil.isEmpty(loginPassword)) {
            AdminUserDo adminUserDo = adminUserRepository.findByLoginNameAndLoginPassword(loginName, loginPassword);
            if (adminUserDo != null) {
                adminUserDTO = new AdminUserDTO();
                adminUserDTO.setLoginName(adminUserDo.getLoginName());
                adminUserDTO.setNickName(adminUserDo.getNickName());
                adminUserDTO.setId(adminUserDo.getId());
                // 添加操作记录
                this.addAdminUserOperationRecord(adminUserDo, BaseCommon.ADMIN_USER_OPERATION_TYPE_LOGIN);
            }
        } else {
            LOGGER.warn("loginAuthentication() 方法入参不正确，loginName或loginPassword为空。");
        }
        return adminUserDTO;
    }

    /**
     * 创建管理员用户登录凭证
     *
     * @param adminUserDTO
     * @return
     */
    @Override
    public String createLoginToken(AdminUserDTO adminUserDTO) {
        String token = null;
        if (adminUserDTO != null) {
            token = UUID.randomUUID().toString();
        }
        return token;
    }

    /**
     * 添加管理员用户操作记录
     *
     * @param adminUserDo
     * @param operationType
     * @return
     */
    @Override
    public void addAdminUserOperationRecord(AdminUserDo adminUserDo, String operationType) {
        AdminUserOperationRecordDo adminUserOperationRecordDo = new AdminUserOperationRecordDo();
        adminUserOperationRecordDo.setOperateUser(adminUserDo);
        adminUserOperationRecordDo.setOperateType(operationType);
        adminUserOperationRecordDo.setOperateTime(new Date());
        adminUserOperationRecordDo.setOperateRemark("登录操作");
        adminUserOperationRecordRepository.save(adminUserOperationRecordDo);
    }
}