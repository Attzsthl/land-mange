package com.lete.land.landdal.repository;

import com.lete.land.landdal.entity.SysRole;
import com.lete.land.landdal.entity.SysRoleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by WJ on 2019/3/28 0028
 */
@Repository
public interface SysRoleUserRepository  extends JpaRepository<SysRoleUser,Integer>, JpaSpecificationExecutor<SysRoleUser> {
    @Query(value = "SELECT b.* FROM `sys_role_user` a left JOIN `sys_role` b ON a.role_id = b.id WHERE a.user_id = 1",nativeQuery = true)
    List<Object[]> selectRoleByUserId(int userId);
}
