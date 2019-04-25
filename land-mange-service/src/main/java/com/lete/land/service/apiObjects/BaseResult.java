package com.lete.land.service.apiObjects;

import java.io.Serializable;

/**
 * Created by WJ on 2019/4/19 0019
 */
public class BaseResult<E> implements Serializable {
    private static final long serialVersionUID = 1L;

    private String code;

    private String msg;

    private E data;

    public BaseResult() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
