package com.lete.land.landdal.service;


import com.lete.land.landdal.MyResult;

/**
 * Created by WJ on 2019/3/27 0027
 */
public class FirstService {
    MyResult myResult = new MyResult();

    public MyResult getMyResult() {
        myResult.setMessage("hello world");
        return myResult;
    }
}
