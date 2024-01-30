package com.zzmr.fgback.bean;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author zzmr
 * @since 2024-01-30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class VegetableBasket implements Serializable {

    @TableId(value = "basket_id", type = IdType.AUTO)
    private Long basketId;

    private Long userId;

    /**
     * 菜谱id，通过菜谱添加到的菜篮子会有菜谱id
     */
    private Long recipeId;

    /**
     * 菜篮子名
     */
    private String basketName;

    /**
     * 菜篮子创建时间
     */
    private LocalDateTime createTime;


}
