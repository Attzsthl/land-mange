package com.lete.land.landdal.vo;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by WJ on 2019/3/29 0029
 */
public class LoginInfo implements Serializable {
    private Integer userId;

    private String userName;

    private Set<String> roleList;

    private Set<String> permissionList;

    public LoginInfo() {
    }

    public LoginInfo(Set<String> roleList, Set<String> permissionList) {
        this.roleList = roleList;
        this.permissionList = permissionList;
    }

    public LoginInfo(Integer userId, String userName, Set<String> roleList, Set<String> permissionList) {
        this.userId = userId;
        this.userName = userName;
        this.roleList = roleList;
        this.permissionList = permissionList;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Set<String> getRoleList() {
        return roleList;
    }

    public void setRoleList(Set<String> roleList) {
        this.roleList = roleList;
    }

    public Set<String> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(Set<String> permissionList) {
        this.permissionList = permissionList;
    }
}
