package com.zzmr.fgback.mapper;

import com.zzmr.fgback.bean.RecipeStep;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zzmr
 * @since 2024-01-24
 */
@Mapper
public interface RecipeStepMapper extends BaseMapper<RecipeStep> {

    /**
     * 批量插入菜谱步骤
     * @param recipeStepList
     */
    void insertBatch(@Param("recipeStepList") List<RecipeStep> recipeStepList);
}
