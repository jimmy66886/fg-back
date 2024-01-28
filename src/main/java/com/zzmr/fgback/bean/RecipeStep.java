package com.zzmr.fgback.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class RecipeStep implements Serializable {

    @ApiModelProperty(value = "菜谱步骤id")
    @TableId(value = "recipe_step_id", type = IdType.AUTO)
    private Long recipeStepId;

    @ApiModelProperty(value = "步骤数，从1开始，1，2，3，4,....")
    private Integer stepNumber;

    @ApiModelProperty(value = "菜谱id")
    private Long recipeId;

    @ApiModelProperty(value = "步骤图片")
    private String img;

    @ApiModelProperty(value = "步骤描述")
    private String content;


}
