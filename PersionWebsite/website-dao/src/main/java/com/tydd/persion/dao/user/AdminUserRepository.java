package com.tydd.persion.dao.user;

import com.tydd.persion.model.user.AdminUserDo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName AdminUserRepository
 * @Description 管理员用户表DAO
 * @Author kun
 * @Date 2019/2/23
 * @Version 1.0
 **/
public interface AdminUserRepository extends JpaRepository<AdminUserDo, Long> {

    /**
     * 查询管理员用户
     * @param loginName
     * @param loginPassword
     * @return
     */
    AdminUserDo findByLoginNameAndLoginPassword(String loginName, String loginPassword);

}