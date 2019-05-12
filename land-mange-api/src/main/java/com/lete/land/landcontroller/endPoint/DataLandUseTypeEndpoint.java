package com.lete.land.landcontroller.endPoint;

import com.lete.land.landdal.Result;
import com.lete.land.landdal.entity.DataLandUseType;
import com.lete.land.landdal.service.DataLandUseTypeService;
import com.lete.land.landdal.vo.landMange.ExcelAnalysisVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by WJ on 2019/4/23 0023
 */
@Controller
@RequestMapping("/api/land-use-type")
public class DataLandUseTypeEndpoint {
    @Resource
    private DataLandUseTypeService dataLandUseTypeService;

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    @ResponseBody
    public Page<DataLandUseType> getLandUseTypePage(
            @RequestParam(value = "townId",required = false)String townId,
            @RequestParam(value = "village",required = false)String village,
            @RequestParam(value = "year",required = false)String year,
            @PageableDefault Pageable pageable) {
        return dataLandUseTypeService.getLandUseTypePage(townId,village,year,pageable);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Result save() {
        return dataLandUseTypeService.save();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Result deleteRegPopulation(@RequestParam(value = "id") String id) {
        return dataLandUseTypeService.delete(id);
    }

    @RequestMapping(value = "/analysis", method = RequestMethod.GET)
    @ResponseBody
    public List<ExcelAnalysisVo> landTypeAnalysis(@RequestParam(value = "townId",required = false) String townId,
                                 @RequestParam(value = "year",required = false)String year,
                                 @RequestParam(value = "indicators",required = false)String indicators) {
        return dataLandUseTypeService.analysisLandType(townId,year,indicators);
    }

}
