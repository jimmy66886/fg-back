package com.zzmr.fgback.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(value = "点赞id")
    @TableId(value = "likes_id", type = IdType.AUTO)
    private Long likesId;

    @ApiModelProperty(value = "点赞内容类型 直接用recipe/comment")
    private String contentType;

    @ApiModelProperty(value = "点赞内容id,文章id/评论id")
    private Long contentId;

    @ApiModelProperty(value = "点赞的用户")
    private Long userId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;


}
