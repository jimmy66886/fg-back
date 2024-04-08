package com.zzmr.fgback.controller.admin;


import com.zzmr.fgback.bean.MainCategory;
import com.zzmr.fgback.bean.SysCategory;
import com.zzmr.fgback.dto.AddCategoryDto;
import com.zzmr.fgback.result.Result;
import com.zzmr.fgback.service.SysCategoryService;
import com.zzmr.fgback.vo.CategoryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zzmr
 * @since 2024-04-08
 */
@RestController
@RequestMapping("/admin/sysCategory")
@CrossOrigin
@Api(tags = "展示分类相关接口")
@Slf4j
public class AdminCategoryController {

    @Autowired
    private SysCategoryService sysCategoryService;

    @ApiOperation("获取大类，以及大类下的分类")
    @GetMapping("/getAllCategory")
    public Result getAllCategory() {
        List<CategoryVo> categoryVoList = sysCategoryService.getAllCategory();
        return Result.success(categoryVoList);
    }

    @ApiOperation("大类的新增")
    @PostMapping("/addMainCategory")
    public Result addMainCategory(@RequestBody MainCategory mainCategory) {
        sysCategoryService.addMainCategory(mainCategory);
        return Result.success();
    }

    @ApiOperation("给大类下新增分类")
    @PostMapping("/addCategory")
    public Result addCategory(@RequestBody AddCategoryDto addCategoryDto) {
        sysCategoryService.addCategory(addCategoryDto);
        return Result.success();
    }

    @ApiOperation("给分类上传照片")
    @PostMapping("/uploadImg")
    public Result uploadImg(@RequestParam(name = "img") MultipartFile img) {
        String newAvatarUrl = sysCategoryService.uploadImg(img);
        return Result.success(newAvatarUrl);
    }

    @ApiOperation("删除分类")
    @PostMapping("/deleteCategory")
    public Result deleteCategory(@RequestBody SysCategory category) {
        sysCategoryService.deleteCategory(category.getCategoryId());
        return Result.success();
    }

    @ApiOperation("修改分类")
    @PostMapping("/updateCategory")
    public Result updateCategory(@RequestBody SysCategory sysCategory) {
        sysCategoryService.updateById(sysCategory);
        return Result.success();
    }

    @ApiOperation("删除大类")
    @PostMapping("/deleteMainCategory")
    public Result deleteMainCategory(@RequestBody MainCategory mainCategory) {
        sysCategoryService.deleteMainCategory(mainCategory);
        return Result.success();
    }

}

