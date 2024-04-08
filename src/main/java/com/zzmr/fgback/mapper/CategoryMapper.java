package com.zzmr.fgback.mapper;

import com.github.pagehelper.Page;
import com.zzmr.fgback.bean.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzmr.fgback.vo.CategoryVo;
import com.zzmr.fgback.vo.RecipeBasicVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zzmr
 * @since 2024-01-13
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

    /**
     * 通过分类id分页查询分类的菜谱
     *
     * @return
     */
    Page<RecipeBasicVo> getRecipeList(@Param("name") String name);

    /**
     * 获取所有分类
     *
     * @return
     */
    List<Category> getAll();

    /**
     * 批量插入分类
     *
     * @param categoryList
     */
    void insertBatch(@Param("categoryList") List<Category> categoryList);
}
