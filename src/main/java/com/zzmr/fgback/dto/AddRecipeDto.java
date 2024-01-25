package com.zzmr.fgback.dto;

import com.zzmr.fgback.bean.Materials;
import com.zzmr.fgback.bean.Recipe;
import com.zzmr.fgback.bean.RecipeStep;
import lombok.Data;

import java.util.List;

/**
 * @author zzmr
 * @create 2024-01-25 16:04
 * 添加菜谱的dto
 */
@Data
public class AddRecipeDto extends Recipe {

    private List<RecipeStep> recipeStepList;

    private List<Materials> materialsList;

}
