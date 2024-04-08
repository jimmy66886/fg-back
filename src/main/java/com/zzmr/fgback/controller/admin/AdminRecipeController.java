package com.zzmr.fgback.controller.admin;

import com.zzmr.fgback.bean.Recipe;
import com.zzmr.fgback.dto.RecipeDto;
import com.zzmr.fgback.result.PageResult;
import com.zzmr.fgback.result.Result;
import com.zzmr.fgback.service.RecipeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zzmr
 * @create 2024-04-06 18:13
 */
@RestController
@CrossOrigin
@Slf4j
@Api(tags = "管理员相关接口")
@RequestMapping("/admin/recipe")
public class AdminRecipeController {

    @Autowired
    private RecipeService recipeService;

    @ApiOperation("分页查询菜谱列表")
    @PostMapping("/getList")
    public PageResult getRecipeList(@RequestBody RecipeDto recipeDto) {
        PageResult pageResult = recipeService.getRecipeListAdmin(recipeDto);
        return pageResult;
    }

    @ApiOperation("修改菜谱状态")
    @PostMapping("/changeStatus")
    public Result changeStatus(@RequestBody Recipe recipe){
        recipeService.changeStatus(recipe);
        return Result.success();
    }

}
