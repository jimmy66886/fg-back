package com.zzmr.fgback.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zzmr
 * @create 2024-01-31 15:29
 */
@Data
public class UserLoginDto implements Serializable {

    private String account;

    private String password;

}
