package com.zzmr.fgback.controller.app;


import com.zzmr.fgback.bean.Recipe;
import com.zzmr.fgback.constant.ViewConstant;
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
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/app/recipe")
@CrossOrigin
@Api(tags = "菜谱相关接口")
@Slf4j
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private RedisUtils redisUtils;

    @ApiOperation("根据菜谱id查询菜谱以及菜谱对应用料")
    @GetMapping("/getById")
    public Result getByRecipeId(@RequestParam("recipeId") Long recipeId) {

        // 访问量加一
        redisUtils.increment("recipeView:" + recipeId, ViewConstant.AddOne);

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

   /* @ApiOperation("根据用料名称查询菜谱")
    @PostMapping("/getByMaterials")
    public Result getByMaterials(@RequestBody List<MaterialsDto> materialsDtoList) {
        List<RecipeBasicVo> recipeBasicVos = recipeService.getByMaterials(materialsDtoList);
        return Result.success(recipeBasicVos);
    }*/

    @ApiOperation("根据用料名称查询菜谱")
    @PostMapping("/getByMaterials")
    public Result getByMaterialsByArr(@RequestBody List<String> materialList) {
        List<RecipeBasicVo> recipeBasicVos = recipeService.getByMaterialsByArr(materialList);
        return Result.success(recipeBasicVos);
    }

    /**
     * 分页查询菜谱列表要大改
     * 1. 搜索条件变成 标题+分类 的模糊搜索，也就是说，如果查询条件为早餐，那么就要把早餐这个分类下的菜谱以及标题中含有早餐的菜谱都给查出来
     * 这个接口没办法直接添加分类的搜索方法，思路是在Service中调用Category的查询方法，然后将结果用一个Set来去重整合
     *
     * @param recipeDto
     * @return
     */
    @ApiOperation("分页查询菜谱列表")
    @PostMapping("/getList")
    public PageResult getRecipeList(@RequestBody RecipeDto recipeDto) {
        PageResult pageResult = recipeService.getRecipeList(recipeDto);
        return pageResult;
    }

    @ApiOperation("查询用户所有的菜谱")
    @GetMapping("/getByUserId")
    public Result getRecipeListByUser() {
        List<RecipeBasicVo> recipeBasicVos = recipeService.getByUserId();
        return Result.success(recipeBasicVos);
    }

    @ApiOperation("首页展示菜谱列表")
    @PostMapping("/getNormalList")
    public PageResult getNormalList(@RequestBody RecipeDto recipeDto) {
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

    @GetMapping("/test")
    public Result test() {
        List<Recipe> list = recipeService.list();
        return Result.success(list);
    }

}

