package com.lete.land.landcontroller.endPoint;

import com.lete.land.landdal.Result;
import com.lete.land.landdal.VueLoginInfoVo;
import com.lete.land.landdal.entity.User;
import com.lete.land.landdal.service.ResultFactory;
import com.lete.land.landdal.repository.UserRepository;
import com.lete.land.landdal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * Created by WJ on 2019/3/27 0027
 */
@Controller
@RequestMapping
public class HelloController {
   @Autowired
    private UserService userService;

   /* @RequestMapping(value = "/api/login", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result login(@Valid @RequestBody VueLoginInfoVo loginInfoVo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String message = String.format("登陆失败，详细信息[%s].", bindingResult.getFieldError().getDefaultMessage());
            return ResultFactory.buildFailResult(message);
        }

        if (!Objects.equals("wujie", loginInfoVo.getUsername()) || !Objects.equals("123456", loginInfoVo.getPassword())) {
            String message = String.format("登陆失败，详细信息[用户名、密码信息不正确]。");
            return ResultFactory.buildFailResult(message);
        }
        return ResultFactory.buildSuccessResult("登陆成功。");
    }
    @RequestMapping(value = "/api/hello", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Result hello(){
        Result result = new Result(11,"你刚好",11);
        return result;
    }

    @RequestMapping("/api/getUsers")
    @ResponseBody
    public List<User> getUsers(){
        List<User> users = userService.getList();
        return users;
    }*/
}
