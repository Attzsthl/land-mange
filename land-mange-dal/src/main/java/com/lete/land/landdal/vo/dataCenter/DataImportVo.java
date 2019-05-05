package com.lete.land.landdal.vo.dataCenter;

import java.io.Serializable;

/**
 * Created by WJ on 2019/5/5 0005
 */
public class DataImportVo implements Serializable {

    private static final long serialVersionUID = 2510180998320508070L;

    private String templateId;

    private String templateName;

    private String reportingPeriod;

    private String year;

    private int status;

    private String uploadStatus;

    public DataImportVo(String templateId,String templateName, String reportingPeriod, String year, int status) {
        this.templateId = templateId;
        this.templateName = templateName;
        this.reportingPeriod = reportingPeriod;
        this.year = year;
        this.status = status;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getUploadStatus() {
        return uploadStatus;
    }

    public void setUploadStatus(String uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getReportingPeriod() {
        return reportingPeriod;
    }

    public void setReportingPeriod(String reportingPeriod) {
        this.reportingPeriod = reportingPeriod;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
