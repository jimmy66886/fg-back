package com.zzmr.fgback.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @author zzmr
 * @create 2024-01-17 21:42
 */
@Data
@Builder
public class CommentVO {

    // 发送者姓名
    private String senderName;

    // 发送者id
    private Long senderId;

    // 接收者姓名
    private String receiveName;

    // 接收者id
    private Long receiveId;

    // 内容
    private String content;

}
