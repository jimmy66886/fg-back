package com.zzmr.fgback.vo;

import com.zzmr.fgback.bean.SysCategory;
import lombok.Data;

import java.util.List;

/**
 * @author zzmr
 * @create 2024-03-14 20:00
 */
@Data
public class CategoryVo {

    /**
     * 大类id
     */
    private Long mainCategoryId;

    /**
     * 大类名
     */
    private String name;

    /**
     * 大类下的所有分类
     */
    private List<SysCategory> categoryList;

}
