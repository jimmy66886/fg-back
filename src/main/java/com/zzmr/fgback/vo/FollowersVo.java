package com.zzmr.fgback.vo;

import lombok.Data;

/**
 * 关注列表Vo
 * @author zzmr
 * @create 2024-02-06 10:39
 */
@Data
public class FollowersVo {

    private Long userId;

    private String avatarUrl;

    private String nickName;

    private Boolean isFollowed;

}
