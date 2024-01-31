package com.zzmr.fgback.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author zzmr
 * @create 2024-01-30 17:23
 */
@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class RecipeViews {
    private Long recipeId;

    private Integer views;
}
