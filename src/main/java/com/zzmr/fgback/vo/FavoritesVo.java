package com.zzmr.fgback.vo;

import com.zzmr.fgback.bean.Favorites;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author zzmr
 * @create 2024-02-01 11:57
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class FavoritesVo {

    @ApiModelProperty(value = "收藏夹id")
    private Long favoritesId;

    @ApiModelProperty(value = "收藏夹封面图")
    private String coverImg;

    @ApiModelProperty(value = "收藏夹名字")
    private String name;

    @ApiModelProperty(value = "收藏夹内菜谱数量")
    private Integer number;

    @ApiModelProperty(value = "收藏夹简介")
    private String intro;

}
