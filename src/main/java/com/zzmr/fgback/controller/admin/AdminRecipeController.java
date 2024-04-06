package com.zzmr.fgback.controller.admin;

import com.zzmr.fgback.bean.Recipe;
import com.zzmr.fgback.result.Result;
import com.zzmr.fgback.service.RecipeService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/test")
    public Result test() {
        List<Recipe> list = recipeService.list();
        return Result.success(list);
    }

}
