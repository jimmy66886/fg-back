package com.zzmr.fgback.controller.admin;

import com.zzmr.fgback.result.Result;
import com.zzmr.fgback.util.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author zzmr
 * @create 2024-04-10 10:33
 */
@RestController
@CrossOrigin
@Slf4j
@Api(tags = "管理端轮播图相关接口")
@RequestMapping("/admin/carousel")
public class AdminCarouselController {

    @Autowired
    private RedisUtils redisUtils;

    @ApiOperation("查询轮播图")
    @GetMapping("/get")
    public Result get() {
        Map<String, String> carouselList = redisUtils.getHash("carouselList");
        return Result.success(carouselList);
    }

    @ApiOperation("设置轮播图")
    @PostMapping("/update")
    public Result update(@RequestBody Map<String, String> carouselList) {
        redisUtils.setHash("carouselList", carouselList);
        return Result.success();
    }

}
