package com.zzmr.fgback.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.*;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author zzmr
 * @since 2024-01-13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class Comment implements Serializable {

    @TableId(value = "comment_id", type = IdType.AUTO)
    private Long CommentId;

    private Long recipeId;

    private String content;

    private Long userId;

    private Long rootId;

    private Long toId;

    private LocalDateTime createTime;


}
