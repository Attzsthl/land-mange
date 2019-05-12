package com.lete.land.landdal.vo.dataCenter;

import java.io.Serializable;

/**
 * Created by WJ on 2019/5/7 0007
 */
public class TemplateSearchVo implements Serializable {
    private static final long serialVersionUID = -9212392560739441940L;
    private String year;
    private String town;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}
