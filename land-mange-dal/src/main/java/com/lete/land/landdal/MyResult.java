package com.lete.land.landdal;


/**
 * Created by WJ on 2019/3/27 0027
 */
public class MyResult {
    /**
     * 响应状态码
     */
    private int code = 0;
    /**
     * 响应提示信息
     */
    private String message = "返回成功";
    /**
     * 响应结果对象
     */
    private Data data = new Data();

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
