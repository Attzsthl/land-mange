package com.lete.land.landdal.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.lete.land.landdal.entity.SysMuser;

import java.util.ArrayList;
import java.util.List;

//第三种方法
/*@Component*/
public class ExcelListener extends AnalysisEventListener {
    //第一种注入方法，没用
    //WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
   // SysMuserService sysMuserService = (SysMuserService)context.getBean("sysMuserService");
    //第二种方法，没用
  //  SysMuserService sysMuserService = (SysMuserService) SpringUtils.getBean("sysMuserService");


    private List<Object> datas = new ArrayList<Object>();
    @Override
    public void invoke(Object object, AnalysisContext context) {
        System.out.println("当前行:" + context.getCurrentRowNum());
        System.out.println(object);
        datas.add(object);//数据存储到list,供批量处理，或后续自己业务逻辑处理

        /*if(context.getCurrentRowNum() != 0){
            doSomeThing(object,muserList);//根据自己业务做处理
        }*/
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }
    //用一个List<SysMuser>来存储
    //将每一个Object的值映射为SysMuser
    //这样写不好，每一次invoke都要走一下这个方法，效率低
    private  void doSomeThing(Object object,List<SysMuser> muserList){
    }

    public List<Object> getDatas(){
        return datas;
    }

    public void setDatas(List<Object> datas){
        this.datas = datas;
    }
}
