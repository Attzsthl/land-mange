package com.lete.land.landdal.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * Created by WJ on 2019/5/5 0005
 */
public class DataRegPopulationModel extends BaseRowModel {
    @ExcelProperty(value = "序号",index = 0)
    private Integer id;

    @ExcelProperty(value = "身份证号码",index = 1)
    private String idCard;

    @ExcelProperty(value = "性别",index = 2)
    private String sex;

    @ExcelProperty(value = "出生日期",index = 3)
    private String bornDate;

    @ExcelProperty(value = "地籍编号",index = 4)
    private String cadastralNum;

    @ExcelProperty(value = "所在镇",index = 5)
    private String town;

    @ExcelProperty(value = "所在村",index = 6)
    private String village;

    @ExcelProperty(value = "社保类型",index = 7)
    private String socialSecurityType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBornDate() {
        return bornDate;
    }

    public void setBornDate(String bornDate) {
        this.bornDate = bornDate;
    }

    public String getCadastralNum() {
        return cadastralNum;
    }

    public void setCadastralNum(String cadastralNum) {
        this.cadastralNum = cadastralNum;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getSocialSecurityType() {
        return socialSecurityType;
    }

    public void setSocialSecurityType(String socialSecurityType) {
        this.socialSecurityType = socialSecurityType;
    }
}
