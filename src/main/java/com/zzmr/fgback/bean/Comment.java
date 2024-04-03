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
public class Comment implements Serializable {

    @ApiModelProperty(value = "评论id")
    @TableId(value = "comment_id", type = IdType.AUTO)
    private Long CommentId;

    @ApiModelProperty("菜谱id")
    private Long recipeId;

    @ApiModelProperty(value = "评论内容")
    private String content;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "根评论id,为空则表示是顶级评论")
    private Long rootId;

    @ApiModelProperty(value = "被回复的评论id")
    private Long toCommentId;

    @ApiModelProperty(value = "被回复的用户id")
    private Long toUserId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;


}
