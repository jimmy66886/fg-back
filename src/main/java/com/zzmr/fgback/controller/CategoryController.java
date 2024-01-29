package com.zzmr.fgback.controller;


import com.zzmr.fgback.bean.Category;
import com.zzmr.fgback.bean.Recipe;
import com.zzmr.fgback.dto.PageCategoryDto;
import com.zzmr.fgback.result.PageResult;
import com.zzmr.fgback.result.Result;
import com.zzmr.fgback.service.CategoryService;
import com.zzmr.fgback.util.RedisUtils;
import com.zzmr.fgback.vo.RecipeBasicVo;
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
@RequestMapping("/category")
@Api(tags = "分类相关接口")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 准确的说是获取所有分类名，因为表中的分类是会有重复的数据的
     *
     * @return
     */
    @ApiOperation("获取所有分类")
    @GetMapping("/getAll")
    public Result getAll() {
        List<Category> categoryList = categoryService.getAll();
        return Result.success(categoryList);
    }

    /**
     * 由于分类和菜谱是多对多的关系，所以一个分类在表中会有多个id，这就导致根据id实现不了查询的效果
     *
     * @param pageCategoryDto
     * @return
     */
    @PostMapping("/getRecipe")
    @ApiOperation("通过分类名分页查询分类的菜谱")
    public PageResult getRecipe(@RequestBody PageCategoryDto pageCategoryDto) {
        PageResult pageResult = categoryService.getRecipe(pageCategoryDto);
        // 缓存未命中，查询数据库
        return pageResult;
    }

}

