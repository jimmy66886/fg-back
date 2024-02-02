package com.zzmr.fgback.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zzmr
 * @create 2024-02-02 9:20
 */
@Data
public class PageFavoriteDto implements Serializable {
    private Integer page;

    private Integer pageSize;

    private Long userId;
}
