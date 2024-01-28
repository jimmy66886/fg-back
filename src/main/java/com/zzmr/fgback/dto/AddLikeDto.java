package com.zzmr.fgback.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zzmr
 * @create 2024-01-26 10:19
 */
@Data
public class AddLikeDto {

    @ApiModelProperty(value = "菜谱id")
    private Long recipeId;

    @ApiModelProperty(value = "评论id")
    private Long commentId;

}
