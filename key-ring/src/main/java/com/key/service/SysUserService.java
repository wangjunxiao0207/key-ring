package com.key.service;

import com.key.common.ResponseData;
import com.key.param.PageParam;
import com.key.param.UserParam;
import com.key.pojo.SysUser;

public interface SysUserService {
    ResponseData add(UserParam param);

    ResponseData delete(String ids);

    ResponseData update(UserParam param);

    ResponseData get(Integer id);

    ResponseData list(SysUser param, PageParam pageParam);
}
