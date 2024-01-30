package com.zzmr.fgback.controller;


import com.zzmr.fgback.bean.VegetableBasket;
import com.zzmr.fgback.dto.AddVegeBasketDto;
import com.zzmr.fgback.result.Result;
import com.zzmr.fgback.service.VegetableBasketService;
import com.zzmr.fgback.vo.VegetableBasketVo;
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
 * @since 2024-01-30
 */
@RestController
@RequestMapping("/vegetableBasket")
@Api(tags = "菜篮子相关接口")
public class VegetableBasketController {

    @Autowired
    private VegetableBasketService vegetableBasketService;

    /**
     * 分为用户自定义的菜篮子以及从菜谱中直接添加的菜篮子
     * 如果是用户自定义的，就不会有recipeId
     * 如果是根据菜谱用料添加进来的，就会包含菜谱id，并且菜篮子的名字就是菜谱的名字
     * <p>
     * TODO 所以前端传数据时，如果是根据菜谱添加的，就要把菜谱的title赋值给basketName上
     *
     * @param addVegeBasketDto
     * @return
     */
    @ApiOperation("添加菜篮子")
    @PostMapping("/add")
    public Result add(@RequestBody AddVegeBasketDto addVegeBasketDto) {
        vegetableBasketService.add(addVegeBasketDto);
        return Result.success();
    }

    @ApiOperation("查询用户所有的菜篮子")
    @GetMapping("/getAll")
    public Result getAll() {
        List<VegetableBasketVo> vegetableBasketVoList = vegetableBasketService.getAll();
        return Result.success(vegetableBasketVoList);
    }

    @ApiOperation("删除菜篮子")
    @PostMapping("/delete")
    public Result delete(@RequestParam("basketId") Long basketId) {
        vegetableBasketService.delete(basketId);
        return Result.success();
    }

    /**
     * 这个就没什么说的了，在查询时无论是根据菜谱添加的菜篮子还是自定义的菜篮子，在前端都是同样的数据
     * 修改时当成一样的就行了
     * @param addVegeBasketDto
     * @return
     */
    @ApiOperation("修改菜篮子")
    @PostMapping("/update")
    public Result update(@RequestBody AddVegeBasketDto addVegeBasketDto) {
        vegetableBasketService.updateOne(addVegeBasketDto);
        return Result.success();
    }


}

