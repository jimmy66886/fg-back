package com.zzmr.fgback.task;

import com.zzmr.fgback.bean.Recipe;
import com.zzmr.fgback.service.RecipeService;
import com.zzmr.fgback.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author zzmr
 * @create 2024-01-25 13:27
 */
@Slf4j
@Component
public class ViewsSchedule {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private RecipeService recipeService;

    /**
     * 从缓存中更新数据库中菜谱的访问量
     */
    @Scheduled(cron = "0 0/2 * * * ? ")
    public void updateViews() {
        log.info("定时任务开始:更新浏览量开始====>");
        Set<String> keys = redisUtils.getKeys("recipeView:*");
        List<Recipe> recipesToUpdate = new ArrayList<>();
        for (String key : keys) {
            String view = redisUtils.getStr(key);
            Recipe recipe = new Recipe();
            recipe.setRecipeId(Long.valueOf(key.substring(key.lastIndexOf(":") + 1)));
            recipe.setViews(Integer.valueOf(view));
            recipesToUpdate.add(recipe);
        }

        // 批量更新
        recipeService.updateBatchById(recipesToUpdate);

        // 清除缓存
        redisUtils.cleanCache("recipeView:*");
        redisUtils.cleanCache("recipe_*");
        log.info("更新浏览量结束<====");
        // 重新写入缓存
        recipeService.writeCache();
    }

}
