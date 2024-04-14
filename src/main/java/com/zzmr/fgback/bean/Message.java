package com.zzmr.fgback.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.*;

/**
 * <p>
 *
 * </p>
 *
 * @author zzmr
 * @since 2024-04-14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message implements Serializable {


    @TableId(value = "message_id", type = IdType.AUTO)
    private Long messageId;

    private String title;

    private String message;

    private LocalDateTime createTime;

    private Long userId;


}
