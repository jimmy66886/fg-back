package com.zzmr.fgback.mapper;

import com.zzmr.fgback.bean.Recipe;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzmr.fgback.vo.RecipeVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zzmr
 * @since 2024-01-13
 */
@Mapper
public interface RecipeMapper extends BaseMapper<Recipe> {

    RecipeVO getRecipeVO(Long recipeId);

}
