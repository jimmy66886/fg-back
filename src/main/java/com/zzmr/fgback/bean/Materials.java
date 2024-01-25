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
 * @since 2024-01-23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class Materials implements Serializable {

    @TableId(value = "materials_id", type = IdType.AUTO)
    private Long materialsId;

    private Long recipeId;

    /**
     * 食材名称
     */
    private String name;

    /**
     * 用量：1勺，200克，2个
     */
    private String amount;


}
