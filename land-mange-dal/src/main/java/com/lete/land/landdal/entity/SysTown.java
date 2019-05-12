package com.lete.land.landdal.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by WJ on 2019/3/28 0028
 */
@Entity(name = "sys_town")
@Table
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class SysTown implements Serializable{
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private String id;

    @Column(name = "town")
    private String town;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }


}
