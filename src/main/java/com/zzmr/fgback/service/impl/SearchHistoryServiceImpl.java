package com.zzmr.fgback.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zzmr.fgback.bean.SearchHistory;
import com.zzmr.fgback.mapper.SearchHistoryMapper;
import com.zzmr.fgback.service.SearchHistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzmr.fgback.util.ContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zzmr
 * @since 2024-03-14
 */
@Service
public class SearchHistoryServiceImpl extends ServiceImpl<SearchHistoryMapper, SearchHistory> implements SearchHistoryService {

    @Autowired
    private SearchHistoryMapper searchHistoryMapper;

    @Override
    public List<SearchHistory> get() {
        Long userId = ContextUtils.getCurrentId();
        List<SearchHistory> searchHistories =
                searchHistoryMapper.selectList(new LambdaQueryWrapper<SearchHistory>().eq(SearchHistory::getUserId,
                        userId).orderByDesc(SearchHistory::getCreateTime));
        return searchHistories;
    }

    /**
     * 插入一条搜索记录，该方法由RecipeController中的查询接口调用
     *
     * @param content
     */
    @Override
    public void insert(String content) {
        Long userId = ContextUtils.getCurrentId();
        // 插入前判断存不存在，如果存在则删掉之前的，插入新的
        SearchHistory searchHistoryDB =
                searchHistoryMapper.selectOne(new LambdaQueryWrapper<SearchHistory>().eq(SearchHistory::getUserId,
                        userId).eq(SearchHistory::getContent, content));
        if (searchHistoryDB != null) {
            searchHistoryMapper.deleteById(searchHistoryDB.getHistoryId());
        }
        SearchHistory searchHistory = SearchHistory.builder().content(content).userId(userId).build();
        searchHistoryMapper.insert(searchHistory);
    }

    /**
     * 清除用户搜索记录
     */
    @Override
    public void deleteAll() {
        Long userId = ContextUtils.getCurrentId();
        searchHistoryMapper.delete(new LambdaQueryWrapper<SearchHistory>().eq(SearchHistory::getUserId, userId));
    }
}
