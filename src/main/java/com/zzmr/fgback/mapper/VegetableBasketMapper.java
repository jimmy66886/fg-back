package com.zzmr.fgback.mapper;

import com.zzmr.fgback.bean.VegetableBasket;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzmr.fgback.vo.VegetableBasketVo;
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
public interface VegetableBasketMapper extends BaseMapper<VegetableBasket> {

    /**
     * 插入一条
     * @param vegetableBasket
     */
    void insertOne(@Param("vb") VegetableBasket vegetableBasket);

    /**
     * 获取用户所有的菜篮子
     * @param userId
     * @return
     */
    List<VegetableBasketVo> getAll(Long userId);
}
