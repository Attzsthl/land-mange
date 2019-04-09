package com.lete.land.landdal.entity;

import javax.persistence.*;

/**
 * Created by WJ on 2019/3/28 0028
 */
@Entity(name = "sys_role")
@Table
public class SysRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "role")
    private String role;

    @Column(name = "description")
    private String description;

    @Column(name = "available")
    private String available;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }
}
