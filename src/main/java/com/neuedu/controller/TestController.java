package com.neuedu.controller;

import com.neuedu.dao.UserInfoMapper;
import com.neuedu.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * controller的测试类
 */
@RestController
public class TestController {


    //创建接口
    @Autowired
    UserInfoMapper userInfoMapper;
    //查询用户信息
    @RequestMapping(value = "/user/{userid}")
    public UserInfo getUser(@PathVariable Integer userid){
        return userInfoMapper.selectByPrimaryKey(userid);
    }
}
