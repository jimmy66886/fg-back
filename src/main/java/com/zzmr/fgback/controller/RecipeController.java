package com.zzmr.fgback.controller;


import com.zzmr.fgback.dto.MaterialsDTO;
import com.zzmr.fgback.result.Result;
import com.zzmr.fgback.service.RecipeService;
import com.zzmr.fgback.vo.RecipeVO;
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
        RecipeVO recipeVO = recipeService.getByRecipeId(recipeId);
        return Result.success(recipeVO);
    }

    @ApiOperation("根据用料名称查询菜谱")
    @PostMapping("/getByMaterials")
    public Result getByMaterials(@RequestBody List<MaterialsDTO> materialsDTOS) {
        List<RecipeVO> recipeVOS = recipeService.getByMaterials(materialsDTOS);
        return Result.success(recipeVOS);
    }

}

