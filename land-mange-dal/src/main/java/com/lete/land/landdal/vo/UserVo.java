package com.lete.land.landdal.vo;

/**
 * Created by WJ on 2019/3/28 0028
 */
public class UserVo {
    private String username;

    private String id;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserVo(String username) {
        this.username = username;
    }
}
