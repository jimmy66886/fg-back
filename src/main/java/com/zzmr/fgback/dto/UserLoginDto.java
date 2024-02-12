package com.zzmr.fgback.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zzmr
 * @create 2024-01-31 15:29
 */
@Data
public class UserLoginDto implements Serializable {

    private String password;

    private String email;

    @ApiModelProperty(value = "验证码")
    private String code;

    private Boolean isRegister;

}
