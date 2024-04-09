package com.zzmr.fgback.controller.admin;

import com.zzmr.fgback.result.Result;
import com.zzmr.fgback.service.RecipeService;
import com.zzmr.fgback.service.StatisticsService;
import com.zzmr.fgback.vo.OverAllVo;
import com.zzmr.fgback.vo.TodayVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zzmr
 * @create 2024-04-09 9:31
 */

@RestController
@CrossOrigin
@Slf4j
@Api(tags = "统计相关接口")
@RequestMapping("/admin/statistics")
public class AdminStatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @ApiOperation("获取各项今日数据")
    @GetMapping("/getToday")
    public Result getToday() {
        TodayVo todayVo = statisticsService.getToday();
        return Result.success(todayVo);
    }

    @ApiOperation("获取总体数据")
    @GetMapping("/getOverall")
    public Result getOverall() {
        OverAllVo overAllVo = statisticsService.getOverall();
        return Result.success(overAllVo);
    }

}
