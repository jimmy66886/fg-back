package com.zzmr.fgback.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;

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

    @TableId(value = "recipe_id", type = IdType.AUTO)
    private Long recipeId;

    private String title;

    private String intro;

    private Long authorId;

    private String imageUrl;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer views;


}
