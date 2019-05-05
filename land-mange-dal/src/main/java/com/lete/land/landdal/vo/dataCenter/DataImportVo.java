package com.lete.land.landdal.vo.dataCenter;

import java.io.Serializable;

/**
 * Created by WJ on 2019/5/5 0005
 */
public class DataImportVo implements Serializable {

    private static final long serialVersionUID = 2510180998320508070L;

    private String templateName;

    private String reportingPeriod;

    private String year;

    private String status;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
