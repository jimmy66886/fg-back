package com.zzmr.fgback.dto;

import com.zzmr.fgback.bean.BasketMaterial;
import com.zzmr.fgback.bean.VegetableBasket;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author zzmr
 * @create 2024-01-30 10:16
 */
@Data
public class AddVegeBasketDto extends VegetableBasket {

    @ApiModelProperty(value = "菜篮子对应的材料集合")
    private List<BasketMaterial> basketMaterialList;

}
