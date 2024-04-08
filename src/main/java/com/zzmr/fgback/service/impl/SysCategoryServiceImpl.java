package com.zzmr.fgback.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zzmr.fgback.bean.MainCategory;
import com.zzmr.fgback.bean.SysCategory;
import com.zzmr.fgback.bean.User;
import com.zzmr.fgback.dto.AddCategoryDto;
import com.zzmr.fgback.mapper.MainCategoryMapper;
import com.zzmr.fgback.mapper.SysCategoryMapper;
import com.zzmr.fgback.service.SysCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzmr.fgback.util.ContextUtils;
import com.zzmr.fgback.util.MinioUtils;
import com.zzmr.fgback.vo.CategoryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zzmr
 * @since 2024-04-08
 */
@Service
public class SysCategoryServiceImpl extends ServiceImpl<SysCategoryMapper, SysCategory> implements SysCategoryService {

    @Autowired
    private SysCategoryMapper sysCategoryMapper;

    @Autowired
    private MainCategoryMapper mainCategoryMapper;

    @Autowired
    private MinioUtils minioUtils;

    /**
     * 获取所有大类以及大类下的分类
     *
     * @return
     */
    @Override
    public List<CategoryVo> getAllCategory() {
        List<CategoryVo> resultList = sysCategoryMapper.getAllCategory();
        return resultList;
    }

    /**
     * 新增大类
     *
     * @param mainCategory
     */
    @Override
    public void addMainCategory(MainCategory mainCategory) {
        mainCategoryMapper.insert(mainCategory);
    }

    /**
     * 新增分类
     *
     * @param addCategoryDto
     */
    @Override
    public void addCategory(AddCategoryDto addCategoryDto) {
        SysCategory sysCategory = new SysCategory();
        BeanUtils.copyProperties(addCategoryDto, sysCategory);
        sysCategoryMapper.insert(sysCategory);
    }

    /**
     * 上传照片
     *
     * @param img
     * @return
     */
    @Override
    public String uploadImg(MultipartFile img) {
        return minioUtils.upload(img);
    }

    /**
     * 删除分类
     *
     * @param categoryId
     */
    @Override
    public void deleteCategory(Long categoryId) {
        sysCategoryMapper.deleteById(categoryId);
    }

    /**
     * 删除大类
     * 以及其下的分类
     *
     * @param mainCategory
     */
    @Override
    @Transactional
    public void deleteMainCategory(MainCategory mainCategory) {
        mainCategoryMapper.deleteById(mainCategory);
        sysCategoryMapper.delete(new LambdaQueryWrapper<SysCategory>().eq(SysCategory::getMainCategoryId,
                mainCategory.getMainCategoryId()));
    }
}
