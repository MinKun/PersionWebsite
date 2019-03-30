package com.tydd.persion.dao.user;

import com.tydd.persion.model.user.AdminUserOperationRecordDo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName AdminUserOperationRecordRepository
 * @Description 管理员操作记录表DAO
 * @Author kun
 * @Date 2019/2/23
 * @Version 1.0
 **/
public interface AdminUserOperationRecordRepository extends JpaRepository<AdminUserOperationRecordDo, Long> {

}