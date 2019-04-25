package com.lete.land.landdal.service;

import com.lete.land.landdal.entity.DataRegPopulation;
import com.lete.land.landdal.entity.DataTemplate;
import com.lete.land.landdal.repository.DataTemplateRepository;
import com.lete.land.landdal.vo.dataCenter.DataTemplateVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by WJ on 2019/4/24 0024
 */
@Service
public class DataTemplateService {
    @Resource
    private DataTemplateRepository dataTemplateRepository;
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
        List<Object[]> commentObject = dataTemplateRepository.findTemplateCommentsByTableName(tableName);

        List<DataTemplateVo> templateCommentList = commentObject.stream().map(obj -> {
            DataTemplateVo vo = new DataTemplateVo();
            vo.setColumnComment(obj[0].toString());
            vo.setColumnName(obj[1].toString());

            return vo;
        }).collect(Collectors.toList());

        return templateCommentList;
    }
}
