package com.neuedu.service.serviceImpl;

import com.neuedu.common.ResponseCord;
import com.neuedu.common.ServerResponse;
import com.neuedu.dao.UserInfoMapper;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.UserInfoService;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
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
            return ServerResponse.responseIsError("用户名或密码错误");
        }
        /**
         * 返回结果
         */
        return ServerResponse.responseIsSuccess(null,userInfo);
    }

    @Override
    public ServerResponse isRegisterSuccess(UserInfo userInfo) {
        //验证参数是否存在
        if(userInfo == null){
            return ServerResponse.responseIsError("参数不能为空");
        }
        //验证用户名是否存在
        int isExist = userInfoMapper.checkUsernameIsExist(userInfo.getUsername());
        //用户名不存在
        if(isExist > 0){
            return ServerResponse.responseIsError("该用户名已存在");
        }
        //验证邮箱是否存在
        int emailIsExist = userInfoMapper.checkEmailIsExist(userInfo.getEmail());
        if(emailIsExist > 0){
            return ServerResponse.responseIsError("该邮箱已存在");
        }
        //验证注册是否成功
        userInfo.setRole(ResponseCord.UserEnum.USER_COMMON.getCode());
        int insert = userInfoMapper.insert(userInfo);
        if(insert == 0){
            return ServerResponse.responseIsError("注册失败");
        }
        return ServerResponse.responseIsSuccess("注册成功");
    }
}
