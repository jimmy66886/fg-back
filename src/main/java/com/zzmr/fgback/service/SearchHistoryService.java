package com.zzmr.fgback.service;

import com.zzmr.fgback.bean.SearchHistory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zzmr
 * @since 2024-03-14
 */
public interface SearchHistoryService extends IService<SearchHistory> {

    /**
     * 获取用户搜索记录
     *
     * @return
     */
    List<SearchHistory> get();

    /**
     * 插入一条搜索记录
     */
    void insert(String content);

    /**
     * 清楚用户搜索记录
     */
    void deleteAll();
}
