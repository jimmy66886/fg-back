package com.zzmr.fgback.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zzmr
 * @create 2024-01-25 10:50
 */
@Data
public class RecipeDto {

    @ApiModelProperty(value = "标题模糊搜索")
    private String title;

    @ApiModelProperty(value = "菜谱id")
    private Long recipeId;

    @ApiModelProperty(value = "页号")
    private Integer page;

    @ApiModelProperty(value = "页面大小")
    private Integer pageSize;

    @ApiModelProperty(value = "排序方式")
    private String orderBy;

}
