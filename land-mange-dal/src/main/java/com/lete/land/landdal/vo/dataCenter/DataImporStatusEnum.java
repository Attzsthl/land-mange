package com.lete.land.landdal.vo.dataCenter;

/**
 * Created by WJ on 2019/5/5 0005
 */
public enum DataImporStatusEnum {
    NOT(0,"未申报"),STA(1,"已申报"),URG(2,"催报"),UNDO(3,"退修"),FIN(4,"审核通过");
    private Integer index;

    private String desc;

    DataImporStatusEnum(Integer index, String desc) {
        this.index = index;
        this.desc = desc;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}


