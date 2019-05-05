package com.lete.land.landdal.vo.dataCenter;

import java.io.Serializable;

public class DataTemplateVo implements Serializable {

    private static final long serialVersionUID = -5565938505678505506L;

    private String columnName;

     private String columnComment;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }
}
