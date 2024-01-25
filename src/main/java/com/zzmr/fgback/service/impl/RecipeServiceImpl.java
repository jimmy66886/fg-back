package com.zzmr.fgback.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zzmr.fgback.bean.*;
import com.zzmr.fgback.constant.LikesConstant;
import com.zzmr.fgback.dto.MaterialsDTO;
import com.zzmr.fgback.exception.BaseException;
import com.zzmr.fgback.mapper.*;
import com.zzmr.fgback.service.RecipeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzmr.fgback.vo.RecipeVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zzmr
 * @since 2024-01-13
 */
@Service
public class RecipeServiceImpl extends ServiceImpl<RecipeMapper, Recipe> implements RecipeService {

    @Autowired
    private RecipeMapper recipeMapper;

    @Autowired
    private MaterialsMapper materialsMapper;

    /**
     * 根据菜谱id查询菜谱以及菜谱对应用料
     *
     * @param recipeId
     * @return
     */
    @Override
    public RecipeVO getByRecipeId(Long recipeId) {
        RecipeVO recipeVO = getRecipeVO(recipeId);
        return recipeVO;
    }

    /**
     * 输入菜谱id，自动获取有关菜谱和菜谱对应的用料和菜谱的步骤
     *
     * @param recipeId
     * @return
     */
    private RecipeVO getRecipeVO(Long recipeId) {

        RecipeVO recipeVO = recipeMapper.getRecipeVO(recipeId);
        return recipeVO;

        // RecipeVO recipeVO = new RecipeVO();
        // Recipe recipe = recipeMapper.selectById(recipeId);
        // BeanUtils.copyProperties(recipe, recipeVO);
        // /**
        //  * 先查到菜谱的基本信息
        //  * 再根据菜谱id去用料表中查找所包含的用料
        //  */
        // LambdaQueryWrapper<Materials> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // lambdaQueryWrapper.eq(Materials::getRecipeId, recipeId);
        // recipeVO.setMaterials(materialsMapper.selectList(lambdaQueryWrapper));
        //
        // /**
        //  * 根据菜谱id去菜谱步骤表中查找菜谱步骤
        //  */
        // LambdaQueryWrapper<RecipeStep> lambdaQueryWrapper1 = new LambdaQueryWrapper<>();
        // lambdaQueryWrapper1.eq(RecipeStep::getRecipeId, recipeId);
        // List<RecipeStep> recipeSteps = recipeStepMapper.selectList(lambdaQueryWrapper1);
        // recipeVO.setRecipeSteps(recipeSteps);
        //
        // /**
        //  * 获取菜谱点赞数和收藏数
        //  */
        // LambdaQueryWrapper<Likes> lambdaQueryWrapper2 = new LambdaQueryWrapper();
        // lambdaQueryWrapper2.eq(Likes::getContentId, recipeId).eq(Likes::getContentType, LikesConstant.RecipeType);
        // recipeVO.setLikeNumber(likesMapper.selectCount(lambdaQueryWrapper2));
        // LambdaQueryWrapper<Favorite> lambdaQueryWrapper3 = new LambdaQueryWrapper<>();
        // lambdaQueryWrapper3.eq(Favorite::getRecipeId, recipeId);
        // recipeVO.setFavoriteNumber(favoriteMapper.selectCount(lambdaQueryWrapper3));
        //
        // return recipeVO;

    }

    /**
     * 根据用料名称查询菜谱
     *
     * @param materialsDTOS
     * @return
     */
    @Override
    public List<RecipeVO> getByMaterials(List<MaterialsDTO> materialsDTOS) {

        List<RecipeVO> recipeVOS = new ArrayList<>();

        // 获取用料名称集合
        List<String> materialNames = new ArrayList<>();
        for (MaterialsDTO materialsDTO : materialsDTOS) {
            materialNames.add(materialsDTO.getMaterialName());
        }
        LambdaQueryWrapper<Materials> lambdaQueryWrapper = new LambdaQueryWrapper();
        /**
         * 根据用料名在用料表中查找，sql为：SELECT * FROM materials WHERE `name` IN ('土豆','生抽')
         * 规则是：只要有查询用料中有一个存在对应的记录，就会返回这个结果，
         * 比如这里的土豆和生抽，土豆是有的，但是生抽没有，也会返回包含土豆的菜谱，并不是说一定要某个菜谱包含土豆又包含生抽
         */
        lambdaQueryWrapper.in(Materials::getName, materialNames);
        List<Materials> materials = materialsMapper.selectList(lambdaQueryWrapper);
        if (materials.isEmpty()) {
            throw new BaseException("暂无该食材相关的菜谱");
        }
        // 根据菜谱id查询菜谱详细信息
        for (Materials material : materials) {
            RecipeVO recipeVO = getRecipeVO(material.getRecipeId());
            recipeVOS.add(recipeVO);
        }
        return recipeVOS;
    }
}
