package com.zzmr.fgback.mapper;

import com.zzmr.fgback.bean.Materials;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zzmr
 * @since 2024-01-23
 */
@Mapper
public interface MaterialsMapper extends BaseMapper<Materials> {

    /**
     * 批量插入用料
     * @param materialsList
     */
    void insertBatch(@Param("materialsList") List<Materials> materialsList);
}
