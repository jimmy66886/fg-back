package com.zzmr.fgback.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zzmr
 * @create 2024-01-31 15:38
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginVo {

    private Long userId;

    private String nickName;

    private String account;

    private String token;

}
