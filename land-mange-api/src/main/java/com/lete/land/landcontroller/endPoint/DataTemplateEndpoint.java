package com.lete.land.landcontroller.endPoint;

import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.lete.land.landdal.Result;
import com.lete.land.landdal.entity.DataRegPopulation;
import com.lete.land.landdal.entity.DataTemplate;
import com.lete.land.landdal.entity.LandMangeApprove;
import com.lete.land.landdal.entity.SysMuser;
import com.lete.land.landdal.service.DataRegPopulationService;
import com.lete.land.landdal.service.DataTemplateService;
import com.lete.land.landdal.vo.DataRegPopulationModel;
import com.lete.land.landdal.vo.SysMuserModel;
import com.lete.land.landdal.vo.dataCenter.DataImportVo;
import com.lete.land.landdal.vo.dataCenter.DataTemplateVo;
import com.lete.land.landdal.vo.dataCenter.TemplateRequestVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by WJ on 2019/4/23 0023
 */
@Controller
public class DataTemplateEndpoint {
    @Resource
    private DataRegPopulationService dataRegPopulationService;

    @Resource
    private DataTemplateService dataTemplateService;


    /*@RequestMapping(value = "/guest/alterColumn",method = RequestMethod.GET)
    public void alterColumnComment() {
        dataRegPopulationService.alterTemplate("id","id表头");
    }*/

    @RequestMapping(value = "/api/template-page", method = RequestMethod.GET)
    @ResponseBody
    public Page<DataTemplate> getTemplatePage(@RequestParam(name = "templateName", required = false) String templateName,
                                              @PageableDefault Pageable pageable) {
        Page<DataTemplate> list = dataTemplateService.getTemplatePage(templateName, pageable);
        return list;
    }

    @RequestMapping(value = "/api/template-edit", method = RequestMethod.GET)
    @ResponseBody
    public List<DataTemplateVo> getTemplateComments(@RequestParam(name = "tableName", required = true) String tableName) {
        return dataTemplateService.getTemplateComments(tableName);
    }

    @RequestMapping(value = "/api/template-edit-submit", method = RequestMethod.POST)
    @ResponseBody
    public Result editTemplate(@RequestBody TemplateRequestVo templateRequestVo) {
        return dataTemplateService.editTemplate(templateRequestVo.getTableName(), templateRequestVo.getTemplates());
    }

    @RequestMapping(value = "/api/template-import-page", method = RequestMethod.GET)
    @ResponseBody
    public Page<DataImportVo> getTemplateImportPage(@RequestParam(name = "townId", required = false) String townId,
                                                    @PageableDefault Pageable pageable) {
        Page<DataImportVo> list = dataTemplateService.getTemplateDetailPage(townId, pageable);
        return list;
    }

    @RequestMapping(value = "/api/readExcel", method = RequestMethod.POST)
    @ResponseBody
    public Result readExcelWithSheets(MultipartFile excel,
                                      @RequestParam(name = "templateId")String templateId,
                                      @RequestParam(name = "year")String year){
        Result result = dataTemplateService.importExcel(excel,templateId,year);
        return result;

    }


}
