package com.lete.land.landdal.vo.dataCenter;

import java.io.Serializable;

/**
 * Created by WJ on 2019/5/9 0009
 * 确定唯一上报表的VO
 */
public class DataAuditRequestVo implements Serializable {

    private static final long serialVersionUID = 3541211980100712433L;

    private String templateId;

    private String year;

    private String townId;

    private String status;

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTownId() {
        return townId;
    }

    public void setTownId(String townId) {
        this.townId = townId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
