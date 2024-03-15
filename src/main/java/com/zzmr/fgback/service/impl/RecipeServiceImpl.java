package com.zzmr.fgback.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zzmr.fgback.bean.*;
import com.zzmr.fgback.constant.OrderConstant;
import com.zzmr.fgback.constant.ViewConstant;
import com.zzmr.fgback.dto.AddRecipeDto;
import com.zzmr.fgback.dto.MaterialsDto;
import com.zzmr.fgback.dto.RecipeDto;
import com.zzmr.fgback.mapper.*;
import com.zzmr.fgback.result.PageResult;
import com.zzmr.fgback.service.RecipeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzmr.fgback.service.SearchHistoryService;
import com.zzmr.fgback.util.ContextUtils;
import com.zzmr.fgback.util.RedisUtils;
import com.zzmr.fgback.vo.RecipeBasicVo;
import com.zzmr.fgback.vo.RecipeViews;
import com.zzmr.fgback.vo.RecipeVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zzmr
 * @since 2024-01-13
 */
@Service
@Slf4j
public class RecipeServiceImpl extends ServiceImpl<RecipeMapper, Recipe> implements RecipeService {

    @Autowired
    private RecipeMapper recipeMapper;

    @Autowired
    private RecipeStepMapper recipeStepMapper;

    @Autowired
    private MaterialsMapper materialsMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private SearchHistoryService searchHistoryService;

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
    /*@Override
    public List<RecipeBasicVo> getByMaterials(List<MaterialsDto> materialsDtoList) {

        List<String> materialsNameList = new ArrayList<>();
        for (MaterialsDto materialsDTO : materialsDtoList) {
            materialsNameList.add(materialsDTO.getMaterialName());
        }
        List<RecipeBasicVo> recipeBasicVos = recipeMapper.getByMaterials(materialsNameList);
        return recipeBasicVos;
    }*/

    /**
     * 根据用料名称查询菜谱
     *
     * @param materialList
     * @return
     */
    @Override
    public List<RecipeBasicVo> getByMaterialsByArr(List<String> materialList) {
        List<RecipeBasicVo> recipeBasicVos = recipeMapper.getByMaterials(materialList);
        return recipeBasicVos;
    }

    /**
     * 分页查询菜谱列表
     * 有三种排序方式，默认是创建时间降序
     * 可选点赞/收藏数量降序
     * <p>
     * 这个接口没办法直接添加分类的搜索方法，思路是在Service中调用Category的查询方法，然后将结果用一个Set来去重整合
     * 不太行，这个地方还要涉及到分页呢，整合到一块后还要处理分页的事情，不如直接改sql了
     *
     * @param recipeDto
     * @return
     */
    @Override
    public PageResult getRecipeList(RecipeDto recipeDto) {
        if (recipeDto.getTitle() != null) {
            // 插入该历史记录
            searchHistoryService.insert(recipeDto.getTitle());
        }
        // 下面是搜索正常逻辑
        if (StringUtils.isEmpty(recipeDto.getOrderBy())) {
            // 如果排序字段为空，那就是默认使用create_time降序排序
            recipeDto.setOrderBy(OrderConstant.DEFAULT);
        }
        PageHelper.startPage(recipeDto.getPage(), recipeDto.getPageSize(), recipeDto.getOrderBy() + OrderConstant.DESC);
        Page<RecipeBasicVo> page = recipeMapper.getRecipeList(recipeDto.getTitle());
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
        recipe.setAuthorId(ContextUtils.getCurrentId());
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
    @Transactional
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
    @Transactional
    public void insertOthers(AddRecipeDto addRecipeDto, Recipe recipe) {
        Long recipeId = recipe.getRecipeId();
        List<Materials> materialsList = addRecipeDto.getMaterialsList();
        List<RecipeStep> recipeStepList = addRecipeDto.getRecipeStepList();
        materialsList.forEach(materials -> materials.setRecipeId(recipeId));
        recipeStepList.forEach(recipeStep -> recipeStep.setRecipeId(recipeId));
        materialsMapper.insertBatch(materialsList);
        recipeStepMapper.insertBatch(recipeStepList);
    }

    /**
     * 项目启动时写缓存
     */
    @PostConstruct
    public void initViews() {
        writeCache();
    }

    @Override
    public void writeCache() {
        log.info("菜谱浏览量写入缓存开始=====>");
        // 获取菜谱的访问量
        List<RecipeViews> viewsList = recipeMapper.getViews();
        // 放到缓存中
        for (RecipeViews recipeViews : viewsList) {
            redisUtils.set("recipeView" + ":" + recipeViews.getRecipeId(), recipeViews.getViews() + "");
        }
        log.info("写入缓冲完成<=======");
    }

}
