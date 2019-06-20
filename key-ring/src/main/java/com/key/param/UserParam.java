package com.key.param;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class UserParam {
    private Integer id;

    @NotBlank(message="用户名不可以为空")
    @Length(min=0, max=20, message="用户名长度需要在20个字以内")
    private String name;

    @Length(min=11, max=11, message="手机号必须11位")
    private String telephone;

    @Length(min=5, max=50, message = "邮箱需要在5-50个字以内")
    private String mail;

    private String password;

    private Integer deptId;

    @NotNull(message="必须指定用户的状态")
    @Min(value=0, message="用户状态不合法")
    @Max(value=2, message="用户状态不合法")
    private Integer status;

    @Length(min=0, max=200, message="备注长度需要在200个字以内")
    private String remark;
}
