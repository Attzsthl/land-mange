package com.lete.land.landdal.repository;

import com.lete.land.landdal.entity.LandMangeApprove;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by WJ on 2019/4/1 0001
 */
@Repository
public interface LandMangeApproveRepository  extends JpaRepository<LandMangeApprove,Integer>, JpaSpecificationExecutor<LandMangeApprove> {
}
