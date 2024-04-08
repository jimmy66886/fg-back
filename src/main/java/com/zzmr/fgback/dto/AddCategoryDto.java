package com.zzmr.fgback.dto;

import lombok.Data;

/**
 * @author zzmr
 * @create 2024-04-08 14:51
 */
@Data
public class AddCategoryDto {

    // 大类id
    private Long mainCategoryId;

    // 分类名
    private String name;

    // 图片url
    private String img;

}
