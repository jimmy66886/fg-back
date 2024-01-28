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
 * @since 2024-01-23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class Materials implements Serializable {

    @ApiModelProperty(value = "用料id")
    @TableId(value = "materials_id", type = IdType.AUTO)
    private Long materialsId;

    @ApiModelProperty(value = "菜谱id")
    private Long recipeId;

    @ApiModelProperty(value = "食材名称")
    private String name;

    @ApiModelProperty(value = "用量：1勺,200克,2个,适量")
    private String amount;


}
