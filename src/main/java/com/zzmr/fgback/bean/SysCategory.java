package com.zzmr.fgback.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author zzmr
 * @since 2024-04-08
 */
@Data
public class SysCategory implements Serializable {

    @TableId(value = "category_id", type = IdType.AUTO)
    private Long categoryId;

    private String name;

    private Long mainCategoryId;

    private String img;


}
