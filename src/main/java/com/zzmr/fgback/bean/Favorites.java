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
 * @since 2024-01-24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class Favorites implements Serializable {

    @ApiModelProperty(value = "收藏夹id")
    @TableId(value = "favorites_id", type = IdType.AUTO)
    private Long favoritesId;

    @ApiModelProperty(value = "收藏夹归属用户id")
    private Long userId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "收藏夹名称")
    private String name;

    @ApiModelProperty(value = "收藏夹描述")
    private String intro;

}
