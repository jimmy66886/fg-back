package com.zzmr.fgback.dto;

import lombok.Data;

/**
 * @author zzmr
 * @create 2024-01-26 10:19
 */
@Data
public class AddLikeDto {

    // 菜谱id
    private Long recipeId;

    // 评论id
    private Long commentId;

}
