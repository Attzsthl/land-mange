package com.lete.land.landdal.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lete.land.landdal.Result;
import com.lete.land.landdal.entity.DataTemplate;
import com.lete.land.landdal.repository.DataTemplateDetailRepository;
import com.lete.land.landdal.repository.DataTemplateRepository;
import com.lete.land.landdal.vo.dataCenter.DataImporStatusEnum;
import com.lete.land.landdal.vo.dataCenter.DataImportVo;
import com.lete.land.landdal.vo.dataCenter.DataTemplateVo;
import com.lete.land.landdal.vo.dataCenter.TemplateVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by WJ on 2019/4/24 0024
 */
@Service
public class DataTemplateService {
    @Resource
    private DataTemplateRepository dataTemplateRepository;

    @Resource
    private DataTemplateDetailRepository dataTemplateDetailRepository;

    //获取模板表的分页数据
    public Page<DataTemplate> getTemplatePage(String templateName, Pageable pageable) {
        Specification<DataTemplate> specification = new Specification<DataTemplate>() {
            @Override
            public Predicate toPredicate(Root<DataTemplate> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

                List<Predicate> predicateList = new ArrayList<>();
                if (!StringUtils.isEmpty(templateName)) {
                    Path<String> templateName = root.get("templateName");
                    predicateList.add(cb.like(templateName, "%" + templateName + "%"));
                }

                Predicate[] preArr = new Predicate[predicateList.size()];
                return cq.where(predicateList.toArray(preArr)).getRestriction();
            }
        };
        Page<DataTemplate> list = dataTemplateRepository.findAll(specification, pageable);
        return list;
    }

    //
    public List<DataTemplateVo> getTemplateComments(String tableName) {
        String templateJson = dataTemplateRepository.findTableHeadByTableName(tableName);
        return JSONObject.parseArray(templateJson,DataTemplateVo.class);
//        List<Object[]> commentObject = dataTemplateRepository.findTemplateCommentsByTableName(tableName);
//        List<DataTemplateVo> templateCommentList = commentObject.stream().map(obj -> {
//            DataTemplateVo vo = new DataTemplateVo();
//            vo.setColumnComment(obj[0].toString());
//            vo.setColumnName(obj[1].toString());
//
//            return vo;
//        }).collect(Collectors.toList());
//
//        return templateCommentList;
    }

    public Result editTemplate(String tableName, List<DataTemplateVo> templates) {
        DataTemplate dataTemplate = dataTemplateRepository.findByTableName(tableName);

        Iterator<DataTemplateVo> iterator = templates.iterator();
        while (iterator.hasNext()) {
            DataTemplateVo vo = iterator.next();
            if(StringUtils.isEmpty(vo.getColumnName())){
                iterator.remove();
            }
        }
        //  List<DataTemplateVo> oldTemplates = JSONObject.parseArray(templateJson, .class); // ***
        dataTemplate.setTableHead(JSON.toJSONString(templates));
        dataTemplateRepository.save(dataTemplate);

        return ResultFactory.buildSuccessResult("测试中");
    }

    //获取模板表的分页数据
    public Page<DataImportVo> getTemplateDetailPage(String townId, Pageable pageable) {
        List<Object[]> content = dataTemplateDetailRepository.findTemplateDetailByTownId(townId);
        long total = dataTemplateDetailRepository.countById();
        List<DataImportVo> dataImportVos = new ArrayList<>();

        content.forEach(obj -> {
            DataImportVo dataImportVo = new DataImportVo();
            dataImportVo.setTemplateName(obj[0].toString());
            dataImportVo.setReportingPeriod(obj[1].toString());
            dataImportVo.setYear(obj[2].toString());
            String status = getStatus(Integer.valueOf(obj[3].toString()));
            dataImportVo.setStatus(status);
            dataImportVos.add(dataImportVo);
        });

        Page<DataImportVo> page = new PageImpl<>(dataImportVos,pageable,total);
        return page;
    }

    private String getStatus(Integer index) {
        for (DataImporStatusEnum vo : DataImporStatusEnum.values()) {
            if (vo.getIndex().equals(index)) {
                return vo.getDesc();
            }
        }
        return "未开始";
    }
}
