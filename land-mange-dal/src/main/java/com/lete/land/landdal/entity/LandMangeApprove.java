package com.lete.land.landdal.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by WJ on 2019/4/1 0001
 */
@Entity(name = "land_mange_approve")
@Table
public class LandMangeApprove implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "pro_code")
    private String proCode;

    @Column(name = "pro_name")
    private String proName;

    @Column(name = "apply_date")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date applyDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProCode() {
        return proCode;
    }

    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    @Override
    public String toString() {
        return "MangeApprove" + "id=" + id + "proCode" + proCode + "proName" + proName;
    }
}
