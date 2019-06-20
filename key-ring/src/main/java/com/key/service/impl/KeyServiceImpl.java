package com.key.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.key.common.RequestHolder;
import com.key.common.ResponseData;
import com.key.common.constant.LogType;
import com.key.dao.KeyMapper;
import com.key.dao.SysUserMapper;
import com.key.param.KeyParam;
import com.key.param.PageParam;
import com.key.pojo.Key;
import com.key.pojo.SysUser;
import com.key.service.KeyService;
import com.key.service.SysLogService;
import com.key.util.BeanUtil;
import com.key.util.BeanValidator;
import com.key.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class KeyServiceImpl implements KeyService {
    @Resource
    private KeyMapper keyMapper;
    @Resource
    private SysUserMapper userMapper;
    @Resource
    private SysLogService sysLogService;

    @Override
    public ResponseData add(KeyParam param) {
        BeanValidator.check(param);

        Key key = new Key();
        BeanUtil.copyProperties(param, key);
        SysUser sysUser = userMapper.selectByPrimaryKey(param.getUserId());
        if(sysUser == null) {
            return ResponseData.error("根据user_id找不到用户");
        }
        key.setUserName(sysUser.getName());
        SysUser currUser = RequestHolder.getCurrentUser();
        key.setOperator(currUser.getName());
        keyMapper.insertSelective(key);

        sysLogService.add(null, key, LogType.ADD);

        return ResponseData.success(key);
    }

    @Override
    public ResponseData delete(String ids) {
        if(StringUtil.isBlank(ids)) {
            return ResponseData.error("参数不能为空");
        }
        List<Integer> idArr = StringUtil.splitToListInt(ids);
        int result = keyMapper.batchDelete(idArr);

        sysLogService.add(null, ids, LogType.DELETE);

        return ResponseData.success();
    }

    @Override
    public ResponseData update(KeyParam param) {
        BeanValidator.check(param);

        Key before = keyMapper.selectByPrimaryKey(param.getId());
        if(before == null) {
            return ResponseData.error("更新的对象不存在");
        }
        Key after = new Key();
        BeanUtil.copyProperties(before, after);
        BeanUtil.copyProperties(param, after);
        keyMapper.updateByPrimaryKeySelective(after);

        sysLogService.add(before, after, LogType.UPDATE);

        return ResponseData.success();
    }

    @Override
    public ResponseData get(Integer id) {
        Key key = keyMapper.selectByPrimaryKey(id);
        if(key == null) {
            return ResponseData.error("获取的对象不存在");
        }

        sysLogService.add(null, id, LogType.READ);

        return ResponseData.success(key);
    }

    @Override
    public ResponseData list(Key param, PageParam pageParam) {
        PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        List<Key> list = keyMapper.list(param);
        PageInfo info = new PageInfo(list);

        sysLogService.add(null, param, LogType.READ);

        return ResponseData.success(info);
    }

    @Override
    public ResponseData listByUser(String tags, PageParam pageParam) {
        // "" 看作null
        if(StringUtil.isBlank(tags)) {
            tags = null;
        }
        SysUser currentUser = RequestHolder.getCurrentUser();
        Key query = new Key();
        query.setTags(tags);
        query.setUserId(currentUser.getId());

        sysLogService.add(null, tags, LogType.READ);

        return list(query, pageParam);
    }
}
