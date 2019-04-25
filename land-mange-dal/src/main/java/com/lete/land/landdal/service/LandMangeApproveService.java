package com.lete.land.landdal.service;

import com.lete.land.landdal.entity.LandMangeApprove;
import com.lete.land.landdal.entity.SysMuser;
import com.lete.land.landdal.repository.LandMangeApproveRepository;
import com.lete.land.landdal.repository.SysMuserRepository;
import com.lete.land.landdal.util.RedisUtil;
import com.lete.land.landdal.vo.SysMuserModel;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by WJ on 2019/4/1 0001
 */
@Service
public class LandMangeApproveService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LandMangeApproveService.class);

    @Resource
    private LandMangeApproveRepository landMangeApproveRepository;

    @Resource
    private SysMuserRepository sysMuserRepository;

    @Resource
    private RedisUtil redisUtil;

    @Autowired
    private RedisTemplate redisTemplate;

    public LandMangeApprove findApproveById(Integer id) {
        String key = "approve_" + id;
        ValueOperations<String,LandMangeApprove> operations = redisTemplate.opsForValue();
        //缓存存在
        boolean hasKey = redisTemplate.hasKey(key);

        if(hasKey) {
            LandMangeApprove landMangeApprove = operations.get(key);
            LOGGER.info("Approve.findApproveById() : 从缓存中获取了城市 》》" + landMangeApprove.toString());
            return landMangeApprove;
        }

        //从数据库中获取城市信息
        LandMangeApprove landMangeApprove = landMangeApproveRepository.findById(id).get();
        //插入缓存
        operations.set(key,landMangeApprove,10, TimeUnit.SECONDS);
        LOGGER.info("Approve.findApproveById() : 城市插入缓存 》》" + landMangeApprove.toString());

        return landMangeApprove;
    }

    public Page<LandMangeApprove> queryMangeApprove(String proName,Pageable pageable) {

       Specification<LandMangeApprove> specification = new Specification<LandMangeApprove>() {
            @Override
            public Predicate toPredicate(Root<LandMangeApprove> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<>();

                if(!StringUtils.isEmpty(proName)){
                    Path<String> townName = root.get("proName");
                    predicateList.add(cb.like(townName,"%" + proName + "%"));
                }

                Predicate[] preArr = new Predicate[predicateList.size()];
                return cq.where(predicateList.toArray(preArr)).getRestriction();
            }
        };
        Page<LandMangeApprove> list = landMangeApproveRepository.findAll(specification,pageable);
        return list;
    }

  /*  public void getExcel(HttpServletRequest request, HttpServletResponse response) {
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
//            Field field = SysMuserModel.class.getDeclaredField("name");
//            ExcelProperty excelProperty = field.getAnnotation(ExcelProperty.class);
//            InvocationHandler h = Proxy.getInvocationHandler(excelProperty);
//            Field hfield = h.getClass().getDeclaredField("memberValues");
//            hfield.setAccessible(true);
//            Map memberValues = (Map)hfield.get(h);
//            memberValues.put("value","反射");

            Sheet sheet1 = new Sheet(1, 0, SysMuserModel.class);
            sheet1.setSheetName("用户表");
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition", "attachment;filename="+fileName+".xlsx");
            writer.write(getListString(), sheet1);

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
    }*/


    public List<SysMuserModel> getListString() {
        List<SysMuser> musers = sysMuserRepository.findAll();

        List<SysMuserModel> list = new ArrayList<SysMuserModel>();

        for(SysMuser sysMuser : musers){
            SysMuserModel sysMuserModel = new SysMuserModel();
            sysMuserModel.setId(sysMuser.getId());
            sysMuserModel.setAddress(sysMuser.getAddress());
           // sysMuserModel.setName(sysMuser.getName());
            sysMuserModel.setPassword(sysMuser.getPassword());
            sysMuserModel.setQq(sysMuser.getQq());
            sysMuserModel.setRoteType(sysMuser.getRoteType());
            sysMuserModel.setSex(sysMuser.getSex());
            // sysMuserModel.setSignTime(new SimpleDateFormat("yyyy-MM-DD HH:mm:ss").format(sysMuser.getSignTime()));

            list.add(sysMuserModel);
        }

        return list;
    }




}
