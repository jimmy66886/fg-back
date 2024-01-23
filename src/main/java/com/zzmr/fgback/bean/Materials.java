package com.zzmr.fgback.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author zzmr
 * @since 2024-01-23
 */
@Data
@Builder
public class Materials implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

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
