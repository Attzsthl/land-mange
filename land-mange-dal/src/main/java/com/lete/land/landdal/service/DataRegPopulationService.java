package com.lete.land.landdal.service;

import com.lete.land.landdal.repository.DataRegPopulationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by WJ on 2019/4/22 0022
 */
@Service
public class DataRegPopulationService {
    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    private DataRegPopulationRepository dataRegPopulationRepository;

    @Transactional
    public void alterTemplate(String column, String comment) {
        dataRegPopulationRepository.alterTemplate("表头1");
    }





}
