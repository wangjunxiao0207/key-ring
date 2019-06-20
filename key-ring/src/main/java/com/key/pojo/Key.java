package com.key.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
}