package com.neuedu.service.serviceImpl;

import com.neuedu.common.ServerResponse;
import com.neuedu.dao.UserInfoMapper;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public ServerResponse isLoginSuccess(String username, String password) {
        /**
         * 判断输入的用户名、密码是否为空
         */
        if(username == null || username.equals("")){
            return ServerResponse.responseIsError("用户名不能为空");
        }
        if(password == null || password.equals("")){
            return ServerResponse.responseIsError("密码不能为空");
        }
        /**
         * 判断用户名是否存在
         */
        int isExist = userInfoMapper.checkUsernameIsExist(username);
        //用户名不存在
        if(isExist == 0){
            return ServerResponse.responseIsError("该用户名不存在");
        }
        /**
         * 根据用户名和密码查询用户信息
         */
        UserInfo userInfo = userInfoMapper.loginByUsernameAndPassword(username, password);
        if(userInfo == null){
            return ServerResponse.responseIsError("用户名或密码有问题");
        }
        /**
         * 返回结果
         */
        return ServerResponse.responseIsSuccess(null,userInfo);
    }
}
