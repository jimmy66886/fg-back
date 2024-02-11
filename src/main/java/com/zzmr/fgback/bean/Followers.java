package com.zzmr.fgback.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author zzmr
 * @since 2024-02-06
 */
@Data
public class Followers implements Serializable {

    @TableId(value = "follow_id", type = IdType.AUTO)
    private Long followId;

    /**
     * 关注者id
     */
    private Long followerId;

    /**
     * 被关注着id
     */
    private Long followingId;

    private LocalDateTime createTime;


}
