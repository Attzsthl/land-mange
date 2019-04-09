package com.lete.land.landdal;

import com.lete.land.landdal.entity.SysRole;
import com.lete.land.landdal.service.SysRoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LandDalApplicationTests {
   @Resource
   private SysRoleService sysRoleService;
    @Test
    public void contextLoads() {

    }

}
