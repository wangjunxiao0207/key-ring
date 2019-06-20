package com.key.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Preconditions;
import com.key.common.RequestHolder;
import com.key.common.ResponseData;
import com.key.common.exception.ParamException;
import com.key.dao.SysLogMapper;
import com.key.dao.SysUserMapper;
import com.key.param.PageParam;
import com.key.param.SearchLogParam;
import com.key.pojo.SysLog;
import com.key.pojo.SysLogWithBLOBs;
import com.key.pojo.SysUser;
import com.key.service.SysLogService;
import com.key.util.BeanValidator;
import com.key.util.ClientUtil;
import com.key.util.IpUtil;
import com.key.util.JsonMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class SysLogServiceImpl implements SysLogService {
    @Resource
    private SysLogMapper sysLogMapper;

    @Override
    public ResponseData listWithBLOBS(SearchLogParam param, PageParam pageParam) {
        PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        List<SysLogWithBLOBs> list = sysLogMapper.listWithBLOBS(param);
        PageInfo pageInfo = new PageInfo(list);
        return ResponseData.success(pageInfo);
    }

    @Override
    public ResponseData add(Object oldValue, Object newValue, int type) {
        HttpServletRequest request = RequestHolder.getCurrentRequest();
        SysUser user = RequestHolder.getCurrentUser();
        String servletPath = request.getServletPath();

        SysLogWithBLOBs log = new SysLogWithBLOBs();
        log.setType(type);
        log.setMethod(servletPath);
        log.setOldValue(JsonMapper.obj2String(oldValue));
        log.setNewValue(JsonMapper.obj2String(newValue));
        log.setOperator(user.getName());
        log.setIp(ClientUtil.getIpWithNginx(request));

        sysLogMapper.insertSelective(log);
        return ResponseData.success();
    }
}
