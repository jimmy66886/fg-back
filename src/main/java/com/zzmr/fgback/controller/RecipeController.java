package com.zzmr.fgback.controller;


import com.zzmr.fgback.bean.Recipe;
import com.zzmr.fgback.dto.AddRecipeDto;
import com.zzmr.fgback.dto.MaterialsDto;
import com.zzmr.fgback.dto.RecipeDto;
import com.zzmr.fgback.result.PageResult;
import com.zzmr.fgback.result.Result;
import com.zzmr.fgback.service.RecipeService;
import com.zzmr.fgback.util.RedisUtils;
import com.zzmr.fgback.vo.RecipeBasicVo;
import com.zzmr.fgback.vo.RecipeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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

    @Autowired
    private RedisUtils redisUtils;

    @ApiOperation("根据菜谱id查询菜谱以及菜谱对应用料")
    @GetMapping("/getById")
    public Result getByRecipeId(@RequestParam("recipeId") Long recipeId) {
        // 先查缓存
        RecipeVo recipeVo = redisUtils.getJsonToBean("recipe_" + recipeId, RecipeVo.class);
        if (recipeVo != null) {
            return Result.success(recipeVo);
        }

        // 缓存为空，则查数据库
        recipeVo = recipeService.getByRecipeId(recipeId);
        redisUtils.setBean("recipe_" + recipeId, recipeVo);
        return Result.success(recipeVo);
    }

    @ApiOperation("根据用料名称查询菜谱")
    @PostMapping("/getByMaterials")
    public Result getByMaterials(@RequestBody List<MaterialsDto> materialsDtoList) {
        List<RecipeBasicVo> recipeBasicVos = recipeService.getByMaterials(materialsDtoList);
        return Result.success(recipeBasicVos);
    }

    @ApiOperation("分页查询菜谱列表")
    @PostMapping("/getList")
    public PageResult getRecipeList(@RequestBody RecipeDto recipeDto) {
        PageResult pageResult = recipeService.getRecipeList(recipeDto);
        return pageResult;
    }

    /**
     * 涉及到菜谱步骤和用料的删除
     *
     * @param recipeDto
     * @return
     */
    @ApiOperation("根据菜谱id删除菜谱")
    @PostMapping("/removeById")
    public Result removeById(@RequestBody RecipeDto recipeDto) {
        recipeService.removeOne(recipeDto.getRecipeId());
        // 清除缓存
        redisUtils.cleanCache("recipe_" + recipeDto.getRecipeId());
        return Result.success();
    }

    /**
     * 涉及到菜谱步骤和用料的删除
     *
     * @param recipeIds
     * @return
     */
    @ApiOperation("批量删除菜谱")
    @PostMapping("/removeByIdList")
    public Result removeByIdList(@RequestBody List<Long> recipeIds) {
        recipeService.removeBatch(recipeIds);
        // 清除缓存
        redisUtils.cleanCache("recipe_*");
        return Result.success();
    }

    /**
     * 涉及到菜谱步骤和用料的新增
     *
     * @param addRecipeDto
     * @return
     */
    @ApiOperation("新增菜谱")
    @PostMapping("/add")
    public Result addRecipe(@RequestBody AddRecipeDto addRecipeDto) {
        recipeService.addRecipe(addRecipeDto);
        return Result.success();
    }

    /**
     * 涉及到菜谱的步骤和用料的修改
     *
     * @param addRecipeDto
     * @return
     */
    @ApiOperation("修改菜谱")
    @PostMapping("/update")
    public Result updateRecipe(@RequestBody AddRecipeDto addRecipeDto) {
        recipeService.updateOne(addRecipeDto);
        redisUtils.cleanCache("recipe_" + addRecipeDto.getRecipeId());
        return Result.success();
    }

}

