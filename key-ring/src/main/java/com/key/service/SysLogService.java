package com.key.service;

import com.key.common.ResponseData;
import com.key.param.PageParam;
import com.key.param.SearchLogParam;

public interface SysLogService {
    ResponseData listWithBLOBS(SearchLogParam param, PageParam pageParam);
    ResponseData add(Object oldValue, Object newValue, int type);
}
