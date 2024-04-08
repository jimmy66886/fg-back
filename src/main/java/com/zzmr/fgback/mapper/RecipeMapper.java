package com.zzmr.fgback.mapper;

import com.github.pagehelper.Page;
import com.zzmr.fgback.bean.Recipe;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzmr.fgback.vo.RecipeBasicVo;
import com.zzmr.fgback.vo.RecipeViews;
import com.zzmr.fgback.vo.RecipeVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zzmr
 * @since 2024-01-13
 */
@Mapper
public interface RecipeMapper extends BaseMapper<Recipe> {

    /**
     * 根据菜谱id获取菜谱以及关联信息
     *
     * @param recipeId
     * @return
     */
    RecipeVo getRecipeVo(Long recipeId);

    /**
     * 根据用料集合获取菜谱集合
     *
     * @param materialsNameList
     * @return
     */
    List<RecipeBasicVo> getByMaterials(@Param("materialsNameList") List<String> materialsNameList);


    /**
     * 根据title模糊查询
     *
     * @param title
     * @return
     */
    Page<RecipeBasicVo> getRecipeList(@Param("title") String title);

    List<RecipeBasicVo> getRecipeListByUserId(@Param("userId") Long userId);

    /**
     * 插入一条，并返回主键
     *
     * @param recipe
     * @return
     */
    Long insertOne(Recipe recipe);

    /**
     * 获取菜谱id和访问量
     *
     * @return
     */
    List<RecipeViews> getViews();

    /**
     * 管理员查询菜谱列表,单独写是因为这里要查出来status
     *
     * @param title
     * @return
     */
    Page<RecipeBasicVo> getRecipeListAdmin(String title);
}
