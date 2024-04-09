package com.zzmr.fgback.vo;

import lombok.Data;

/**
 * @author zzmr
 * @create 2024-04-09 9:35
 */
@Data
public class TodayVo {

    // 今日新增菜谱数
    private Integer recipeAddition;

    // 今日用户数
    private Integer userAddition;

    // 今日点赞最多的菜谱
    private RecipeBasicVo likeRecipe;

    // 今日收藏最多
    private RecipeBasicVo favoriteRecipe;

}
