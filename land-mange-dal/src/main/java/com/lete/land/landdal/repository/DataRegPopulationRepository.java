package com.lete.land.landdal.repository;

import com.lete.land.landdal.entity.DataRegPopulation;
import com.lete.land.landdal.vo.dataCenter.TemplateVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by WJ on 2019/3/28 0028
 */
public interface DataRegPopulationRepository extends JpaRepository<DataRegPopulation,String>, JpaSpecificationExecutor<DataRegPopulation> {
    @Modifying
    @Query(value = "alter table d_reg_population modify column id varchar(32) comment ?1",nativeQuery = true)
    void alterTemplate(String comment);

    @Query(value = "select u.idCard as idCard,u.sex as sex from DataRegPopulation u")
    List<TemplateVo> findTemplateVo();
}
