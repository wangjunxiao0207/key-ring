package com.key.pojo;

import java.util.Date;

public class Key {
    private Integer id;

    private String account;

    private String password;

    private String tags;

    private String salt;

    private Integer userId;

    private String userName;

    private String operator;

    private Date createTime;

    private Date updateTime;

    public Key(Integer id, String account, String password, String tags, String salt, Integer userId, String userName, String operator, Date createTime, Date updateTime) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.tags = tags;
        this.salt = salt;
        this.userId = userId;
        this.userName = userName;
        this.operator = operator;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public String getTags() {
        return tags;
    }

    public String getSalt() {
        return salt;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getOperator() {
        return operator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }
}