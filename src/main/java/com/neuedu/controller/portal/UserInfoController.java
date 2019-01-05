package com.neuedu.controller.portal;

import com.neuedu.common.ResponseCord;
import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * user用户的控制层
 */
@RestController
@RequestMapping(value = "/user")
public class UserInfoController {

    @Autowired
    UserInfoService userInfoService;
    //登录
    @RequestMapping(value = "/login.do")
    public ServerResponse isLoginSuccess(HttpSession session,String username, String password){
        ServerResponse loginSuccess = userInfoService.isLoginSuccess(username, password);
        if(loginSuccess.isSuccess()){//登录成功
            UserInfo userInfo = (UserInfo) loginSuccess.getDate();
            //保存用户信息
            session.setAttribute(ResponseCord.CURRENTUSER,userInfo);
        }
        return loginSuccess;
    }
    //注册
    @RequestMapping(value = "/register.do")
    public ServerResponse isRegisterSuccess(HttpSession session,UserInfo userInfo){
        ServerResponse registerSuccess = userInfoService.isRegisterSuccess(userInfo);
        return registerSuccess;
    }
}
