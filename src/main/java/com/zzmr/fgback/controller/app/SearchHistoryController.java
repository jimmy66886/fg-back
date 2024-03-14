package com.zzmr.fgback.controller.app;


import com.zzmr.fgback.bean.SearchHistory;
import com.zzmr.fgback.result.Result;
import com.zzmr.fgback.service.SearchHistoryService;
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
 * @since 2024-03-14
 */
@CrossOrigin
@Slf4j
@Api(tags = "用户搜索记录接口")
@RestController
@RequestMapping("/app/searchHistory")
public class SearchHistoryController {

    @Autowired
    private SearchHistoryService searchHistoryService;

    @ApiOperation("获取用户搜索记录")
    @GetMapping("/get")
    public Result get() {
        List<SearchHistory> resultList = searchHistoryService.get();
        return Result.success(resultList);
    }

    @ApiOperation("清除用户记录")
    @PostMapping("/deleteAll")
    public Result deleteAll() {
        searchHistoryService.deleteAll();
        return Result.success();
    }
}

