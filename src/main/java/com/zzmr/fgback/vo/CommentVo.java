package com.zzmr.fgback.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author zzmr
 * @create 2024-01-17 21:42
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class CommentVo {

    private Long commentId;

    // 发送者姓名
    private String senderName;

    // 发送者头像url
    private String senderAvatarUrl;

    // 发送者id
    private Long senderId;

    // 接收者姓名
    private String receiverName;

    // 接收者头像url
    private String receiverAvatarUrl;

    // 接收者id
    private Long receiverId;

    // 内容
    private String content;

    // 发送时间
    private LocalDateTime sendDateTime;

}
