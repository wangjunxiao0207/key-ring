package com.key.filter;

import com.key.common.LoginJWT;
import com.key.common.RequestHolder;
import com.key.common.constant.Constants;
import com.key.pojo.SysUser;
import com.key.util.ClientUtil;
import com.key.util.StringUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 解决跨域问题
 */
@Slf4j
public class CorsFilter implements Filter {
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

        String origin = request.getHeader("Origin");
        if(!StringUtil.isBlank(origin)) {
            // 支持所有域名访问，不带Cookie的跨域请求头字段设置
            response.addHeader("Access-Control-Allow-Origin", origin);
        }

        response.addHeader("Access-Control-Allow-Methods", "*");

        // 非简单请求通过预检命令请求头字段设置
        String headers = request.getHeader("Access-Control-Request-Headers");
        if(!StringUtil.isBlank(headers)) {
            // 支持所有的自定义请求头
            response.addHeader("Access-Control-Allow-Headers", headers);
        }
        response.addHeader("Access-Control-Max-Age", "3600");
        // 带Cookie的跨域请求头字段设置
        // Access-Control-Allow-Origin字段必须是全匹配，不能使用*
        response.addHeader("Access-Control-Allow-Credentials", "true");
        chain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {
    }
}
