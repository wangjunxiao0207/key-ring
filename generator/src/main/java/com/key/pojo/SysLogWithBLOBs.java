package com.key.pojo;

import java.util.Date;

public class SysLogWithBLOBs extends SysLog {
    private String oldValue;

    private String newValue;

    public SysLogWithBLOBs(Integer id, Integer type, String method, String operator, String ip, Date createTime, Date updateTime, String oldValue, String newValue) {
        super(id, type, method, operator, ip, createTime, updateTime);
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public String getOldValue() {
        return oldValue;
    }

    public String getNewValue() {
        return newValue;
    }
}