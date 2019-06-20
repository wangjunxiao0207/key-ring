package com.key.util;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {
    public static String getRealPath(HttpServletRequest request, String path) {
        return request.getServletContext().getRealPath(path);
    }
}
