package com.zzmr.fgback.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author zzmr
 * @create 2024-04-03 18:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikeVo {

    private Long userId;

    private String nickName;

    private String avatarUrl;

    /**
     * 菜谱id
     */
    private Long recipeId;

    /**
     * 点赞内容
     */
    private String content;

    /**
     * 点赞类型
     */
    private String likeType;

    /**
     * 点赞时间
     */
    private LocalDateTime createTime;

}
