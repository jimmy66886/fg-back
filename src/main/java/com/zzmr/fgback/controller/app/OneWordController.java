package com.zzmr.fgback.controller.app;


import com.zzmr.fgback.bean.OneWord;
import com.zzmr.fgback.result.Result;
import com.zzmr.fgback.service.OneWordService;
import com.zzmr.fgback.util.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zzmr
 * @since 2024-02-16
 */
@RestController
@RequestMapping("/app/oneWord")
@CrossOrigin
@Api(tags = "一言相关接口")
@Slf4j
public class OneWordController {

    @Autowired
    private OneWordService oneWordService;

    @ApiOperation("获取一言")
    @GetMapping("/get")
    public Result get() {
        OneWord oneWord = oneWordService.get();
        return Result.success(oneWord);
    }

}

