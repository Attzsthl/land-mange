package com.lete.land.landcontroller.endPoint;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.lete.land.landdal.entity.LandMangeApprove;
import com.lete.land.landdal.service.DataRegPopulationService;
import com.lete.land.landdal.service.LandMangeApproveService;
import com.lete.land.landdal.vo.SysMuserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by WJ on 2019/4/1 0001
 */
@Controller
public class MangeApproveController {
    @Resource
    private LandMangeApproveService landMangeApproveService;

    @Resource
    private DataRegPopulationService dataRegPopulationService;

    @RequestMapping(value = "/api/mangeApprove", method = RequestMethod.POST )
    @ResponseBody
    public Page<LandMangeApprove> getMangeApprovePage(@RequestParam(name = "proName", required = false) String proName,
                                                      @PageableDefault Pageable pageable)
    {
        Page<LandMangeApprove> list = landMangeApproveService.queryMangeApprove(proName,pageable);
        return list;
    }

    @RequestMapping(value = "/guest/alterColumn",method = RequestMethod.GET)
    public void alterColumnComment() {
        dataRegPopulationService.alterTemplate("id","id表头");
    }

    @RequestMapping(value = "/guest/redisGet", method = RequestMethod.GET )
    @ResponseBody
    public LandMangeApprove redisGetApprove(@RequestParam(name = "approveId", required = false)Integer approveId)
    {
       LandMangeApprove landMangeApprove = landMangeApproveService.findApproveById(approveId);
        return landMangeApprove;
    }

    @RequestMapping(value = "/api/getExcel",method = RequestMethod.GET)
    public void getExcel(HttpServletRequest servletRequest,HttpServletResponse response)
    {
        ServletOutputStream out = null;

        try {
            out = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, true);
        try {
            String fileName = new String(("UserInfo " + new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
                    .getBytes(), "UTF-8");

            Field field = SysMuserModel.class.getDeclaredField("name");
            ExcelProperty excelProperty = field.getAnnotation(ExcelProperty.class);
            InvocationHandler h = Proxy.getInvocationHandler(excelProperty);
            Field hfield = h.getClass().getDeclaredField("memberValues");
            hfield.setAccessible(true);
            Map memberValues = (Map)hfield.get(h);
            memberValues.put("value",new String[]{"反射"});
            hfield.setAccessible(false);

            Sheet sheet1 = new Sheet(1, 0, SysMuserModel.class);
            sheet1.setSheetName("用户表");
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition", "attachment;filename="+fileName+".xlsx");
            writer.write(landMangeApproveService.getListString(), sheet1);

            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            writer.finish();
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}
