package com.key.util;

import org.apache.commons.lang.exception.ExceptionUtils;

public class ExceptionUtil {
    /**
     * 获取异常的完整堆栈信息
     * @param t
     * @return
     */
    public static String printStackTraceToString(Throwable t) {
        return ExceptionUtils.getFullStackTrace(t);
    }
}
