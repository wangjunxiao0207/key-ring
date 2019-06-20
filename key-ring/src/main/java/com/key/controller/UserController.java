package com.key.controller;

import com.key.common.RequestHolder;
import com.key.common.ResponseCode;
import com.key.common.ResponseData;
import com.key.common.constant.Constants;
import com.key.param.UserParam;
import com.key.pojo.SysUser;
import com.key.service.SysUserService;
import com.key.service.UserService;
import com.key.util.PropertiesUtil;
import io.swagger.annotations.*;
import org.omg.CORBA.Request;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Api(tags = "用户登录接口")
@Controller
@RequestMapping("/user/")
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private SysUserService sysUserService;

    @ApiOperation(value = "用户登录", notes = "可以使用用户名，手机号，邮箱进行登录")
    @PostMapping("do_login.json")
    @ResponseBody
    public ResponseData doLogin(String principal, String password) {
        ResponseData result = userService.doLogin(principal, password);
        if(result.isSuccess()) {
            HttpSession session = RequestHolder.getCurrentRequest().getSession();
            session.setAttribute(Constants.CURRENT_USER, result.getData());
        }
        return result;
    }

    @ApiOperation(value = "移动应用登录", notes = "移动应用登录接口，可以使用用户名，手机号，邮箱进行登录,成功返回token")
    @PostMapping("app_do_login.json")
    @ResponseBody
    public ResponseData appDoLogin(String principal, String password,  @ApiIgnore HttpServletResponse response) {
        ResponseData result = userService.appDoLogin(principal, password);
        if(result.isSuccess()) {
            String token = (String) result.getData();
            response.addHeader(PropertiesUtil.getProperty("jwt.header", "auth"), token);
        }
        return result;
    }

    @ApiOperation(value = "刷新JWT token", notes = "刷新JWT token,成功返回token")
    @PostMapping("refresh_token.json")
    @ResponseBody
    public ResponseData refreshToken() {
       return userService.refreshToken();
    }

    @ApiOperation("用户退出登录")
    @GetMapping("logout.json")
    @ResponseBody
    public ResponseData logout() {
        HttpSession session = RequestHolder.getCurrentRequest().getSession();
        session.removeAttribute(Constants.CURRENT_USER);
        return ResponseData.success();
    }

    @ApiOperation(value = "用户没有登录", notes = "用户没有登录，需要登录")
    @GetMapping("need_login.json")
    @ResponseBody
    public ResponseData needLogin() {
        return ResponseData.error(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
    }

    @ApiOperation(value = "用户信息", notes = "获取当前登录用户信息")
    @GetMapping("user_info.json")
    @ResponseBody
    public ResponseData userInfo() {
        SysUser currUser = RequestHolder.getCurrentUser();
        if(currUser == null) {
            return ResponseData.error(ResponseCode.NEED_LOGIN.getCode(), "请先登录");
        }
        return ResponseData.success(currUser);
    }

    @ApiOperation(value = "忘记密码", notes = "忘记密码状态下重置密码")
    @PostMapping(value="forget_reset_password.json")
    @ResponseBody
    public ResponseData forgetResetPassword(String principal, String newPassword, String forgetToken) {
        // todo
        return ResponseData.error("该接口正在开发中");
    }

    @ApiOperation(value="重置密码", notes="登录状态下重置密码")
    @PostMapping(value="reset_password.json")
    @ResponseBody
    public ResponseData resetPassword(String newPassword, String oldPassword) {
        SysUser user = RequestHolder.getCurrentUser();
        if(user == null) {
            return ResponseData.error("用户未登录");
        }
        // todo， 重置完密码，让用户重新登录
        return ResponseData.error("该接口正在开发中");
    }

    /**
     * 更改用户信息
     * 为了防止越权问题，采取了如下措施
     * userId从session中获取，而不是让前台传进来
     */
    @ApiOperation(value="修改个人信息", notes="用户修改个人信息")
    @PostMapping(value="update_infomation.json")
    @ResponseBody
    public ResponseData updateInfomation(UserParam user) {
        SysUser currentUser = RequestHolder.getCurrentUser();
        if(currentUser == null) {
            return ResponseData.error("用户未登陆");
        }
        user.setId(currentUser.getId());
        return sysUserService.update(user);
    }
}