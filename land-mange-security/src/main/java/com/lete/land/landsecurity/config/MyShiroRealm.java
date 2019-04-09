package com.lete.land.landsecurity.config;

import com.lete.land.landdal.entity.SysPermission;
import com.lete.land.landdal.entity.SysRole;
import com.lete.land.landdal.entity.User;
import com.lete.land.landdal.repository.UserRepository;
import com.lete.land.landdal.service.SysPermissionService;
import com.lete.land.landdal.service.SysRoleService;
import com.lete.land.landdal.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by WJ on 2019/3/28 0028
 * 自定义权限匹配和密码匹配
 */
public class MyShiroRealm extends AuthorizingRealm {
    @Resource
    private SysRoleService sysRoleService;

    @Resource
    private UserRepository userRepository;

    @Resource
    private SysPermissionService sysPermissionService;

    @Resource
    private UserService userService;

    @Override
    public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User User = (User) principals.getPrimaryPrincipal();
        try {
            List<SysRole> roles = sysRoleService.selectRoleByUserId(User.getId());
            for (SysRole role : roles) {
                authorizationInfo.addRole(role.getRole());//角色存储
            }
            //此处如果多个角色都拥有某项权限，bu会数据重复，内部用的是Set
            List<SysPermission> sysPermissions = sysPermissionService.selectPermByRole(roles);
            for (SysPermission perm : sysPermissions) {
                authorizationInfo.addStringPermission(perm.getPermission());//权限存储
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authorizationInfo;
    }

    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
        //获取用户的输入的账号.
        String username = (String) token.getPrincipal();
//       System.out.println(token.getCredentials());
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        User user = userRepository.findByUsername(username).get();//*
        if (user == null) {
            return null;
        }

        if (user.getState() == 0) { //账户冻结
            throw new LockedAccountException();
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user, //用户名
                user.getPassword(), //密码
                ByteSource.Util.bytes(user.getCredentialsSalt()),//salt=username+salt
                getName()  //realm name
        );
        return authenticationInfo;
    }
}
