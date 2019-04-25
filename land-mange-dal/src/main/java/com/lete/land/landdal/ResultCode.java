package com.lete.land.landdal;

/**
 * Created by WJ on 2019/3/26 0026
 */
public enum ResultCode {
    /**
     * 成功
     */
    SUCCESS(0),

    LOGINSUCESS(0),
    /**
     * 失败
     */
    FAIL(400),
    /**
     * 未认证（签名错误）
     */
    UNAUTHORIZED(401),

    /**
     * 接口不存在
     */
    NOT_FOUND(404),

    /**
     * 服务器内部错误
     */
    INTERNAL_SERVER_ERROR(500);

    public int code;

    ResultCode(int code) {
        this.code = code;
    }
}
