package com.key.util;

import javax.servlet.http.HttpServletRequest;

//获取客户端设备信息的工具
public class ClientUtil {
    // 服务器没有被nginx代理，获取客户端ip的方式
    public static String getIpNoProxy(HttpServletRequest req) {
        String remoteAddr = req.getRemoteAddr();
        return remoteAddr;
    }

    // 服务器被nginx代理了，获取客户端ip的方式
    public static String getIpWithNginx(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String getUserAgent(HttpServletRequest request) {
        String ua = request.getHeader("User-Agent");
        return ua;
    }

    public static boolean isMobileDevice(HttpServletRequest request) {
        boolean result = false;
        String ua = request.getHeader("User-Agent") ;
        if (ua != null) {
            if (ua.indexOf("iPhone") >-1 || ua.indexOf("iPad") >-1 || (ua.indexOf("ndroid") >-1)) {
                result = true;
            }
        }
        return result;
    }
}
