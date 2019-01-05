package com.neuedu.service;

import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.UserInfo;
import org.springframework.stereotype.Service;

/**
 * user用户的业务层
 */
@Service
public interface UserInfoService {

    //根据用户名和密码验证用户登录
    ServerResponse isLoginSuccess(String username,String password);
    //注册
    ServerResponse isRegisterSuccess(UserInfo userInfo);
}
