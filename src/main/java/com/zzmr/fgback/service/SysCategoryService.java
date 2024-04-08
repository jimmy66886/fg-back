package com.zzmr.fgback.service;

import com.zzmr.fgback.bean.MainCategory;
import com.zzmr.fgback.bean.SysCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zzmr.fgback.dto.AddCategoryDto;
import com.zzmr.fgback.vo.CategoryVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zzmr
 * @since 2024-04-08
 */
public interface SysCategoryService extends IService<SysCategory> {

    /**
     * 获取所有大类以及大类下的分类
     *
     * @return
     */
    List<CategoryVo> getAllCategory();

    /**
     * 新增大类
     *
     * @param mainCategory
     */
    void addMainCategory(MainCategory mainCategory);

    /**
     * 新增分类
     *
     * @param addCategoryDto
     */
    void addCategory(AddCategoryDto addCategoryDto);

    /**
     * 上传照片
     *
     * @param img
     * @return
     */
    String uploadImg(MultipartFile img);

    /**
     * 删除分类
     *
     * @param categoryId
     */
    void deleteCategory(Long categoryId);

    /**
     * 删除大类
     * @param mainCategory
     */
    void deleteMainCategory(MainCategory mainCategory);
}
