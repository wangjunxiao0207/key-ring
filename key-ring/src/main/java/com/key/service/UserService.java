package com.key.service;

import com.key.common.ResponseData;

public interface UserService {
    ResponseData doLogin(String principal, String password);

    ResponseData appDoLogin(String principal, String password);

    ResponseData refreshToken();
}
