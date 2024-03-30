package com.zzmr.fgback.service;

import com.zzmr.fgback.bean.Recipe;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzmr.fgback.dto.AddRecipeDto;
import com.zzmr.fgback.dto.MaterialsDto;
import com.zzmr.fgback.dto.RecipeDto;
import com.zzmr.fgback.result.PageResult;
import com.zzmr.fgback.vo.RecipeBasicVo;
import com.zzmr.fgback.vo.RecipeVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zzmr
 * @since 2024-01-13
 */
public interface RecipeService extends IService<Recipe> {

    /**
     * 根据菜谱id查询菜谱以及菜谱对应用料
     *
     * @param recipeId
     * @return
     */
    RecipeVo getByRecipeId(Long recipeId);

    /**
     * 根据用料名称查询菜谱
     *
     * @param materialsDtoList
     * @return
     */
    // List<RecipeBasicVo> getByMaterials(List<MaterialsDto> materialsDtoList);

    /**
     * 分页查询菜谱列表
     * 可根据title模糊查询
     *
     * @param recipeDto
     * @return
     */
    PageResult getRecipeList(RecipeDto recipeDto);

    /**
     * 根据菜谱id删除菜谱信息
     * 以及菜谱对应的用料，步骤信息
     *
     * @param recipeId
     */
    void removeOne(Long recipeId);

    /**
     * 根据菜谱id集合删除菜谱信息
     * 以及菜谱对应的用料，步骤信息
     *
     * @param recipeIds
     */
    void removeBatch(List<Long> recipeIds);

    /**
     * 添加菜谱以及菜谱对应的步骤-用料
     *
     * @param addRecipeDto
     */
    void addRecipe(AddRecipeDto addRecipeDto);

    /**
     * 修改一条记录
     *
     * @param addRecipeDto
     */
    void updateOne(AddRecipeDto addRecipeDto);

    /**
     * 写菜谱浏览量缓存
     */
    void writeCache();

    /**
     * 根据用料名称查询菜谱
     * @param materialList
     * @return
     */
    List<RecipeBasicVo> getByMaterialsByArr(List<String> materialList);

    /**
     * 查询用户所有的菜谱
     * @return
     */
    List<RecipeBasicVo> getByUserId(Long userId);

    /**
     * 菜谱识别
     * @param img
     * @return
     */
    PageResult recognition(MultipartFile img);
}
