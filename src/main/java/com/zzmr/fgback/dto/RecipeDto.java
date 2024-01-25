package com.zzmr.fgback.dto;

import lombok.Data;

/**
 * @author zzmr
 * @create 2024-01-25 10:50
 */
@Data
public class RecipeDto {

    /**
     * 标题模糊搜索
     */
    private String title;

    /**
     * 菜谱id
     */
    private Long recipeId;

    private Integer page;

    private Integer pageSize;

}
