package com.lete.land.landdal.repository;

import com.lete.land.landdal.entity.DataTemplate;
import com.lete.land.landdal.entity.SysMuser;
import com.lete.land.landdal.vo.dataCenter.DataTemplateVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by WJ on 2019/3/28 0028
 */
public interface DataTemplateRepository extends JpaRepository<DataTemplate,String>, JpaSpecificationExecutor<DataTemplate> {

    @Query(value = "SELECT  column_name, column_comment FROM information_schema.columns WHERE table_schema ='land_mange' AND  table_name = ?1" ,nativeQuery = true)
    List<Object[]> findTemplateCommentsByTableName(String tableName);

    DataTemplate findOneByIdAndName();
}
