package com.zzmr.fgback.vo;

import com.zzmr.fgback.bean.Materials;
import com.zzmr.fgback.bean.Recipe;
import com.zzmr.fgback.bean.RecipeStep;
import lombok.Data;

import java.util.List;

/**
 * @author zzmr
 * @create 2024-01-23 21:27
 */
@Data
public class RecipeVo extends Recipe {

    // Vo中多了一个用料集合
    private List<Materials> materialsList;

    // 还有一个步骤集合
    private List<RecipeStep> recipeStepList;

    // 点赞数
    private Integer likeNumber;

    // 收藏数
    private Integer favoriteNumber;

}
