package com.zzmr.fgback.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zzmr.fgback.bean.Category;
import com.zzmr.fgback.dto.PageCategoryDto;
import com.zzmr.fgback.mapper.CategoryMapper;
import com.zzmr.fgback.result.PageResult;
import com.zzmr.fgback.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzmr.fgback.vo.RecipeBasicVo;
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

    /**
     * 通过分类名分页查询分类的菜谱
     *
     * @param pageCategoryDto
     * @return
     */
    @Override
    public PageResult getRecipe(PageCategoryDto pageCategoryDto) {
        PageHelper.startPage(pageCategoryDto.getPage(), pageCategoryDto.getPageSize());
        Page<RecipeBasicVo> page = categoryMapper.getRecipeList(pageCategoryDto.getName());
        return new PageResult(page.getTotal(),page.getResult());
    }

    /**
     * 获取所有分类
     * @return
     */
    @Override
    public List<Category> getAll() {
        List<Category> list = categoryMapper.getAll();
        return list;
    }
}
