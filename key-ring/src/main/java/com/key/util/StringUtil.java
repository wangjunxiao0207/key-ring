package com.key.util;

import com.google.common.base.Splitter;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

public class StringUtil {
    /**
     * 分割字符串
     * @param rawStr
     * @param splitChar 默认,
     * @return
     */
    public static List<String> split(String rawStr, String splitChar) {
        if(StringUtils.isBlank(splitChar)) {
            Splitter splitter = Splitter.on(",").omitEmptyStrings().trimResults();
            return splitter.splitToList(rawStr);
        } else {
            Splitter splitter = Splitter.on(splitChar).omitEmptyStrings().trimResults();
            return splitter.splitToList(rawStr);
        }
    }

    public static List<Integer> splitToListInt(String str) {
        List<String> strList = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(str);
        return strList.stream().map(strItem -> Integer.parseInt(strItem)).collect(Collectors.toList());
    }

    public static String join(Object[] arr, String sperator) {
        return StringUtils.join(arr, sperator);
    }

    public static void main(String[] args) {
        String startDate = "2019.01.01";

        System.out.println(StringUtil.join(startDate.split("\\."), "-"));
    }

    public static boolean isBlank(String s) {
        return StringUtils.isBlank(s);
    }

    public static boolean isNotBlank(String s) {
        return StringUtils.isNotBlank(s);
    }

    public static boolean equals(String s1, String s2) {
        return StringUtils.equals(s1, s2);
    }
}
