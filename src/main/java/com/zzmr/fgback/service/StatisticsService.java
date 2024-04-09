package com.zzmr.fgback.service;

import com.zzmr.fgback.vo.OverAllVo;
import com.zzmr.fgback.vo.TodayVo;

/**
 * @author zzmr
 * @create 2024-04-09 9:33
 */
public interface StatisticsService {
    /**
     * 获取今日数据
     *
     * @return
     */
    TodayVo getToday();

    /**
     * 获取总体数据
     *
     * @return
     */
    OverAllVo getOverall();
}
