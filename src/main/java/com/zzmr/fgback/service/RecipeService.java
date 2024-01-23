package com.zzmr.fgback.service;

import com.zzmr.fgback.bean.Recipe;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzmr.fgback.dto.MaterialsDTO;
import com.zzmr.fgback.vo.RecipeVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zzmr
 * @since 2024-01-13
 */
public interface RecipeService extends IService<Recipe> {

    /**
     * 根据菜谱id查询菜谱以及菜谱对应用料
     * @param recipeId
     * @return
     */
    RecipeVO getByRecipeId(Long recipeId);

    /**
     * 根据用料名称查询菜谱
     * @param materialsDTOS
     * @return
     */
    List<RecipeVO> getByMaterials(List<MaterialsDTO> materialsDTOS);
}
