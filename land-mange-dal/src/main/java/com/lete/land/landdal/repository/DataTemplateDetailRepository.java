package com.lete.land.landdal.repository;

import com.lete.land.landdal.entity.DataTemplateDeatil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by WJ on 2019/3/28 0028
 */
public interface DataTemplateDetailRepository extends JpaRepository<DataTemplateDeatil,String>, JpaSpecificationExecutor<DataTemplateDeatil> {

    @Query(value = "SELECT t.template_name,t.reporting_period ,d.year,d.status FROM d_template t LEFT JOIN d_template_deatil d ON t.id = d.template_id WHERE d.town_id = ?1" ,nativeQuery = true)
    List<Object[]> findTemplateDetailByTownId(String townId);


    long countById();
}
