package com.zzmr.fgback.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zzmr.fgback.bean.Category;
import com.zzmr.fgback.constant.OrderConstant;
import com.zzmr.fgback.dto.PageCategoryDto;
import com.zzmr.fgback.mapper.CategoryMapper;
import com.zzmr.fgback.mapper.MainCategoryMapper;
import com.zzmr.fgback.result.PageResult;
import com.zzmr.fgback.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzmr.fgback.vo.CategoryVo;
import com.zzmr.fgback.vo.RecipeBasicVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private MainCategoryMapper mainCategoryMapper;

    /**
     * 通过分类名分页查询分类的菜谱
     *
     * @param pageCategoryDto
     * @return
     */
    @Override
    public PageResult getRecipe(PageCategoryDto pageCategoryDto) {
        if (StringUtils.isEmpty(pageCategoryDto.getOrderBy())) {
            pageCategoryDto.setOrderBy(OrderConstant.DEFAULT);
        }
        PageHelper.startPage(pageCategoryDto.getPage(), pageCategoryDto.getPageSize(),
                pageCategoryDto.getOrderBy() + OrderConstant.DESC);
        Page<RecipeBasicVo> page = categoryMapper.getRecipeList(pageCategoryDto.getName());
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 获取所有分类
     *
     * @return
     */
    @Override
    public List<Category> getAll() {
        List<Category> list = categoryMapper.getAll();
        return list;
    }

    /**
     * 根据菜谱id获取菜谱的分类集合
     *
     * @param recipeId
     * @return
     */
    @Override
    public List<Category> getByRecipeId(Long recipeId) {
        List<Category> categoryList =
                categoryMapper.selectList(new LambdaQueryWrapper<Category>().eq(Category::getRecipeId, recipeId));
        return categoryList;
    }
}
