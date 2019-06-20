package com.key.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysLog {
    private Integer id;

    private Integer type;

    private String method;

    private String operator;

    private String ip;

    private Date createTime;

    private Date updateTime;
}