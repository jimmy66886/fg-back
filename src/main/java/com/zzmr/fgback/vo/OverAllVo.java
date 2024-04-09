package com.zzmr.fgback.vo;

import lombok.Data;

import java.util.List;

/**
 * @author zzmr
 * @create 2024-04-09 10:37
 */
@Data
public class OverAllVo {

    // 全站用户数量
    private Integer userNumber;

    // 全站菜谱数量
    private Integer recipeNumber;

    // 今日点赞top10
    private List<RecipeBasicVo> likeTop10;

    // 今日收藏top10
    private List<RecipeBasicVo> favoriteTop10;

}
