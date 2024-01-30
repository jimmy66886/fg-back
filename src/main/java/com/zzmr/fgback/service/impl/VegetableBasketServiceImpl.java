package com.zzmr.fgback.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zzmr.fgback.bean.BasketMaterial;
import com.zzmr.fgback.bean.Materials;
import com.zzmr.fgback.bean.VegetableBasket;
import com.zzmr.fgback.dto.AddVegeBasketDto;
import com.zzmr.fgback.mapper.BasketMaterialMapper;
import com.zzmr.fgback.mapper.MaterialsMapper;
import com.zzmr.fgback.mapper.VegetableBasketMapper;
import com.zzmr.fgback.service.VegetableBasketService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzmr.fgback.vo.VegetableBasketVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zzmr
 * @since 2024-01-30
 */
@Service
public class VegetableBasketServiceImpl extends ServiceImpl<VegetableBasketMapper, VegetableBasket> implements VegetableBasketService {

    @Autowired
    private VegetableBasketMapper vegetableBasketMapper;

    @Autowired
    private BasketMaterialMapper basketMaterialMapper;

    @Autowired
    private MaterialsMapper materialsMapper;

    /**
     * 添加菜篮子
     * 如果包含菜谱id，就说明是根据菜谱添加的菜篮子，就不用传递用料了，再查一遍数据库
     *
     * @param addVegeBasketDto
     */
    @Override
    @Transactional
    public void add(AddVegeBasketDto addVegeBasketDto) {
        // TODO 从threadLocal中取出userId
        VegetableBasket vegetableBasket = new VegetableBasket();
        BeanUtils.copyProperties(addVegeBasketDto, vegetableBasket);
        vegetableBasket.setUserId(1L);
        vegetableBasketMapper.insertOne(vegetableBasket);
        Long basketId = vegetableBasket.getBasketId();

        // 判断是否是根据菜谱添加的用料
        if (addVegeBasketDto.getRecipeId() != null) {
            // 菜谱导入的菜篮子
            List<Materials> materialsList = materialsMapper.selectList(new LambdaQueryWrapper<Materials>()
                    .eq(Materials::getRecipeId, addVegeBasketDto.getRecipeId())
            );
            List<BasketMaterial> basketMaterialList = new ArrayList<>();
            for (Materials materials : materialsList) {
                BasketMaterial basketMaterial =
                        BasketMaterial.builder().basketId(basketId).materialName(materials.getName())
                                .materialAmount(materials.getAmount())
                                .build();
                basketMaterialList.add(basketMaterial);
            }
            basketMaterialMapper.insertBatch(basketMaterialList);
        } else {
            // 自定义菜篮子
            List<BasketMaterial> basketMaterialList = addVegeBasketDto.getBasketMaterialList();
            basketMaterialList.forEach(basketMaterial -> basketMaterial.setBasketId(basketId));
            basketMaterialMapper.insertBatch(basketMaterialList);
        }
    }

    /**
     * 查询用户的菜篮子
     *
     * @return
     */
    @Override
    public List<VegetableBasketVo> getAll() {
        // TODO 从threadLocal中取出userId
        Long userId = 1L;
        return vegetableBasketMapper.getAll(userId);
    }

    /**
     * 删除菜篮子
     *
     * @param basketId
     */
    @Override
    @Transactional
    public void delete(Long basketId) {
        // 删除菜篮子
        vegetableBasketMapper.deleteById(basketId);
        // 删除菜篮子对应的材料
        basketMaterialMapper.delete(new LambdaQueryWrapper<BasketMaterial>()
                .eq(BasketMaterial::getBasketId, basketId)
        );
    }

    /**
     * 修改菜篮子
     *
     * @param addVegeBasketDto
     */
    @Override
    @Transactional
    public void updateOne(AddVegeBasketDto addVegeBasketDto) {
        VegetableBasket vegetableBasket = new VegetableBasket();
        BeanUtils.copyProperties(addVegeBasketDto, vegetableBasket);
        // 更新菜篮子数据
        vegetableBasketMapper.updateById(vegetableBasket);

        // 删除掉菜篮子对应的材料
        basketMaterialMapper.delete(new LambdaQueryWrapper<BasketMaterial>()
                .eq(BasketMaterial::getBasketId, vegetableBasket.getBasketId())
        );
        // 重新添加
        List<BasketMaterial> basketMaterialList = addVegeBasketDto.getBasketMaterialList();
        basketMaterialList.forEach(basketMaterial -> basketMaterial.setBasketId(vegetableBasket.getBasketId()));
        basketMaterialMapper.insertBatch(basketMaterialList);

    }
}
