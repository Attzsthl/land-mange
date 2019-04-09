package com.lete.land.landcontroller.endPoint;

import com.lete.land.landdal.entity.LandMangeApprove;
import com.lete.land.landdal.service.LandMangeApproveService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * Created by WJ on 2019/4/1 0001
 */
@Controller
public class MangeApproveController {
    @Resource
    private LandMangeApproveService landMangeApproveService;

    @RequestMapping(value = "/api/mangeApprove", method = RequestMethod.POST )
    @ResponseBody
    public Page<LandMangeApprove> getMangeApprovePage(@RequestParam(name = "proName", required = false) String proName,
                                                      @PageableDefault Pageable pageable)
    {
        Page<LandMangeApprove> list = landMangeApproveService.queryMangeApprove(proName,pageable);
        return list;
    }

    @RequestMapping(value = "/guest/redisGet", method = RequestMethod.GET )
    @ResponseBody
    public LandMangeApprove redisGetApprove(@RequestParam(name = "approveId", required = false)Integer approveId)
    {
       LandMangeApprove landMangeApprove = landMangeApproveService.findApproveById(approveId);
        return landMangeApprove;
    }

}
