package com.lete.land.landcontroller.endPoint;

import com.lete.land.landdal.Result;
import com.lete.land.landdal.entity.DataRegPopulation;
import com.lete.land.landdal.service.DataRegPopulationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by WJ on 2019/4/23 0023
 */
@Controller
public class DataRegPopulationEndpoint {
    @Resource
    private DataRegPopulationService dataRegPopulationService;


    @RequestMapping(value = "/api/reg-population-page", method = RequestMethod.GET)
    @ResponseBody
    public Page<DataRegPopulation> getRegPopulationPage(
            @RequestParam(value = "townId",required = false)String townId,
            @RequestParam(value = "year",required = false)String year,
            @PageableDefault Pageable pageable) {
        return dataRegPopulationService.getRegPopulationPage(townId,year,pageable);
    }

    @RequestMapping(value = "/api/save-reg-population", method = RequestMethod.POST)
    @ResponseBody
    public Result saveRegPopulation(@RequestBody DataRegPopulation dataRegPopulation) {
        return dataRegPopulationService.saveRegPopulation(dataRegPopulation);
    }

    @RequestMapping(value = "/api/delete-reg-population", method = RequestMethod.POST)
    @ResponseBody
    public Result deleteRegPopulation(@RequestParam(value = "id") String id) {
        return dataRegPopulationService.deleteRegPopulation(id);
    }


}
