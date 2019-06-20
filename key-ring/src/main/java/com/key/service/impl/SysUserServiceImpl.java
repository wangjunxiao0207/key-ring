package com.key.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.key.common.RequestHolder;
import com.key.common.ResponseData;
import com.key.common.constant.LogType;
import com.key.dao.SysUserMapper;
import com.key.param.PageParam;
import com.key.param.UserParam;
import com.key.pojo.SysUser;
import com.key.service.SysLogService;
import com.key.service.SysUserService;
import com.key.util.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SysLogService sysLogService;

    public ResponseData add(UserParam param) {
        BeanValidator.check(param);

        if(checkUserName(param.getName(), null).isSuccess()) {
            return ResponseData.error("用户名已被占用");
        }
        if(checkTelephoneExist(param.getTelephone(), null).isSuccess()) {
            return ResponseData.error("电话已被占用");
        }
        if(checkEmailExist(param.getMail(), null).isSuccess()) {
            return ResponseData.error("邮箱已被占用");
        }

        String encryptedPassword = MD5Util.MD5EncodeUtf8(param.getPassword());

        SysUser user = new SysUser();
        BeanUtil.copyProperties(param, user);
        user.setPassword(encryptedPassword);
        SysUser currUser = RequestHolder.getCurrentUser();
        user.setOperator(currUser.getName());

        sysUserMapper.insertSelective(user);

        sysLogService.add(null, user, LogType.ADD);

        return ResponseData.success(user);
    }


    public ResponseData delete(String ids) {
        if(StringUtil.isBlank(ids)) {
            return ResponseData.error("参数不能为空");
        }
        List<Integer> idArr = StringUtil.splitToListInt(ids);
        int result = sysUserMapper.batchDelete(idArr);

        sysLogService.add(null, ids, LogType.DELETE);

        return ResponseData.success();
    }

    @Override
    public ResponseData update(UserParam param) {
        BeanValidator.check(param);

        SysUser before = sysUserMapper.selectByPrimaryKey(param.getId());
        if(before == null) {
            return ResponseData.error("更新的对象不存在");
        }
        SysUser after = new SysUser();
        BeanUtil.copyProperties(before, after);
        BeanUtil.copyProperties(param, after);
        sysUserMapper.updateByPrimaryKeySelective(after);

        sysLogService.add(before, after, LogType.UPDATE);

        return ResponseData.success();
    }

    @Override
    public ResponseData get(Integer id) {
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(id);
        if(sysUser == null) {
            return ResponseData.error("获取的对象不存在");
        }

        sysLogService.add(null, id, LogType.READ);

        return ResponseData.success(sysUser);
    }

    @Override
    public ResponseData list(SysUser param, PageParam pageParam) {
        PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        List<SysUser> list = sysUserMapper.list(param);
        PageInfo info = new PageInfo(list);

        sysLogService.add(null, param, LogType.READ);

        return ResponseData.success(info);
    }

    public ResponseData checkUserName(String name, Integer id) {
        SysUser user = sysUserMapper.checkUserName(name, id);
        if(user == null) {
            return ResponseData.error();
        }
        return ResponseData.success(user);
    }

    public ResponseData checkTelephoneExist(String phone, Integer id) {
        SysUser user = sysUserMapper.checkTelephone(phone, id);
        if(user == null) {
            return ResponseData.error();
        }
        return ResponseData.success(user);
    }

    public ResponseData checkEmailExist(String email, Integer id) {
        SysUser user = sysUserMapper.checkEmailExist(email, id);
        if(user == null) {
            return ResponseData.error();
        }
        return ResponseData.success(user);
    }
}
