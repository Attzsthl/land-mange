package com.lete.land.landdal.repository;

import com.lete.land.landdal.entity.DataLandUseType;
import com.lete.land.landdal.vo.DataLandUsePro;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by WJ on 2019/3/28 0028
 */
public interface DataLandUseRepository extends JpaRepository<DataLandUseType,String>, JpaSpecificationExecutor<DataLandUseType> {
}
