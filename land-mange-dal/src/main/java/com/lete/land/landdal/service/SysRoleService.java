package com.lete.land.landdal.service;

import com.lete.land.landdal.entity.SysRole;
import com.lete.land.landdal.repository.SysRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by WJ on 2019/3/28 0028
 */
@Service
public class SysRoleService {
    @Autowired
    private SysRoleRepository sysRoleRepository;

    public List<SysRole> selectRoleByUserId(int userId) {
       return sysRoleRepository.selectRoleByUserId(userId);
    }
}
