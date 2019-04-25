package com.lete.land.landdal;

import com.lete.land.landdal.vo.LoginInfo;

/**
 * Created by WJ on 2019/3/26 0026
 */
public class Result {
    /**
     * 响应状态码
     */
    private int code;
    /**
     * 响应提示信息
     */
    private String message;
    /**
     * 响应结果对象
     */
    private LoginInfo data;

    public Result(int code, String message, LoginInfo loginInfo) {
        this.code = code;
        this.message = message;
        this.data = loginInfo;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LoginInfo getData() {
        return data;
    }

    public void setData(LoginInfo data) {
        this.data = data;
    }
}
