package com.zzmr.fgback.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zzmr.fgback.bean.Recipe;
import com.zzmr.fgback.mapper.RecipeMapper;
import com.zzmr.fgback.service.StatisticsService;
import com.zzmr.fgback.vo.OverAllVo;
import com.zzmr.fgback.vo.RecipeBasicVo;
import com.zzmr.fgback.vo.RecipeVo;
import com.zzmr.fgback.vo.TodayVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zzmr
 * @create 2024-04-09 9:33
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private RecipeMapper recipeMapper;

    /**
     * 获取今日数据
     *
     * @return
     */
    @Override
    public TodayVo getToday() {

        // 先获取今日新增
        TodayVo todayVo = recipeMapper.getAddition();
        // 获取今日点赞最多的菜谱/收藏最多的菜谱
        RecipeBasicVo likeRecipe = recipeMapper.getLikeRecipe();
        RecipeBasicVo favoriteRecipe = recipeMapper.getFavoriteRecipe();
        todayVo.setLikeRecipe(likeRecipe);
        todayVo.setFavoriteRecipe(favoriteRecipe);
        return todayVo;
    }

    /**
     * 获取总体数据
     *
     * @return
     */
    @Override
    public OverAllVo getOverall() {
        OverAllVo overAllVo = recipeMapper.getOverall();

        // 获取全站菜谱点赞top10
        List<RecipeBasicVo> likeTop10 = recipeMapper.getLikeTop10();
        List<RecipeBasicVo> favoriteTop10 = recipeMapper.getFavoriteTop10();

        overAllVo.setLikeTop10(likeTop10);
        overAllVo.setFavoriteTop10(favoriteTop10);

        return overAllVo;
    }
}
