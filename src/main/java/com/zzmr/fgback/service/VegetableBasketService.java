package com.zzmr.fgback.service;

import com.zzmr.fgback.bean.VegetableBasket;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzmr.fgback.dto.AddVegeBasketDto;
import com.zzmr.fgback.vo.VegetableBasketVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zzmr
 * @since 2024-01-30
 */
public interface VegetableBasketService extends IService<VegetableBasket> {

    /**
     * 添加菜篮子
     * @param addVegeBasketDto
     */
    void add(AddVegeBasketDto addVegeBasketDto);

    /**
     * 获取用户所有的菜篮子
     * @return
     */
    List<VegetableBasketVo> getAll();

    /**
     * 删除菜篮子
     * @param basketId
     */
    void delete(Long basketId);

    /**
     * 修改菜篮子
     * @param addVegeBasketDto
     */
    void updateOne(AddVegeBasketDto addVegeBasketDto);
}
