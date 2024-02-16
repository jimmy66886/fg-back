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
 * @since 2024-02-16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OneWord implements Serializable {

    @TableId(value = "text_id", type = IdType.AUTO)
    private Long textId;

    private String content;


}
