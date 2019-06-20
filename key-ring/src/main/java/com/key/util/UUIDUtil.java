package com.key.util;

import java.util.Date;
import java.util.UUID;

public class UUIDUtil {
    public static String uuid() {
        String uuid = UUID.randomUUID().toString().replace("-","");
        return uuid;
    }

    public static String uuidWithTS() {
        String uuid = uuid();
        uuid = uuid + "_" + new Date().getTime();
        return uuid;
    }
    public static void main(String[] args) {
        System.out.println(uuidWithTS());
    }
}
