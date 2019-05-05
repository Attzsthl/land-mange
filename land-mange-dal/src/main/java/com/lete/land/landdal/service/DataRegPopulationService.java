package com.lete.land.landdal.service;

import com.lete.land.landdal.entity.DataRegPopulation;
import com.lete.land.landdal.repository.DataRegPopulationRepository;
import com.lete.land.landdal.vo.DataRegPopulationModel;
import com.lete.land.landdal.vo.dataCenter.SocialSecurityEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    public  void transferAndSave(List<Object> modelList){
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




}
