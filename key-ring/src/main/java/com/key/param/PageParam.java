package com.key.param;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class PageParam {
    @Min(value=1, message = "最小值为1")
    private int pageNum = 1;

    @Min(value=1, message="最小值为1")
    private int pageSize = 10;
}
