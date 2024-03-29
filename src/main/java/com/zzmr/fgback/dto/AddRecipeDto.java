package com.zzmr.fgback.dto;

import com.zzmr.fgback.bean.Materials;
import com.zzmr.fgback.bean.Recipe;
import com.zzmr.fgback.bean.RecipeStep;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author zzmr
 * @create 2024-01-25 16:04
 * 添加菜谱的dto
 */
@Data
public class AddRecipeDto extends Recipe {

    @ApiModelProperty(value = "菜谱步骤集合")
    private List<RecipeStep> recipeStepList;

    @ApiModelProperty(value = "菜谱用料集合")
    private List<Materials> materialsList;

    @ApiModelProperty(value = "分类名集合")
    private List<String> categoryList;

}
