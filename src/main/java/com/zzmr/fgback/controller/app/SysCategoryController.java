package com.zzmr.fgback.controller.app;


import com.zzmr.fgback.bean.MainCategory;
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
@RequestMapping("/app/sysCategory")
@CrossOrigin
@Api(tags = "展示分类相关接口")
@Slf4j
public class SysCategoryController {

    @Autowired
    private SysCategoryService sysCategoryService;

    @ApiOperation("获取大类，以及大类下的分类")
    @GetMapping("/getAllCategory")
    public Result getAllCategory() {
        List<CategoryVo> categoryVoList = sysCategoryService.getAllCategory();
        return Result.success(categoryVoList);
    }

}

