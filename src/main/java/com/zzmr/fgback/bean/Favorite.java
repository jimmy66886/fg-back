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
public class Favorite implements Serializable {

    @ApiModelProperty(value = "收藏id")
    @TableId(value = "favorite_id", type = IdType.AUTO)
    private Long favoriteId;

    @ApiModelProperty(value = "收藏归属的用户id")
    private Long userId;

    @ApiModelProperty(value = "收藏的菜谱id")
    private Long recipeId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "收藏对应的收藏夹id,为空则表示是用户的默认收藏")
    private Long favoritesId;



}
