package com.lete.land.landdal.repository;

import com.lete.land.landdal.entity.DataTemplateDeatil;
import com.lete.land.landdal.vo.dataCenter.DataImportVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by WJ on 2019/3/28 0028
 */
public interface DataTemplateDetailRepository extends JpaRepository<DataTemplateDeatil,String>, JpaSpecificationExecutor<DataTemplateDeatil> {

    @Query(value = "SELECT t.template_name,t.reporting_period ,d.year,d.status FROM d_template t LEFT JOIN d_template_deatil d ON t.id = d.template_id WHERE d.town_id = ?1" ,nativeQuery = true)
    List<Object[]> findTemplateDetailByTownId(String townId);

    @Query(value = "select  new com.lete.land.landdal.vo.dataCenter.DataImportVo(t.id as templateId, t.templateName,t.reportingPeriod,d.year,d.status) from DataTemplateDeatil d left join d.dataTemplate t where d.townId = :townId and d.year = :year")
    Page<DataImportVo> findTemplateDetailPageByTownId(@Param(value = "townId")String townId,@Param(value = "year") String year, Pageable pageable);

    @Query(value = "select  new com.lete.land.landdal.vo.dataCenter.DataImportVo(t.id as templateId, t.templateName,t.reportingPeriod,d.year,d.status) from DataTemplateDeatil d left join d.dataTemplate t where d.townId = :townId")
    Page<DataImportVo> findTemplateDetailPageByTownId(@Param(value = "townId")String townId, Pageable pageable);

    @Query(value = "SELECT * FROM d_template_deatil d WHERE d.template_id = ?1 AND d.year = ?2 ",nativeQuery = true)
    DataTemplateDeatil findByTemplateIdAndYear(String templateId, String year);

    @Query(value = "SELECT * FROM d_template_deatil d WHERE d.template_id = ?1 AND d.year = ?2  AND d.town_id = ?3",nativeQuery = true)
    DataTemplateDeatil findByTemplateIdAndYearAndTownId(String templateId, String year, String townId);

    List<DataTemplateDeatil> findByYearAndTownId(String year, String townId);
}
