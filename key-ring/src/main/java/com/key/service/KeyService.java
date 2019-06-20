package com.key.service;

import com.key.common.ResponseData;
import com.key.param.KeyParam;
import com.key.param.PageParam;
import com.key.pojo.Key;

public interface KeyService {
    ResponseData add(KeyParam param);

    ResponseData delete(String ids);

    ResponseData update(KeyParam param);

    ResponseData get(Integer id);

    ResponseData list(Key param, PageParam pageParam);

    ResponseData listByUser(String tags, PageParam pageParam);
}
