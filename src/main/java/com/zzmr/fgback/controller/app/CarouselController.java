package com.zzmr.fgback.controller.app;

import com.zzmr.fgback.result.Result;
import com.zzmr.fgback.util.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author zzmr
 * @create 2024-04-10 10:33
 */
@RestController
@CrossOrigin
@Slf4j
@Api(tags = "用户端轮播图接口")
@RequestMapping("/app/carousel")
public class CarouselController {

    @Autowired
    private RedisUtils redisUtils;

    @ApiOperation("查询轮播图")
    @GetMapping("/get")
    public Result get() {
        Map<String, String> carouselList = redisUtils.getHash("carouselList");
        return Result.success(carouselList);
    }

}
