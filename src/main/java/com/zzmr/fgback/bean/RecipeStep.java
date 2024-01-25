package com.zzmr.fgback.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

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

    @TableId(value = "recipe_step_id", type = IdType.AUTO)
    private Long recipeStepId;

    /**
     * 步骤数，从1开始，1，2，3，4,....
     */
    private Integer stepNumber;

    /**
     * 菜谱id
     */
    private Long recipeId;

    /**
     * 步骤图片
     */
    private String img;

    /**
     * 步骤描述
     */
    private String content;


}
