package com.zzmr.fgback.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 *
 * </p>
 *
 * @author zzmr
 * @since 2024-03-14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MainCategory implements Serializable {

    @TableId(value = "main_category_id", type = IdType.AUTO)
    private Long mainCategoryId;

    private String name;

}
