package com.lete.land.landcontroller.endPoint;

import com.alibaba.fastjson.JSONObject;
import com.lete.land.landdal.Result;
import com.lete.land.landdal.entity.User;
import com.lete.land.landdal.service.LoginService;
import com.lete.land.landdal.service.ResultFactory;
import com.lete.land.landdal.vo.LoginInfo;
import com.lete.land.landdal.vo.UserVo;
import com.lete.land.landsecurity.config.MyShiroRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Administrator on 2017/12/11.
 */
@Controller
public class ShiroController {
   @Resource
   private LoginService loginService;
    /**
     * 登录方法
     * @param userInfo
     * @return
     */
    @RequestMapping(value = "/api/ajaxLogin", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result ajaxLogin(@RequestBody User userInfo) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userInfo.getUsername(), userInfo.getPassword());

        try {
            subject.login(token);
            LoginInfo loginInfo = loginService.getLoginInfo(userInfo.getUsername());
            return ResultFactory.buildSuccessResult(loginInfo);
        } catch (IncorrectCredentialsException e) {
            return ResultFactory.buildFailResult("密码错误");
        } catch (LockedAccountException e) {
            return ResultFactory.buildFailResult("登录失败，该用户已被冻结");
        } catch (AuthenticationException e) {
            return ResultFactory.buildFailResult("该用户不存在");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResultFactory.buildFailResult("登陆失败");
    }

    /**
     * 未登录，shiro应重定向到登录界面，此处返回未登录状态信息由前端控制跳转页面
     * @return
     */
    @RequestMapping(value = "/unauth")
    @ResponseBody
    public Object unauth() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", "1000000");
        map.put("msg", "未登录");
        return map;
    }
}
