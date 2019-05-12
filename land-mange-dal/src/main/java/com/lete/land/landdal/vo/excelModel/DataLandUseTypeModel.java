package com.lete.land.landdal.vo.excelModel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * Created by WJ on 2019/5/5 0005
 */
public class DataLandUseTypeModel extends BaseRowModel {
    @ExcelProperty(value = "序号",index = 0)
    private Integer id;

    @ExcelProperty(value = "村",index = 1)
    private String village;

    @ExcelProperty(value = "镇",index = 2)
    private String town;

    @ExcelProperty(value = "年末耕地面积",index = 3)
    private Double yearendCulArea;

    @ExcelProperty(value = "高标准农田面积",index = 4)
    private Double hsfArea;

    @ExcelProperty(value = "设施农业面积",index = 5)
    private Double faArea;

    @ExcelProperty(value = "粮食播种面积",index = 6)
    private Double gsArea;

    @ExcelProperty(value = "蔬菜播种面积",index = 7)
    private Double vpArea;

    @ExcelProperty(value = "水体养殖面积",index = 8)
    private Double aqArea;

    @ExcelProperty(value = "村集体经营性建设用地面积",index = 9)
    private Double constructionLandArea;

    @ExcelProperty(value = "宅基地面积",index = 10)
    private Double homesteadArea;

    @ExcelProperty(value = "统计年份",index = 11)
    private String year;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public Double getYearendCulArea() {
        return yearendCulArea;
    }

    public void setYearendCulArea(Double yearendCulArea) {
        this.yearendCulArea = yearendCulArea;
    }

    public Double getHsfArea() {
        return hsfArea;
    }

    public void setHsfArea(Double hsfArea) {
        this.hsfArea = hsfArea;
    }

    public Double getFaArea() {
        return faArea;
    }

    public void setFaArea(Double faArea) {
        this.faArea = faArea;
    }

    public Double getGsArea() {
        return gsArea;
    }

    public void setGsArea(Double gsArea) {
        this.gsArea = gsArea;
    }

    public Double getVpArea() {
        return vpArea;
    }

    public void setVpArea(Double vpArea) {
        this.vpArea = vpArea;
    }

    public Double getAqArea() {
        return aqArea;
    }

    public void setAqArea(Double aqArea) {
        this.aqArea = aqArea;
    }

    public Double getConstructionLandArea() {
        return constructionLandArea;
    }

    public void setConstructionLandArea(Double constructionLandArea) {
        this.constructionLandArea = constructionLandArea;
    }

    public Double getHomesteadArea() {
        return homesteadArea;
    }

    public void setHomesteadArea(Double homesteadArea) {
        this.homesteadArea = homesteadArea;
    }


    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
