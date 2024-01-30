package com.zzmr.fgback.vo;

import com.zzmr.fgback.bean.BasketMaterial;
import com.zzmr.fgback.bean.VegetableBasket;
import lombok.Data;

import java.util.List;

/**
 * @author zzmr
 * @create 2024-01-30 11:28
 */
@Data
public class VegetableBasketVo extends VegetableBasket {
    private List<BasketMaterial> basketMaterialList;
}
