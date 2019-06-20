package com.key.param;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class KeyParam {
    private Integer id;

    private String account;

    private String password;

    @NotBlank(message="不能为空")
    private String tags;

    private String salt;

    @NotNull(message="用户id不能为空")
    private Integer userId;

}
