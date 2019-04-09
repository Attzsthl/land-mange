package com.lete.land.landdal.repository;

import com.lete.land.landdal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by WJ on 2019/3/27 0027
 */
@Repository
public interface UserRepository extends JpaRepository<User,Integer>, JpaSpecificationExecutor<User> {

   Optional<User> findByUsername(String userName);
}
