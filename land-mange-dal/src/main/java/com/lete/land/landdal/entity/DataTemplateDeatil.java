package com.lete.land.landdal.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "d_template_deatil")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class DataTemplateDeatil {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private String id;
    @Column(name = "town_id")
    private String townId;
    @Column(name = "town_name")
    private String townName;
    @Column(name = "year")
    private String year;
    @Column(name = "status")
    private Integer status;
    @Column(name = "template_id")
    private String templateId;
    @ManyToOne
    @JoinColumn(name = "template_id", insertable = false, updatable = false, nullable=true)
    private DataTemplate dataTemplate;

    public DataTemplate getDataTemplate() {
        return dataTemplate;
    }

    public void setDataTemplate(DataTemplate dataTemplate) {
        this.dataTemplate = dataTemplate;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTownId() {
        return townId;
    }

    public void setTownId(String townId) {
        this.townId = townId;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
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
