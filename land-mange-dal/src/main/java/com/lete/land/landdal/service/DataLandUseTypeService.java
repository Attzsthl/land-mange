package com.lete.land.landdal.service;

import com.lete.land.landdal.Result;
import com.lete.land.landdal.entity.DataLandUseType;
import com.lete.land.landdal.repository.DataLandUseRepository;
import com.lete.land.landdal.vo.Constants;
import com.lete.land.landdal.vo.excelModel.DataLandUseTypeModel;
import com.lete.land.landdal.vo.landMange.ExcelAnalysisVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by WJ on 2019/4/24 0024
 */
@Service
public class DataLandUseTypeService {
    @Resource
    private DataLandUseRepository dataLandUseRepository;

    public Page<DataLandUseType> getLandUseTypePage(String townId, String village,String year, Pageable pageable) {
        return dataLandUseRepository.findAll((Specification<DataLandUseType>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new LinkedList<>();

            if(!StringUtils.isEmpty(year)) {
                predicates.add(criteriaBuilder.equal(root.get("year"),year));
            }
            if(!StringUtils.isEmpty(village)) {
                predicates.add(criteriaBuilder.equal(root.get("village"),year));
            }
            if(!StringUtils.isEmpty(townId)) {
                predicates.add(criteriaBuilder.equal(root.get("townId"),year));
            }
            Predicate[] array = new Predicate[predicates.size()];
            return criteriaBuilder.and(predicates.toArray(array));
        },pageable);
    }

    public Result save() {
        DataLandUseType dataLandUseType = new DataLandUseType();
        dataLandUseType.setTown("什么镇");
        dataLandUseType.setAqArea(new Double(200));
        try{
            dataLandUseRepository.save(dataLandUseType);
            return ResultFactory.buildSuccessResult("保存成功");
        }catch (Exception e) {
            System.out.println(e);
            return ResultFactory.buildFailResult("保存失败");
        }
    }

    public Result delete(String id) {
        try{
            dataLandUseRepository.deleteById(id);
            return ResultFactory.buildSuccessResult("保存成功");
        }catch (Exception e) {
            return ResultFactory.buildFailResult("保存失败");
        }
    }

    public  void transferAndSave(List<Object> modelList,String year,String townId){
        List<DataLandUseType> list = new ArrayList<>();

            for(Object obj : modelList){
                DataLandUseType dataLandUseType = new DataLandUseType();
                DataLandUseTypeModel model = (DataLandUseTypeModel)obj;
                dataLandUseType.setVillage(model.getVillage());
                dataLandUseType.setTown(model.getTown());
                dataLandUseType.setTownId(townId);
                dataLandUseType.setYear(model.getYear());

                dataLandUseType.setAqArea(model.getAqArea());
                dataLandUseType.setFaArea(model.getFaArea());
                dataLandUseType.setGsArea(model.getGsArea());
                dataLandUseType.setHsfArea(model.getHsfArea());
                dataLandUseType.setYearendCulArea(model.getYearendCulArea());
                dataLandUseType.setConLandArea(model.getConstructionLandArea());
                dataLandUseType.setVpArea(model.getVpArea());
                dataLandUseType.setHomeSteadArea(model.getHomesteadArea());

                list.add(dataLandUseType);
            }


        dataLandUseRepository.saveAll(list);
    }

    public List<ExcelAnalysisVo> analysisLandType(String townId, String year, String indicators) {
        List<ExcelAnalysisVo> excelAnalysisVoList = new ArrayList<>();
        // 查询
        List<DataLandUseType> dataLandUseTypeList = dataLandUseRepository.findAll((Specification<DataLandUseType>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new LinkedList<>();
            if(!StringUtils.isEmpty(townId)) {
                predicates.add(criteriaBuilder.equal(root.get("townId"),year));
            }
            Predicate[] array = new Predicate[predicates.size()];

            return criteriaBuilder.and(predicates.toArray(array));
        });
        List<String> years = Arrays.asList(Constants.yearArr);

        years.forEach(yearVo -> {
            ExcelAnalysisVo excelAnalysisVo = new ExcelAnalysisVo();
            excelAnalysisVo.setYear(yearVo);
            final Computer computer = new Computer();
            dataLandUseTypeList.forEach(dataLandUseType -> {
                if(yearVo.equals(dataLandUseType.getYear())) {
                    double sumData = computer.getSumData();
                    sumData += dataLandUseType.getYearendCulArea();
                    computer.setSumData(sumData);
                }
            });
            excelAnalysisVo.setYearendCulArea(computer.getSumData());
            excelAnalysisVoList.add(excelAnalysisVo);
        });

        return excelAnalysisVoList;
    }

    class Computer {
        private double sumData;

        public double getSumData() {
            return sumData;
        }

        public void setSumData(double sumData) {
            this.sumData = sumData;
        }
    }
}
