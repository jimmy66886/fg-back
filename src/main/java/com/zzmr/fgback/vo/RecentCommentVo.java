package com.zzmr.fgback.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author zzmr
 * @create 2024-04-03 11:11
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecentCommentVo {

    private Long commentId;

    // 发送者姓名
    private String senderName;

    // 发送者头像url
    private String senderAvatarUrl;

    // 发送者id
    private Long senderId;

    // 内容
    private String content;

    // 菜谱标题
    private String title;

    // 菜谱id
    private Long recipeId;

    // 接收评论内容
    private String originalContent;

    // 发送时间
    private LocalDateTime sendDateTime;

}
