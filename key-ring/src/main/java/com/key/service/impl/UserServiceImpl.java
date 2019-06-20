package com.key.service.impl;

import com.auth0.jwt.JWT;
import com.key.common.LoginJWT;
import com.key.common.RequestHolder;
import com.key.common.ResponseData;
import com.key.dao.SysUserMapper;
import com.key.pojo.SysUser;
import com.key.service.UserService;
import com.key.util.MD5Util;
import com.key.util.PropertiesUtil;
import com.key.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public ResponseData doLogin(String principal, String password) {
        if(StringUtil.isBlank(principal) || StringUtil.isBlank(password)) {
            return ResponseData.error("用户名或者密码不能为空");
        }

        SysUser sysUser = sysUserMapper.getUserByName(principal);
        if(sysUser == null) {
            sysUser = sysUserMapper.getUserByPhone(principal);
            if(sysUser == null) {
                sysUser = sysUserMapper.getUserByMail(principal);
                if(sysUser == null) {
                    return ResponseData.error("用户名/手机号/邮箱不存在");
                }
            }
        }

        String encryptedPassword = MD5Util.MD5EncodeUtf8(password);
        if(StringUtil.equals(encryptedPassword, sysUser.getPassword())) {
            return ResponseData.success("登录成功",sysUser);
        }

        return ResponseData.error("密码错误");
    }

    @Override
    public ResponseData appDoLogin(String principal, String password) {
        ResponseData responseData = doLogin(principal, password);
        if(responseData.isSuccess()) {
            SysUser user = (SysUser) responseData.getData();
            String token = LoginJWT.sign(user, 0);
            return ResponseData.success(token);
        }

        return responseData;
    }

    @Override
    public ResponseData refreshToken() {
        SysUser currentUser = RequestHolder.getCurrentUser();
        String token = LoginJWT.sign(currentUser, 0);
        return ResponseData.success(token);
    }
}
