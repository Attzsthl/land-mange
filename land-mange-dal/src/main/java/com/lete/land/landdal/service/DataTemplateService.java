package com.lete.land.landdal.service;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lete.land.landdal.Result;
import com.lete.land.landdal.entity.*;
import com.lete.land.landdal.repository.DataTemplateDetailRepository;
import com.lete.land.landdal.repository.DataTemplateRepository;
import com.lete.land.landdal.repository.SysTownRepository;
import com.lete.land.landdal.util.ExcelException;
import com.lete.land.landdal.util.ExcelListener;
import com.lete.land.landdal.vo.excelModel.DataLandUseTypeModel;
import com.lete.land.landdal.vo.excelModel.DataRegPopulationModel;
import com.lete.land.landdal.vo.dataCenter.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by WJ on 2019/4/24 0024
 */
@Service
public class DataTemplateService {
    @Resource
    private DataTemplateRepository dataTemplateRepository;

    @Resource
    private DataTemplateDetailRepository dataTemplateDetailRepository;

    @Resource
    private DataRegPopulationService dataRegPopulationService;

    @Resource
    private SysTownRepository sysTownRepository;

    @Resource
    private DataLandUseTypeService dataLandUseTypeService;




    public List<DataTemplateVo> getTemplateJson() {
        List<Object[]> objects = dataTemplateRepository.findTemplateCommentsByTableName("d_land_use_type");
        List<DataTemplateVo> list = new ArrayList<>();
        objects.forEach(objects1 -> {
            DataTemplateVo dataTemplateVo = new DataTemplateVo();
            dataTemplateVo.setColumnName(objects1[1].toString());
            dataTemplateVo.setColumnComment(objects1[0].toString());
            list.add(dataTemplateVo);
        });

        return list;
    }
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
        return JSONObject.parseArray(templateJson, DataTemplateVo.class);
    }

    public Result editTemplate(String tableName, List<DataTemplateVo> templates) {
        DataTemplate dataTemplate = dataTemplateRepository.findByTableName(tableName);

        Iterator<DataTemplateVo> iterator = templates.iterator();
        while (iterator.hasNext()) {
            DataTemplateVo vo = iterator.next();
            if (StringUtils.isEmpty(vo.getColumnName())) {
                iterator.remove();
            }
        }
        //  List<DataTemplateVo> oldTemplates = JSONObject.parseArray(templateJson, .class); // ***
        dataTemplate.setTableHead(JSON.toJSONString(templates));
        dataTemplateRepository.save(dataTemplate);

        return ResultFactory.buildSuccessResult("测试中");
    }

    //获取模板表的分页数据-不同的镇只能看到自己的
    public Page<DataImportVo> getTemplateDetailPage(String townId,String year,Pageable pageable) {
        generateTemplateDetail(townId,year); // 生成细节表 只生成一次
        Page<DataImportVo> page;
        if (year == null || year == "") {
            page  = dataTemplateDetailRepository.findTemplateDetailPageByTownId(townId, pageable);
        } else {
            page  = dataTemplateDetailRepository.findTemplateDetailPageByTownId(townId,year,pageable);
        }


        page.getContent().forEach(con -> {
            String status = getStatus(con.getStatus());
            con.setUploadStatus(status);
        });
        return page;
    }

    public void generateTemplateDetail(String townId,String year) {
        // 数据库查询该年份的细节表
        List<DataTemplate> dataTemplate = dataTemplateRepository.findAll();
        List<DataTemplateDeatil> dataTemplateDeatils;
        if(year == "" || year == null) {
            year = String.valueOf(LocalDate.now().getYear());
            dataTemplateDeatils = dataTemplateDetailRepository.findByYearAndTownId(year,townId); // 默认查询当前年份
        } else {
            dataTemplateDeatils =  dataTemplateDetailRepository.findByYearAndTownId(year,townId); // 默认查询当前年份
        }
        final String saveYear = year;
        if (dataTemplateDeatils.size() != dataTemplate.size()) {
            dataTemplate.forEach(template -> {
                DataTemplateDeatil dataTemplateDeatil = new DataTemplateDeatil();
                dataTemplateDeatil.setTemplateId(template.getId());
                dataTemplateDeatil.setTownId(townId);
                dataTemplateDeatil.setTownName(sysTownRepository.findById(townId).get().getTown());
                dataTemplateDeatil.setYear(saveYear);
                dataTemplateDeatil.setStatus(DataImporStatusEnum.NOT.getIndex());
                dataTemplateDeatils.add(dataTemplateDeatil);
            });

        }
        dataTemplateDetailRepository.saveAll(dataTemplateDeatils);
    }

    private String getStatus(Integer index) {
        for (DataImporStatusEnum vo : DataImporStatusEnum.values()) {
            if (vo.getIndex().equals(index)) {
                return vo.getDesc();
            }
        }
        return "未开始";
    }

    public Result importExcel(MultipartFile excel,String templateId,String year,String townId) {
        try {
            if(templateId.equals("1")) { //
                List<Object> data = readExcel(excel,new DataRegPopulationModel());
                dataRegPopulationService.transferAndSave(data,year,townId);

            }else if(templateId.equals("2")) {
                List<Object> data = readExcel(excel,new DataLandUseTypeModel());
                dataLandUseTypeService.transferAndSave(data,year,townId);
            }else if(templateId.equals("3")) {


            }

            DataTemplateDeatil  dataTemplateDeatil = dataTemplateDetailRepository.findByTemplateIdAndYearAndTownId(templateId,year,townId);
            dataTemplateDeatil.setStatus(DataImporStatusEnum.STA.getIndex()); // 设置已经提交
            dataTemplateDetailRepository.save(dataTemplateDeatil);
            return ResultFactory.buildSuccessResult("导入成功");
        }catch (Exception e) {
            System.out.println(e);
            return ResultFactory.buildFailResult("导入失败");
        }



    }
    // 根据templateId来选择模板
    public List<Object> readExcel(MultipartFile excel, BaseRowModel rowModel) {
        ExcelListener excelListener = new ExcelListener();
        ExcelReader reader = getReader(excel, excelListener);
        if (reader == null) {
            return null;
        }

        for (Sheet sheet : reader.getSheets()) {
            if (rowModel != null) {
                sheet.setClazz(rowModel.getClass());
            }
            reader.read(sheet);
        }

        return excelListener.getDatas();
    }

    public  ExcelReader getReader(MultipartFile excel,
                                  ExcelListener excelListener) {
        String filename = excel.getOriginalFilename();
        if (filename == null || (!filename.toLowerCase().endsWith(".xls") && !filename.toLowerCase().endsWith(".xlsx"))) {
            throw new ExcelException("文件格式错误！");
        }
        InputStream inputStream;
        try {
            inputStream = new BufferedInputStream(excel.getInputStream());
            return new ExcelReader(inputStream, null, excelListener, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 区用户查看所有模板表
    public Page<DataTemplateDeatil> getTemplateAuditPage(TemplateSearchVo seach, Pageable pageable) {
        return dataTemplateDetailRepository.findAll((Specification<DataTemplateDeatil>) (root,criteriaQuery,criteriaBuilder) -> {
                List<Predicate> predicates = new LinkedList<>();
                Optional<TemplateSearchVo> optional = Optional.ofNullable(seach);
                optional.filter(searchVo -> seach.getYear() != "").map(TemplateSearchVo::getYear).ifPresent(year -> {
                    predicates.add(criteriaBuilder.equal(root.get("year"),year));
                });
                optional.filter(searchVo -> seach.getTown() != "").map(TemplateSearchVo::getTown).ifPresent(town -> {
                    predicates.add(criteriaBuilder.like(root.get("townName"),"%" + town + "%"));
                });
                Predicate[] array = new Predicate[predicates.size()];
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get("townName")));
                return criteriaBuilder.and(predicates.toArray(array));
            },pageable);
    }
    // 审核变更模板表状态
    public Result auditTemplate(DataAuditRequestVo auditRequestVo) {
        DataTemplateDeatil dataTemplateDeatil = dataTemplateDetailRepository.findByTemplateIdAndYearAndTownId(auditRequestVo.getTemplateId(),auditRequestVo.getYear(),auditRequestVo.getTownId());
        try {
            dataTemplateDeatil.setStatus(Integer.valueOf(auditRequestVo.getStatus()));
            dataTemplateDetailRepository.save(dataTemplateDeatil);
        }catch (NullPointerException e) {
            System.out.println("数据不存在");
            return ResultFactory.buildFailResult("数据不存在");
        }

        return ResultFactory.buildSuccessResult("催报成功");
    }
}
