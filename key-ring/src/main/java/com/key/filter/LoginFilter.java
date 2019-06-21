package com.key.filter;

import com.key.common.LoginJWT;
import com.key.common.RequestHolder;
import com.key.common.constant.Constants;
import com.key.pojo.SysUser;
import com.key.util.ClientUtil;
import com.key.util.PropertiesUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     *
     * App使用JWT进行身份识别.
     * 网页使用Session进行身份识别。
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        RequestHolder.add(request);

        String servletPath = request.getServletPath();
        // 如果是登录请求则放行
        if(servletPath.indexOf("login") > 0) {
            chain.doFilter(servletRequest, servletResponse);
            return;
        }

        SysUser currUser = null;
        // 如果是移动设备，使用JWT进行身份识别
        if (ClientUtil.isMobileDevice(request)) {
            // jwt token
            String authToken = request.getHeader(PropertiesUtil.getProperty("jwt.header", "auth"));
            // 如果没有authToken,则提示需要登录
            if(authToken != null) {
                // token中没有user（可能是token过期啦）,返回null
                currUser = LoginJWT.unsign(authToken, SysUser.class);
            }
        }
        // 使用session进行身份识别
        else {
            currUser = (SysUser) request.getSession().getAttribute(Constants.CURRENT_USER);
        }

        // 没有登录用户
        if(currUser == null) {
            request.getRequestDispatcher("/user/need_login.json").forward(servletRequest, servletResponse);
            // 对于跨域的预请求，不运行重定向
//            response.sendRedirect("/user/need_login.json");
            return;
        }

        RequestHolder.add(currUser);
        chain.doFilter(servletRequest, servletResponse);
        RequestHolder.remove();
    }

    @Override
    public void destroy() {
    }
}
