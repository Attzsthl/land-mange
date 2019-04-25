package com.lete.land.landdal.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

public class SysMuserModel extends BaseRowModel {

    @ExcelProperty(value = "序号",index = 0)
    private Integer id;

    @ExcelProperty(value = "姓名",index = 1)
    private String name;

    @ExcelProperty(value = "密码",index = 2)
    private String password;

    @ExcelProperty(value = "角色",index = 3)
    private String roteType;

    @ExcelProperty(value = "地址",index = 4)
    private String address;

    @ExcelProperty(value = "电话",index = 5)
    private String tes;

    @ExcelProperty(value = "qq",index = 6)
    private String qq;

    @ExcelProperty(value = "性别",index = 7)
    private String sex;

    @ExcelProperty(value = "图片地址",index = 8)
    private String uimg;

    @ExcelProperty(value = "登记时间",index = 9)
    private String signTime;

    private String ddd = "111";


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoteType() {
        return roteType;
    }

    public void setRoteType(String roteType) {
        this.roteType = roteType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTes() {
        return tes;
    }

    public void setTes(String tes) {
        this.tes = tes;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUimg() {
        return uimg;
    }

    public void setUimg(String uimg) {
        this.uimg = uimg;
    }

    public String getSignTime() {
        return signTime;
    }

    public void setSignTime(String signTime) {
        this.signTime = signTime;
    }


}
