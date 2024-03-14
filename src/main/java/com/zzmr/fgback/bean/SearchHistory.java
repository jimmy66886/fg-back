package com.zzmr.fgback.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.*;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author zzmr
 * @since 2024-03-14
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class SearchHistory implements Serializable {


    @TableId(value = "history_id", type = IdType.AUTO)
    private Long historyId;

    /**
     * 词条
     */
    private String content;

    /**
     * 归属用户id
     */
    private Long userId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
