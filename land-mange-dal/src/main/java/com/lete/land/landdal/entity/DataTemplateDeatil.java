package com.lete.land.landdal.entity;

import javax.persistence.*;

@Entity
@Table(name = "d_template_deatil")
public class DataTemplateDeatil {

 @Id
 @Column(name = "id")
  private String id;
 @Column(name = "template_id")
  private String templateId;
 @Column(name = "town_id")
  private String townId;
 @Column(name = "year")
  private String year;
 @Column(name = "status")
  private Integer status;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getTemplateId() {
    return templateId;
  }

  public void setTemplateId(String templateId) {
    this.templateId = templateId;
  }


  public String getTownId() {
    return townId;
  }

  public void setTownId(String townId) {
    this.townId = townId;
  }


  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
