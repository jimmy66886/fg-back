package com.zzmr.fgback.dto;

import com.zzmr.fgback.bean.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zzmr
 * @create 2024-02-12 9:57
 */
@Data
public class UserRegisterDto extends User {

    @ApiModelProperty(value = "注册携带的验证码")
    private String code;
}
