package com.lete.land.landdal;

import com.lete.land.landdal.repository.DataRegPopulationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LandDalApplicationTests {
   @Resource
   private DataRegPopulationRepository dataRegPopulationRepository;
    @Test
    public void contextLoads() {
//        List<Object[]> list = dataRegPopulationRepository.getTemplate();
//        System.out.println(list);
    }

}
