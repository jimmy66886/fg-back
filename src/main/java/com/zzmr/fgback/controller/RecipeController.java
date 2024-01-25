package com.zzmr.fgback.controller;


import com.zzmr.fgback.bean.Recipe;
import com.zzmr.fgback.dto.AddRecipeDto;
import com.zzmr.fgback.dto.MaterialsDto;
import com.zzmr.fgback.dto.RecipeDto;
import com.zzmr.fgback.result.PageResult;
import com.zzmr.fgback.result.Result;
import com.zzmr.fgback.service.RecipeService;
import com.zzmr.fgback.vo.RecipeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zzmr
 * @since 2024-01-13
 */
@RestController
@RequestMapping("/recipe")
@CrossOrigin
@Api(tags = "菜谱相关接口")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @ApiOperation("根据菜谱id查询菜谱以及菜谱对应用料")
    @GetMapping("/getByRecipeId")
    public Result getByRecipeId(@RequestParam("recipeId") Long recipeId) {
        RecipeVo recipeVo = recipeService.getByRecipeId(recipeId);
        return Result.success(recipeVo);
    }

    @ApiOperation("根据用料名称查询菜谱")
    @PostMapping("/getByMaterials")
    public Result getByMaterials(@RequestBody List<MaterialsDto> materialsDtoList) {
        List<Recipe> recipes = recipeService.getByMaterials(materialsDtoList);
        return Result.success(recipes);
    }

    @ApiOperation("分页查询菜谱列表")
    @PostMapping("/getRecipeList")
    public PageResult getRecipeList(@RequestBody RecipeDto recipeDto) {
        PageResult pageResult = recipeService.getRecipeList(recipeDto);
        return pageResult;
    }

    /**
     * 涉及到菜谱步骤和用料的删除
     * @param recipeDto
     * @return
     */
    @ApiOperation("根据菜谱id删除菜谱")
    @PostMapping("/removeById")
    public Result removeById(@RequestBody RecipeDto recipeDto) {
        recipeService.removeOne(recipeDto.getRecipeId());
        return Result.success();
    }

    /**
     * 涉及到菜谱步骤和用料的删除
     * @param recipeIds
     * @return
     */
    @ApiOperation("批量删除菜谱")
    @PostMapping("/removeByIdList")
    public Result removeByIdList(@RequestBody List<Long> recipeIds) {
        recipeService.removeBatch(recipeIds);
        return Result.success();
    }

    /**
     * 涉及到菜谱步骤和用料的新增
     * @param addRecipeDto
     * @return
     */
    @ApiOperation("新增菜谱")
    @PostMapping("/addRecipe")
    public Result addRecipe(@RequestBody AddRecipeDto addRecipeDto){
        recipeService.addRecipe(addRecipeDto);
        return Result.success();
    }

    /**
     * 涉及到菜谱的步骤和用料的修改
     * @param addRecipeDto
     * @return
     */
    @ApiOperation("修改菜谱")
    @PostMapping("/updateRecipe")
    public Result updateRecipe(@RequestBody AddRecipeDto addRecipeDto){
        recipeService.updateOne(addRecipeDto);
        return Result.success();
    }

}

