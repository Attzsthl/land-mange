package com.lete.land.landdal.service;

import com.lete.land.landdal.entity.SysPermission;
import com.lete.land.landdal.entity.SysRole;
import com.lete.land.landdal.repository.SysPermissionRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WJ on 2019/3/28 0028
 */
@Service
public class SysPermissionService {
    @Resource
    private SysPermissionRepository sysPermissionRepository;

    public List<SysPermission> selectPermByRole(List<SysRole> roles) {
        List<SysPermission> permissionList = new ArrayList<>();

        roles.forEach(item -> {
            permissionList.addAll(sysPermissionRepository.selectPermissionsByRoleId(item.getId()));
        });

        return permissionList;
    }
}
