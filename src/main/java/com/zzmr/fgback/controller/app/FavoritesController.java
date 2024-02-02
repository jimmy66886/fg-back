package com.zzmr.fgback.controller.app;


import com.zzmr.fgback.bean.Favorites;
import com.zzmr.fgback.result.Result;
import com.zzmr.fgback.service.FavoritesService;
import com.zzmr.fgback.vo.FavoritesVo;
import com.zzmr.fgback.vo.RecipeBasicVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zzmr
 * @since 2024-01-24
 */
@RestController
@CrossOrigin
@Api(tags = "收藏夹相关接口")
@Slf4j
@RequestMapping("/app/favorites")
public class FavoritesController {

    @Autowired
    private FavoritesService favoritesService;

    @GetMapping("/getAllFavorites")
    @ApiOperation(("获取用户所有的收藏夹"))
    public Result getAllFavorites() {
        List<FavoritesVo> favorites = favoritesService.getAllFavorites();
        return Result.success(favorites);
    }

    @PostMapping("/insert")
    @ApiOperation("添加一个收藏夹")
    public Result insert(@RequestBody Favorites favorites) {
        favoritesService.insert(favorites);
        return Result.success();
    }

    @PostMapping("/update")
    @ApiOperation("修改一个收藏夹")
    public Result update(@RequestBody Favorites favorites) {
        favoritesService.updateById(favorites);
        return Result.success();
    }

    @GetMapping("/getById")
    @ApiOperation("根据收藏夹id获取菜谱集合")
    public Result getById(@RequestParam Long favoritesId) {
        List<RecipeBasicVo> recipeBasicVoList = favoritesService.getRecipeList(favoritesId);
        return Result.success(recipeBasicVoList);
    }

}

