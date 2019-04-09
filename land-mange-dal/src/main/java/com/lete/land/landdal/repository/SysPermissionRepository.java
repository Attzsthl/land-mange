package com.lete.land.landdal.repository;

import com.lete.land.landdal.entity.SysPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by WJ on 2019/3/28 0028
 */
@Repository
public interface SysPermissionRepository  extends JpaRepository<SysPermission,Integer>, JpaSpecificationExecutor<SysPermission> {

    @Query(value = "SELECT * FROM `sys_role_permission` a left join `sys_permission` b on a.permission_id = b.id WHERE a.role_id = ?1",nativeQuery = true)
    List<SysPermission> selectPermissionsByRoleId(Integer roleId);
}
