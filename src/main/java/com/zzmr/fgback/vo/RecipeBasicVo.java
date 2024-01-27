package com.zzmr.fgback.vo;

import com.zzmr.fgback.bean.Recipe;
import lombok.Data;

/**
 * @author zzmr
 * @create 2024-01-26 9:20
 * 菜谱基本Vo，用于封装用户根据用料搜索以及分页搜索菜谱等情况的返回结果
 */
@Data
public class RecipeBasicVo extends Recipe {
    // 昵称
    private String nickName;

    // 点赞数
    private Integer likeNumber;

    // 收藏数
    private Integer favoriteNumber;

}
