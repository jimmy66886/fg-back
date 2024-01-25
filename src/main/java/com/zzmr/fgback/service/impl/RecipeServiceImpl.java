package com.zzmr.fgback.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zzmr.fgback.bean.*;
import com.zzmr.fgback.dto.AddRecipeDto;
import com.zzmr.fgback.dto.MaterialsDto;
import com.zzmr.fgback.dto.RecipeDto;
import com.zzmr.fgback.mapper.*;
import com.zzmr.fgback.result.PageResult;
import com.zzmr.fgback.service.RecipeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzmr.fgback.vo.RecipeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
    private RecipeStepMapper recipeStepMapper;

    @Autowired
    private MaterialsMapper materialsMapper;

    /**
     * 根据菜谱id查询菜谱以及菜谱对应用料
     *
     * @param recipeId
     * @return
     */
    @Override
    public RecipeVo getByRecipeId(Long recipeId) {
        RecipeVo recipeVo = getRecipeVo(recipeId);
        return recipeVo;
    }

    /**
     * 输入菜谱id，获取有关菜谱和菜谱对应的用料和菜谱的步骤
     *
     * @param recipeId
     * @return
     */
    private RecipeVo getRecipeVo(Long recipeId) {
        RecipeVo recipeVo = recipeMapper.getRecipeVo(recipeId);
        return recipeVo;
    }

    /**
     * 根据用料名称查询菜谱
     * 只需要菜谱的基本信息
     *
     * @param materialsDtoList
     * @return
     */
    @Override
    public List<Recipe> getByMaterials(List<MaterialsDto> materialsDtoList) {

        List<String> materialsNameList = new ArrayList<>();
        for (MaterialsDto materialsDTO : materialsDtoList) {
            materialsNameList.add(materialsDTO.getMaterialName());
        }
        List<Recipe> recipes = recipeMapper.getByMaterials(materialsNameList);
        return recipes;
    }

    /**
     * 分页查询菜谱列表
     *
     * @param recipeDto
     * @return
     */
    @Override
    public PageResult getRecipeList(RecipeDto recipeDto) {
        PageHelper.startPage(recipeDto.getPage(), recipeDto.getPageSize());
        Page<Recipe> page = recipeMapper.getRecipeList(recipeDto.getTitle());
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 根据菜谱id删除菜谱信息
     * 以及菜谱对应的用料，步骤信息
     *
     * @param recipeId
     */
    @Override
    @Transactional
    public void removeOne(Long recipeId) {
        recipeMapper.deleteById(recipeId);
        recipeStepMapper.delete(new LambdaQueryWrapper<RecipeStep>()
                .eq(RecipeStep::getRecipeId, recipeId));
        materialsMapper.delete(new LambdaQueryWrapper<Materials>()
                .eq(Materials::getRecipeId, recipeId));
    }

    /**
     * 根据菜谱id集合删除菜谱信息
     * 以及菜谱对应的用料，步骤信息
     *
     * @param recipeIds
     */
    @Override
    @Transactional
    public void removeBatch(List<Long> recipeIds) {
        recipeMapper.deleteBatchIds(recipeIds);
        recipeStepMapper.delete(new LambdaQueryWrapper<RecipeStep>()
                .in(RecipeStep::getRecipeId, recipeIds));
        materialsMapper.delete(new LambdaQueryWrapper<Materials>()
                .in(Materials::getRecipeId, recipeIds));
    }

    /**
     * 添加菜谱以及菜谱对应的步骤-用料
     *
     * @param addRecipeDto
     */
    @Override
    @Transactional
    public void addRecipe(AddRecipeDto addRecipeDto) {
        Recipe recipe = new Recipe();
        BeanUtils.copyProperties(addRecipeDto, recipe);
        recipe.setViews(0);
        recipeMapper.insertOne(recipe);
        // 得到主键
        insertOthers(addRecipeDto, recipe);
    }

    /**
     * 修改一条记录
     *
     * @param addRecipeDto
     */
    @Override
    public void updateOne(AddRecipeDto addRecipeDto) {
        Recipe recipe = new Recipe();
        BeanUtils.copyProperties(addRecipeDto, recipe);
        recipe.setUpdateTime(LocalDateTime.now());
        recipeMapper.updateById(recipe);
        // 先把之前的食材和用料都删了
        recipeStepMapper.delete(new LambdaQueryWrapper<RecipeStep>()
                .eq(RecipeStep::getRecipeId, recipe.getRecipeId()));
        materialsMapper.delete(new LambdaQueryWrapper<Materials>()
                .eq(Materials::getRecipeId, recipe.getRecipeId()));
        // 重新插入
        insertOthers(addRecipeDto, recipe);
    }

    /**
     * 插入用料和菜谱步骤
     *
     * @param addRecipeDto
     * @param recipe
     */
    private void insertOthers(AddRecipeDto addRecipeDto, Recipe recipe) {
        Long recipeId = recipe.getRecipeId();
        List<Materials> materialsList = addRecipeDto.getMaterialsList();
        List<RecipeStep> recipeStepList = addRecipeDto.getRecipeStepList();
        materialsList.forEach(materials -> materials.setRecipeId(recipeId));
        recipeStepList.forEach(recipeStep -> recipeStep.setRecipeId(recipeId));
        materialsMapper.insertBatch(materialsList);
        recipeStepMapper.insertBatch(recipeStepList);
    }
}
