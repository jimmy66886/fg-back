package com.zzmr.fgback.mapper;

import com.zzmr.fgback.bean.SysCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzmr.fgback.vo.CategoryVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zzmr
 * @since 2024-04-08
 */
@Mapper
public interface SysCategoryMapper extends BaseMapper<SysCategory> {

    /**
     * 获取所有大类以及大类下的分类
     * @return
     */
    List<CategoryVo> getAllCategory();
}
