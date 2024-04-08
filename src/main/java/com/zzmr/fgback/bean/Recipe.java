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
public class Recipe implements Serializable {

    @ApiModelProperty(value = "菜谱id")
    @TableId(value = "recipe_id", type = IdType.AUTO)
    private Long recipeId;

    @ApiModelProperty(value = "菜谱标题")
    private String title;

    @ApiModelProperty(value = "菜谱简介")
    private String intro;

    @ApiModelProperty(value = "菜谱作者id")
    private Long authorId;

    @ApiModelProperty(value = "菜谱封面图")
    private String imageUrl;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "浏览量")
    private Integer views;

    /**
     * 1 表示正常
     * 0 表示隐藏
     */
    @ApiModelProperty(value = "状态")
    private Boolean status;

}
