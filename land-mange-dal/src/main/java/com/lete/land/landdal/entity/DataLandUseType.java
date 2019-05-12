package com.lete.land.landdal.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "d_land_use_type")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")

public class DataLandUseType {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "id")
    private String id;
    @Column(name = "village")
    private String village;
    @Column(name = "town")
    private String town;
    @Column(name = "year")
    private String year;
    @Column(name = "town_id")
    private String townId;
    @Column(name = "yearend_cul_area")
    private Double yearendCulArea;
    @Column(name = "hsf_area")
    private Double hsfArea;
    @Column(name = "fa_area")
    private Double faArea;
    @Column(name = "gs_area")
    private Double gsArea;
    @Column(name = "vp_area")
    private Double vpArea;
    @Column(name = "aq_area")
    private Double aqArea;
    @Column(name = "con_land_area")
    private Double conLandArea;
    @Column(name = "home_stead_area")
    private Double homeSteadArea;


    public String getId() {
        return id;
    }

    public void setId(String id) {
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


    public Double getConLandArea() {
        return conLandArea;
    }

    public void setConLandArea(Double conLandArea) {
        this.conLandArea = conLandArea;
    }


    public Double getHomeSteadArea() {
        return homeSteadArea;
    }

    public void setHomeSteadArea(Double homeSteadArea) {
        this.homeSteadArea = homeSteadArea;
    }

}
