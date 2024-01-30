package com.zzmr.fgback.mapper;

import com.zzmr.fgback.bean.BasketMaterial;
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
 * @since 2024-01-30
 */
@Mapper
public interface BasketMaterialMapper extends BaseMapper<BasketMaterial> {

    /**
     * 批量插入菜篮子材料
     * @param basketMaterialList
     */
    void insertBatch(@Param("bml") List<BasketMaterial> basketMaterialList);
}
