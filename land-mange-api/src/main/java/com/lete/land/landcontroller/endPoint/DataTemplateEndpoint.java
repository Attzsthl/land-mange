package com.lete.land.landcontroller.endPoint;

import com.lete.land.landdal.entity.DataRegPopulation;
import com.lete.land.landdal.entity.DataTemplate;
import com.lete.land.landdal.entity.LandMangeApprove;
import com.lete.land.landdal.service.DataRegPopulationService;
import com.lete.land.landdal.service.DataTemplateService;
import com.lete.land.landdal.vo.dataCenter.DataTemplateVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

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
    public List<DataTemplateVo> getTemplateComments(@RequestParam(name = "tableName",required = true) String tableName) {
        return dataTemplateService.getTemplateComments(tableName);
    }
}
