package com.key.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class SysLogWithBLOBs extends SysLog {
    private String oldValue;

    private String newValue;

    public SysLogWithBLOBs(Integer id, Integer type, String method, String operator, String ip, Date createTime, Date updateTime, String oldValue, String newValue) {
        super(id, type, method, operator, ip, createTime, updateTime);
        this.oldValue = oldValue;
        this.newValue = newValue;
    }
}