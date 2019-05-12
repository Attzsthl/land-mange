package com.lete.land.landdal.service;

import com.lete.land.landdal.entity.SysPermission;
import com.lete.land.landdal.entity.SysRole;
import com.lete.land.landdal.entity.User;
import com.lete.land.landdal.repository.UserRepository;
import com.lete.land.landdal.vo.LoginInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by WJ on 2019/3/29 0029
 */
@Service
public class LoginService {
    @Resource
    private SysRoleService sysRoleService;

    @Resource
    private UserRepository userRepository;

    @Resource
    private SysPermissionService sysPermissionService;

    public LoginInfo getLoginInfo(String username) {
        User user = userRepository.findByUsername(username).get();

        List<SysRole> roles = sysRoleService.selectRoleByUserId(user.getId());

        Set<String> roleList = new HashSet<>();
        Set<String> permissionList = new HashSet<>();
        for (SysRole role : roles) {
            roleList.add(role.getRole());//角色存储
        }
        //此处如果多个角色都拥有某项权限，bu会数据重复，内部用的是Set
        List<SysPermission> sysPermissions = sysPermissionService.selectPermByRole(roles);
        for (SysPermission perm : sysPermissions) {
            permissionList.add(perm.getPermission());//权限存储
        }
        LoginInfo loginInfo = new LoginInfo(user.getId(),user.getUsername(),roleList,permissionList);
        loginInfo.setToken(String.valueOf(user.getId()));
        loginInfo.setUuid(user.getId());
        loginInfo.setTownId(user.getTownId());

        return loginInfo;
    }
}
