package com.lete.land.landdal.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by WJ on 2019/3/28 0028
 */
@Entity(name = "sys_role_user")
@Table
public class SysRoleUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

   private Integer roleId;

   private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
