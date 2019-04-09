package com.lete.land.landdal.service;

import com.lete.land.landdal.entity.User;
import com.lete.land.landdal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WJ on 2019/3/27 0027
 */
@Service
public class UserService   {
    @Autowired
    private UserRepository userRepository;

    public List<String> getUser(){
        List<String> list = new ArrayList<>();
        list.add("wujie");
        return list;
    }

    public List<User> getList(){
      return userRepository.findAll();
      //return new ArrayList<>();
    }


    public String getString(){
        return "hello world";
    }
}
