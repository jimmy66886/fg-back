package com.zzmr.fgback.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @author zzmr
 * @create 2024-01-28 18:01
 */
@Data
public class PageCategoryDto {

    @ApiModelProperty(value = "页号")
    private Integer page;

    @ApiModelProperty(value = "页面大小")
    private Integer pageSize;

    @ApiModelProperty(value = "分类名",example = "家常菜")
    private String name;

}
