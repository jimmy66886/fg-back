package com.zzmr.fgback.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zzmr
 * @create 2024-01-23 21:35
 */
@Data
public class MaterialsDto {

    @ApiModelProperty(value = "用料名称")
    private String materialName;

}
