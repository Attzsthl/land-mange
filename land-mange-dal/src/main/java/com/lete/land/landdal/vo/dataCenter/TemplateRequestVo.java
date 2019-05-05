package com.lete.land.landdal.vo.dataCenter;

import java.io.Serializable;
import java.util.List;

/**
 * Created by WJ on 2019/5/4 0004
 */
public class TemplateRequestVo implements Serializable {
    private static final long serialVersionUID = -7696930838328708473L;

    private String tableName;

    private List<DataTemplateVo> templates;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<DataTemplateVo> getTemplates() {
        return templates;
    }

    public void setTemplates(List<DataTemplateVo> templates) {
        this.templates = templates;
    }
}
