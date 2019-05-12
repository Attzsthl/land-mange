package com.lete.land.landdal.vo.landMange;

import java.io.Serializable;

/**
 * Created by WJ on 2019/5/10 0010
 */
public class ExcelAnalysisVo implements Serializable {
    private static final long serialVersionUID = -8189486977207293064L;

    private String year;

    private double yearendCulArea;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public double getYearendCulArea() {
        return yearendCulArea;
    }

    public void setYearendCulArea(double yearendCulArea) {
        this.yearendCulArea = yearendCulArea;
    }
}
