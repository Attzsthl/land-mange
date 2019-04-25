package com.lete.land.landdal.entity;

import javax.persistence.*;

@Entity
@Table(name = "sys_muser")
public class SysMuser {

 @Id
 @Column(name = "id")
  private Integer id;
 @Column(name = "name")
  private String name;
 @Column(name = "password")
  private String password;
 @Column(name = "address")
  private String address;
 @Column(name = "tes")
  private String tes;
 @Column(name = "qq")
  private String qq;
 @Column(name = "sex")
  private String sex;
 @Column(name = "uimg")
  private String uimg;
 @Column(name = "rote_type")
  private String roteType;
 @Column(name = "sign_time")
  private java.sql.Timestamp signTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }


  public String getTes() {
    return tes;
  }

  public void setTes(String tes) {
    this.tes = tes;
  }


  public String getQq() {
    return qq;
  }

  public void setQq(String qq) {
    this.qq = qq;
  }


  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }


  public String getUimg() {
    return uimg;
  }

  public void setUimg(String uimg) {
    this.uimg = uimg;
  }


  public String getRoteType() {
    return roteType;
  }

  public void setRoteType(String roteType) {
    this.roteType = roteType;
  }


  public java.sql.Timestamp getSignTime() {
    return signTime;
  }

  public void setSignTime(java.sql.Timestamp signTime) {
    this.signTime = signTime;
  }

}
