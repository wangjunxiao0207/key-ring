package com.key.common;

public enum ResponseCode {
    SUCCESS(0,"SUCCESS"),
    ERROR(100, "ERROR"),
    NEED_LOGIN(200,"NEED_LOGIN"),
    ILLEGAL_ARGUMENT(300, "ILLEGAL_ARGUMENT");

    private final int code;
    private final String desc;

    private ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
