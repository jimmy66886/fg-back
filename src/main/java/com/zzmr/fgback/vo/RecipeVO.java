package com.zzmr.fgback.vo;

import com.zzmr.fgback.bean.Materials;
import com.zzmr.fgback.bean.Recipe;
import lombok.Data;

import java.util.List;

/**
 * @author zzmr
 * @create 2024-01-23 21:27
 */
@Data
public class RecipeVO extends Recipe {

    // VO中多了一个用料集合
    private List<Materials> materials;

}
