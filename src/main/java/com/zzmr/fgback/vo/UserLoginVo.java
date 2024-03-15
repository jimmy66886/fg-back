package com.zzmr.fgback.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

    private String email;

    private String token;

    private String openid;

    private String avatarUrl;

    private LocalDateTime createTime;

    private String bio;

}
