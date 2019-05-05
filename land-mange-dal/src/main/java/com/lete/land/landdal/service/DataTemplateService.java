package com.lete.land.landdal.service;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lete.land.landdal.Result;
import com.lete.land.landdal.entity.DataRegPopulation;
import com.lete.land.landdal.entity.DataTemplate;
import com.lete.land.landdal.entity.DataTemplateDeatil;
import com.lete.land.landdal.entity.SysMuser;
import com.lete.land.landdal.repository.DataRegPopulationRepository;
import com.lete.land.landdal.repository.DataTemplateDetailRepository;
import com.lete.land.landdal.repository.DataTemplateRepository;
import com.lete.land.landdal.util.ExcelException;
import com.lete.land.landdal.util.ExcelListener;
import com.lete.land.landdal.vo.DataRegPopulationModel;
import com.lete.land.landdal.vo.dataCenter.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
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

    @Resource
    private DataRegPopulationService dataRegPopulationService;

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

    //获取模板表的分页数据
    public Page<DataImportVo> getTemplateDetailPage(String townId, Pageable pageable) {
        Page<DataImportVo> page = dataTemplateDetailRepository.findTemplateDetailPageByTownId(townId, pageable);
        page.getContent().forEach(con -> {
            String status = getStatus(con.getStatus());
            con.setUploadStatus(status);
        });
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

    @Transactional
    public Result importExcel(MultipartFile excel,String templateId,String year) {
        if(templateId.equals("1")) { //
            List<Object> data = readExcel(excel,new DataRegPopulationModel());
            dataRegPopulationService.transferAndSave(data);
            DataTemplateDeatil dataTemplateDeatil = dataTemplateDetailRepository.findByTemplateIdAndYear(templateId,year);
            dataTemplateDeatil.setStatus(DataImporStatusEnum.STA.getIndex()); // 设置已经提交
        }else {
            // 其他模板
        }

        return ResultFactory.buildSuccessResult("导入成功");

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


}
