package com.zzmr.fgback.vo;

import lombok.Data;

/**
 * @author zzmr
 * @create 2024-03-31 22:08
 */
@Data
public class SearchUserVo {

    private Long userId;

    private String avatarUrl;

    private String nickName;

    private Boolean isFollowed;
}
