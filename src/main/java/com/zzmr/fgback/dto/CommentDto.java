package com.zzmr.fgback.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zzmr
 * @create 2024-01-17 9:15
 */
@Data
public class CommentDto {

    @ApiModelProperty(value = "菜谱id")
    private Long recipeId;

    @ApiModelProperty(value = "根评论id")
    private Long rootId;

    @ApiModelProperty(value = "页号")
    private Integer page;

    @ApiModelProperty(value = "页面大小")
    private Integer pageSize;

    @ApiModelProperty(value = "用户id")
    private Long userId;

}
