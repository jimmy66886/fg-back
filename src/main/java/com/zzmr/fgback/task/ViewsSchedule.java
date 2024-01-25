package com.zzmr.fgback.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author zzmr
 * @create 2024-01-25 13:27
 */
@Slf4j
@Component
public class ViewsSchedule {

    /**
     * 从缓存中更新数据库中菜谱的访问量
     */
    @Scheduled(cron = "0 0/2 * * * ? ")
    public void updateViews() {
        // TODO 更新逻辑
        System.out.println(LocalDateTime.now() + "  更新访问量");
    }

}
