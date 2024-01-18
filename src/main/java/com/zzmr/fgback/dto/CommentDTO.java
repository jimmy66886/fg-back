package com.zzmr.fgback.dto;

import lombok.Data;

/**
 * @author zzmr
 * @create 2024-01-17 9:15
 */
@Data
public class CommentDTO {

    private Long recipeId;

    private Long rootId;

    private Integer page;

    private Integer pageSize;

}
