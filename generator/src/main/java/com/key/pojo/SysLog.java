package com.key.pojo;

import java.util.Date;

public class SysLog {
    private Integer id;

    private Integer type;

    private String method;

    private String operator;

    private String ip;

    private Date createTime;

    private Date updateTime;

    public SysLog(Integer id, Integer type, String method, String operator, String ip, Date createTime, Date updateTime) {
        this.id = id;
        this.type = type;
        this.method = method;
        this.operator = operator;
        this.ip = ip;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public Integer getType() {
        return type;
    }

    public String getMethod() {
        return method;
    }

    public String getOperator() {
        return operator;
    }

    public String getIp() {
        return ip;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }
}