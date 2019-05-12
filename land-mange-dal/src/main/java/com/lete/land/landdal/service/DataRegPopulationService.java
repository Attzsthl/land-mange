package com.lete.land.landdal.service;

import com.lete.land.landdal.Result;
import com.lete.land.landdal.entity.DataRegPopulation;
import com.lete.land.landdal.repository.DataRegPopulationRepository;
import com.lete.land.landdal.vo.excelModel.DataRegPopulationModel;
import com.lete.land.landdal.vo.dataCenter.SocialSecurityEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
        // dataRegPopulationRepository.alterTemplate("表头1");
    }

    public  void transferAndSave(List<Object> modelList,String year,String townId){
        List<DataRegPopulation> list = new ArrayList<>();

        try {
            for(Object obj : modelList){
                DataRegPopulation dataRegPopulation = new DataRegPopulation();
                DataRegPopulationModel model = (DataRegPopulationModel)obj;
                String uuid = UUID.randomUUID().toString().replace("-", "");
                dataRegPopulation.setId(uuid);
                dataRegPopulation.setIdCard(model.getIdCard());
                dataRegPopulation.setBornDate(new SimpleDateFormat("yyyy-MM-dd").parse(model.getBornDate()));
                dataRegPopulation.setCadastralNum(model.getCadastralNum());
                dataRegPopulation.setSex(model.getSex());
                dataRegPopulation.setYear(year);
                dataRegPopulation.setTownId(townId);

                if(model.getSocialSecurityType().equals(SocialSecurityEnum.City.getDesc())) {
                    dataRegPopulation.setSocialSecurityType(SocialSecurityEnum.City.getIdnex());
                }else {
                    dataRegPopulation.setSocialSecurityType(SocialSecurityEnum.Vill.getIdnex());
                }

                dataRegPopulation.setTown(model.getTown());
                dataRegPopulation.setVillage(model.getVillage());
                list.add(dataRegPopulation);
            }
        }catch (ParseException e){
            System.out.println("日期类型转换错误");
        }

        dataRegPopulationRepository.saveAll(list);
    }


    public Page<DataRegPopulation> getRegPopulationPage(String townId,String year,Pageable pageable) {
        return dataRegPopulationRepository.findAll((Specification<DataRegPopulation>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new LinkedList<>();
            predicates.add(criteriaBuilder.equal(root.get("townId"),townId));
            if(year != null) {
                predicates.add(criteriaBuilder.equal(root.get("year"),year));
            }
            Predicate[] array = new Predicate[predicates.size()];
            return criteriaBuilder.and(predicates.toArray(array));
        },pageable);
    }

    @Transactional
    public Result saveRegPopulation(DataRegPopulation dataRegPopulation) {
        try{
            dataRegPopulationRepository.save(dataRegPopulation);
            return ResultFactory.buildSuccessResult("保存成功");
        }catch (Exception e) {
            return ResultFactory.buildFailResult("保存失败");
        }
    }

    @Transactional
    public Result deleteRegPopulation(String regId) {
        try{
            dataRegPopulationRepository.deleteById(regId);
            return ResultFactory.buildSuccessResult("保存成功");
        }catch (Exception e) {
            return ResultFactory.buildFailResult("保存失败");
        }
    }
}
