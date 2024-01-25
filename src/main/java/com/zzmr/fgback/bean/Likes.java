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
public class Likes implements Serializable {

    @TableId(value = "likes_id", type = IdType.AUTO)
    private Long likesId;

    /**
     * 点赞内容类型 直接用recipe/comment
     */
    private String contentType;

    /**
     * 点赞内容id,文章id/评论id
     */
    private Long contentId;

    private Long userId;

    private LocalDateTime createTime;


}
