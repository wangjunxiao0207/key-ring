package com.key.controller;

import com.key.common.ResponseData;
import com.key.param.PageParam;
import com.key.param.SearchLogParam;
import com.key.service.SysLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Api(tags="系统日志接口")
@Controller
@RequestMapping("/sys/log")
public class SysLogController {

    @Resource
    private SysLogService sysLogService;


//    @ApiOperation(value="数据恢复", notes = "根据日志中的值进行恢复")
//    @PostMapping("recover.json")
//    @ResponseBody
//    public ResponseData recover(@RequestParam("id") int id) {
//        sysLogService.recover(id);
//        return ResponseData.success();
//    }

    @ApiOperation(value="日志查询", notes = "日志查询")
    @GetMapping("/list_with_blobs.json")
    @ResponseBody
    public ResponseData listWithBLOBS(SearchLogParam param, PageParam pageParam) {
        return sysLogService.listWithBLOBS(param, pageParam);
    }
}


