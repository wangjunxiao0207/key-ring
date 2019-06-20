package com.key.param;

import lombok.Data;

import java.util.Date;

@Data
public class SearchLogParam {

    private Integer type; // LogType

    private String operator;

    private Date fromTime;//yyyy-MM-dd HH:mm:ss

    private Date toTime;
}

