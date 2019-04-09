package com.lete.land.landcontroller;

import com.lete.land.landdal.entity.SysPermission;
import com.lete.land.landdal.entity.SysRole;
import com.lete.land.landdal.entity.User;
import com.lete.land.landdal.repository.*;
import com.lete.land.landdal.service.SysPermissionService;
import com.lete.land.landdal.service.SysRoleService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LandControllerApplicationTests {
    @Resource
    private SysRoleService sysRoleService;

    @Resource
    private SysRoleUserRepository SysRoleUserRepository;

    @Resource
    private SysPermissionService sysPermissionService;

    @Resource
    private SysRoleRepository sysRoleRepository;

    @Resource
    private UserRepository userRepository;
    @Test
    public void contextLoads() {

        List<SysRole> sysRoles = sysRoleRepository.selectRoleByUserId(1);
        List<SysPermission> permissionList = sysPermissionService.selectPermByRole(sysRoles);
        User user = userRepository.findByUsername("wujie").get();
        System.out.println(sysRoles);
    }

    @Test
    public void md5Test() {
        String hashAlgorithName = "MD5";
        String password = "123456";
        int hashIterations = 1024;
        ByteSource byteSource = ByteSource.Util.bytes("wujiesalt");
        Object obj = new SimpleHash(hashAlgorithName, password, byteSource, hashIterations);
        System.out.println("加密之后的密码" + obj);
    }

}
