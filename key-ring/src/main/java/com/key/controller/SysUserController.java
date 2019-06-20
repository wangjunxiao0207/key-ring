package com.key.controller;

import com.key.common.ResponseData;
import com.key.param.PageParam;
import com.key.param.UserParam;
import com.key.pojo.SysUser;
import com.key.service.SysUserService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "系统用户接口")
@RestController
@RequestMapping("/sys/user/")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    @ApiOperation(value="添加用户",
            notes="添加系统用户，用户名，手机号和邮箱不能重复")
    @PostMapping("/add.json")
    @ResponseBody
    public ResponseData add(UserParam param) {
        return sysUserService.add(param);
    }

    @ApiOperation(value="删除用户",
            notes="根据id删除系统用户,多个id中间使用逗号分割")
    @GetMapping("/delete.json")
    @ResponseBody
    public ResponseData delete(String ids) {
        return sysUserService.delete(ids);
    }


    @ApiOperation(value="修改用户",
            notes="修改用户信息")
    @PostMapping("/update.json")
    @ResponseBody
    public ResponseData update(UserParam param) {
        return sysUserService.update(param);
    }


    @ApiOperation(value="获取用户信息",
            notes="根据id获取用户信息")
    @GetMapping("/get.json")
    @ResponseBody
    public ResponseData get(Integer id) {
        return sysUserService.get(id);
    }

    @ApiOperation(value="用户列表",
            notes="根据用户信息查询用户，返回列表，每页默认10条")
    @PostMapping("/list.json")
    @ResponseBody
    public ResponseData list(SysUser param, PageParam pageParam) {
        return sysUserService.list(param, pageParam);
    }

}
