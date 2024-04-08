package com.zzmr.fgback.service;

import com.zzmr.fgback.bean.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzmr.fgback.dto.PageCategoryDto;
import com.zzmr.fgback.result.PageResult;
import com.zzmr.fgback.vo.CategoryVo;
import com.zzmr.fgback.vo.RecipeBasicVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zzmr
 * @since 2024-01-13
 */
public interface CategoryService extends IService<Category> {

    /**
     * 通过分类名分页查询分类的菜谱
     * @param pageCategoryDto
     * @return
     */
    PageResult getRecipe(PageCategoryDto pageCategoryDto);

    /**
     * 获取所有分类
     * @return
     */
    List<Category> getAll();


    /**
     * 根据菜谱id获取菜谱的分类集合
     * @param recipeId
     * @return
     */
    List<Category> getByRecipeId(Long recipeId);
}
