package com.key.controller;

import com.key.common.ResponseData;
import com.key.param.KeyParam;
import com.key.param.PageParam;
import com.key.param.UserParam;
import com.key.pojo.Key;
import com.key.pojo.SysUser;
import com.key.service.KeyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags="账户接口")
@RestController
@RequestMapping("/key/")
public class KeyController {

    @Resource
    private KeyService keyService;

    @ApiOperation(value="添加账户",
            notes="tags不能为空，userId不能为null")
    @PostMapping("/add.json")
    @ResponseBody
    public ResponseData add(KeyParam param) {
        return keyService.add(param);
    }

    @ApiOperation(value="删除账户信息",
            notes="根据id删除账户信息,多个id中间使用逗号分割")
    @GetMapping("/delete.json")
    @ResponseBody
    public ResponseData delete(String ids) {
        return keyService.delete(ids);
    }


    @ApiOperation(value="修改账户信息",
            notes="修改账户信息")
    @PostMapping("/update.json")
    @ResponseBody
    public ResponseData update(KeyParam param) {
        return keyService.update(param);
    }


    @ApiOperation(value="获取账户信息",
            notes="根据id获取账户信息")
    @GetMapping("/get.json")
    @ResponseBody
    public ResponseData get(Integer id) {
        return keyService.get(id);
    }

    @ApiOperation(value="账户信息列表",
            notes="查询账户信息，返回列表，每页默认10条")
    @PostMapping("/list.json")
    @ResponseBody
    public ResponseData list(Key param, PageParam pageParam) {
        return keyService.list(param, pageParam);
    }

    @ApiOperation(value="查询自身账户信息",
            notes="登录用户通过tags查询自己的账户信息，返回列表，每页默认10条")
    @PostMapping("/list_user.json")
    @ResponseBody
    public ResponseData listByUser(String tags, PageParam pageParam) {
        return keyService.listByUser(tags, pageParam);
    }

}
